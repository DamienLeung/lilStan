package dfbz.com.dao;

import dfbz.com.annotation.TableAnnotation;
import dfbz.com.pojo.User;
import dfbz.com.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseDao<T> {

    public void delById(Integer id, Class<T> tClass) {
        String tableName = getTableName(tClass);
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            runner.update("delete from " + tableName + " where id = ?", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(T t) {
        Class tClass = t.getClass();
        String tableName = getTableName(tClass);
//        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        StringBuilder query = new StringBuilder("update " + tableName + " set ");
        Field[] args = tClass.getDeclaredFields();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Method getId = tClass.getDeclaredMethod("getId");
            Object id = getId.invoke(t);
            for (Field arg :
                    args) {
                arg.setAccessible(true);
                if (!arg.get(t).equals("") && arg.get(t) != null)
                    query.append(arg.getName()).append("=\"").append(arg.get(t).toString()).append("\", ");
            }
            query.delete(query.lastIndexOf(","), query.length());
            query.append(" where id = ").append(id.toString());
//            runner.update(query.toString(), tableName, id.toString());
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement(query.toString());
            System.out.println(query);
            statement.execute();
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null, statement, connection);
        }
    }

    public List<T> getListById(Class<T> tClass) {
        String tableName = getTableName(tClass);
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            ResultSetHandler<List<T>> h = new BeanListHandler<>(tClass);
            return runner.query("select * from " + tableName, h);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void add(T t) {
        Class tClass = t.getClass();
        String tableName = getTableName(tClass);
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            ArrayList<String> strings = new ArrayList<>();
            Field[] args = t.getClass().getDeclaredFields();
            for (Field arg :
                    args) {
                String col = arg.getName();
                String methodN = "get" + col.substring(0, 1).toUpperCase() + col.substring(1);
                Method method = t.getClass().getDeclaredMethod(methodN);
                strings.add(method.invoke(t).toString());
            }
            runner.update("insert into " + tableName + " values(?,?,?,?)", strings.toArray());

        } catch (SQLException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void delByIds(Integer[] ids, Class<T> tClass) {
        String tableName = getTableName(tClass);
        StringBuilder query = new StringBuilder("delete from " + tableName + " where ");
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            for (Integer id :
                    ids) {
                query.append("id=").append(id).append("||");
            }
            query.delete(query.lastIndexOf("||"), query.length());
            statement = connection.prepareStatement(query.toString());
            System.out.println(query.toString());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null, statement, connection);
        }
    }

    public Integer validateUser(T t) {
        Class tClass = t.getClass();
        String tableName = getTableName(tClass);
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        String name;
        String password;
        try {
            Method getUsername = tClass.getMethod("getUsername");
            Method getPassword = tClass.getMethod("getPassword");
            name = (String) getUsername.invoke(t);
            password = (String) getPassword.invoke(t);
            System.out.println(name + "-" + password);
            System.out.println(t);
            T t1 = runner.query(
                    "SELECT * FROM " + tableName + " WHERE username=? && password=?"
                    , new BeanHandler<T>(tClass), name, password);

            System.out.println(t1);
            if (t1 != null) {
                Method getId = tClass.getMethod("getId");
                Integer id = (Integer) getId.invoke(t1);
                return id;
            }
        } catch (SQLException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } /*finally {
            JDBCUtil.close(results, statement, connection);
        }*/

        System.out.println(t);
        return null;
    }

    public void register(T t) {
        Class tClass = t.getClass();
        String tableName = getTableName(tClass);
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            ArrayList<String> strings = new ArrayList<>();
            Field[] args = t.getClass().getDeclaredFields();
            for (Field arg :
                    args) {
                String col = arg.getName();
                String methodN = "get" + col.substring(0, 1).toUpperCase() + col.substring(1);
                Method method = t.getClass().getDeclaredMethod(methodN);
                if (method.invoke(t) != null)
                    strings.add(method.invoke(t).toString());
            }
            runner.update("insert into " + tableName + " values(?,?,?,?,?,null)", strings.toArray());
        } catch (NoSuchMethodException | IllegalAccessException | SQLException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void getField(Class<?> tClass, ResultSet results, Object o, Field[] args)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, SQLException {
        for (Field arg :
                args) {
            String col = arg.getName();
            String methodN = "set" + col.substring(0, 1).toUpperCase() + col.substring(1);
            Method method = tClass.getDeclaredMethod(methodN, arg.getType());
            method.invoke(o, results.getObject(col));
        }
    }

    public int getMaxId(Class<T> tClass) {
        String tableName = getTableName(tClass);
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
        try {
            return runner.query("select i.real_name, d.`name` from user_info i" +
                            "left join user u" +
                            "on u.id = i.user_id" +
                            "left join dept d" +
                            "on d.id = u.dept_id" +
                            "where u.id=1;"
                    , new MapHandler(), id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getTableName(Class<T> tClass) {
        TableAnnotation annotation = tClass.getAnnotation(TableAnnotation.class);
        return annotation.value();
    }
}
