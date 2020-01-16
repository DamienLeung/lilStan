package dfbz.com.dao.user;

import dfbz.com.annotation.TableAnnotation;
import dfbz.com.dao.BaseDao;
import dfbz.com.pojo.User;
import dfbz.com.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserDao extends BaseDao<User> {

    public Integer validateUser(User user) {
        String tableName = getTableName();
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        String name;
        String password;
        try {
            name = user.getUsername();
            password = user.getPassword();
            User u1 = runner.query(
                    "SELECT * FROM " + tableName + " WHERE username=? && password=?"
                    , new BeanHandler<>(User.class), name, password);

            System.out.println(u1);
            if (u1 != null) {
                return u1.getId();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(user);
        return null;
    }

    public int getMaxId() {
        String tableName = getTableName();
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            ResultSetHandler<List<String>> h = new BeanListHandler<>(String.class);
            List<String> list = runner.query("select id from " + tableName, h);
            System.out.println(list.size());
            return list.size();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Map<String, Object> getUserById(Integer id) {
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        Map<String, Object> map;
        try {
            map =  runner.query("select i.real_name, d.`name` as dept_name from user_info i " +
                            "left join user u " +
                            "on u.id = i.user_id " +
                            "left join dept d " +
                            "on d.id = u.dept_id " +
                            "where u.id=?;"
                    , new MapHandler(), id);
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getTableName() {
        TableAnnotation annotation = User.class.getAnnotation(TableAnnotation.class);
        return annotation.value();
    }
}
