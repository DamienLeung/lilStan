package dfbz.com.dao;

import dfbz.com.annotation.TableAnnotation;
import dfbz.com.dao.base.BaseDao;
import dfbz.com.pojo.Department;
import dfbz.com.pojo.User;
import dfbz.com.pojo.UserInfo;
import dfbz.com.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserLookDao extends BaseDao {
    public Map<String, Object> getUserDetail(Integer id) {
        StringBuilder sql = new StringBuilder();
        sql.append("select u.username, u.is_secret as isSecret, u.email, d.name as deptName" +
                ", ui.real_name as realName, ui.age, ui.phone, ui.gender, ui.register_time as regTime " +
                ", ui.login_time as loginTime, ui.pic, ui.look, ");
        sql.append("(select count(*) from user_focus where user_focus_id = ui.user_id) as fansCount ");
        String tableName = UserInfo.class.getAnnotation(TableAnnotation.class).value();
        sql.append("from ").append(tableName).append(" ui ");
        tableName = User.class.getAnnotation(TableAnnotation.class).value();
        sql.append("left join ").append(tableName).append(" u ");
        sql.append("on u.id = ui.user_id ");
        tableName = Department.class.getAnnotation(TableAnnotation.class).value();
        sql.append("left join ").append(tableName).append(" d ");
        sql.append("on d.id = u.dept_id ");
        sql.append("where u.id = ?");
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            return runner.query(sql.toString(), new MapHandler(), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(User user, UserInfo info) {
        super.save(info);
        super.save(user);
    }
}
