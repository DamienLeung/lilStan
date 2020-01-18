package dfbz.com.dao;

import dfbz.com.annotation.TableAnnotation;
import dfbz.com.dao.base.BaseDao;
import dfbz.com.pojo.User;
import dfbz.com.pojo.UserInfo;
import dfbz.com.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import javax.xml.crypto.Data;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDao extends BaseDao<User> {

    private static final int MAX_PAGE_SIZE = 5;

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

            List<Map<String, Object>> list = runner.query("select id from " + tableName + " order by id", new MapListHandler());
            System.out.println(list.get(list.size() - 1).get("id").toString());
            return Integer.parseInt(list.get(list.size() - 1).get("id").toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Map<String, Object> getUserById(Integer id) {
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        Map<String, Object> map;
        try {
            map =  runner.query("select d.name as deptName, u.username as username from user u " +
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

    public void register(User user) {
        Class tClass = user.getClass();
        String tableName = getTableName();
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        StringBuilder sql = new StringBuilder("insert into ");
        sql.append(tableName).append(" values(");
        try {
            ArrayList<String> strings = new ArrayList<>();
            Field[] args = user.getClass().getDeclaredFields();
            for (Field arg :
                    args) {
                String col = arg.getName();
                String methodN = "get" + col.substring(0, 1).toUpperCase() + col.substring(1);
                Method method = user.getClass().getDeclaredMethod(methodN);
                if (method.invoke(user) != null) {
                    sql.append("?,");
                    strings.add(method.invoke(user).toString());
                } else
                    sql.append("null,");
            }
            sql.delete(sql.lastIndexOf(","), sql.length());
            sql.append(")");
            runner.update(sql.toString(), strings.toArray());
        } catch (NoSuchMethodException | IllegalAccessException | SQLException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private String getTableName() {
        TableAnnotation annotation = User.class.getAnnotation(TableAnnotation.class);
        return annotation.value();
    }

    public List<Map<String, Object>> listMap(int page) {
        StringBuilder sql = new StringBuilder("select ui.user_id as id, ui.real_name as realName" +
                ", ui.gender, ui.age, u.username, ui.`desc` ");
        String tableName = UserInfo.class.getAnnotation(TableAnnotation.class).value();
        sql.append("from ").append(tableName).append(" ui ");
        tableName = getTableName();
        sql.append("left join ").append(tableName).append(" u ");
        sql.append("on ui.user_id = u.id order by ui.user_id ");
        sql.append("limit ?,?");
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());

        try {
            List<Map<String, Object>> map =
                    runner.query(sql.toString(), new MapListHandler()
                            ,(page - 1) * MAX_PAGE_SIZE, MAX_PAGE_SIZE);
            System.out.println(page);
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}
