package dfbz.com.dao;

import dfbz.com.dao.base.BaseDao;
import dfbz.com.pojo.UserInfo;
import dfbz.com.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.apache.commons.dbutils.handlers.MapHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserDetailDao extends BaseDao<UserInfo> {
    public Map<String, Object> getUserDetail(Integer id) {
        try {
            if (id != null) {
                StringBuilder sql = new StringBuilder();
                sql.append("select ui.user_id as userId, ui.real_name as realName, ui.age, ui.`look`, d.name, ui.age, ");
                sql.append("ui.phone, ui.`desc` as intro, ui.register_time as RegTime, ui.login_time as loginTime, ui.pic, ");
                sql.append("(select count(*) as total from user_focus where user_focus_id = ui.user_id) as fansCount ");
                sql.append("from user_info ui ");
                sql.append("left join `user` u on u.id = ui.user_id ");
                sql.append("left join dept d on d.id = ui.user_id ");
                sql.append("where ui.user_id = ?");
                QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());

                return runner.query(sql.toString(), new MapHandler(), id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
