package dfbz.com.dao;

import dfbz.com.dao.base.BaseDao;
import dfbz.com.pojo.UserInfo;
import dfbz.com.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;

import java.sql.SQLException;
import java.util.Map;

public class UserDetailDao extends BaseDao<UserInfo> {
    public Map<String, Object> getUserDetail(Integer id) {
        try {
            if (id != null) {
                QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());

                String sql = "select ui.user_id as userId, ui.real_name as realName, ui.age, ui.`look`, d.name, ui.gender, " +
                        "u.is_secret as isSecret, " +
                        "ui.phone, ui.`desc` as intro, ui.register_time as RegTime, ui.login_time as loginTime, ui.pic, " +
                        "(select count(*) as total from user_focus where user_focus_id = ui.user_id) as fansCount " +
                        "from user_info ui " +
                        "left join `user` u on u.id = ui.user_id " +
                        "left join dept d on d.id = ui.user_id " +
                        "where ui.user_id = ?";
                return runner.query(sql, new MapHandler(), id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
