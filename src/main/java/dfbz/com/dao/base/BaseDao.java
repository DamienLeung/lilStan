package dfbz.com.dao.base;

import dfbz.com.annotation.FieldAnnotation;
import dfbz.com.annotation.TableAnnotation;
import dfbz.com.pojo.User;
import dfbz.com.pojo.UserInfo;
import dfbz.com.util.JDBCUtil;
import dfbz.com.util.MappingVarToColName;
import org.apache.commons.dbutils.*;
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
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            ArrayList<String> strings = new ArrayList<>();
            Method getId = null;
            if (tClass == UserInfo.class)
                getId = tClass.getMethod("getUserId");
            else
                getId = tClass.getMethod("getId");
            Object id = getId.invoke(t);
            for (Field arg :
                    args) {
                String col = arg.getName();

                String methodN = "get" + col.substring(0, 1).toUpperCase() + col.substring(1);
                Method method = null;
                method = t.getClass().getDeclaredMethod(methodN);
                if (method.invoke(t) != null) {
                    query.append("`").append(MappingVarToColName.mappingName(col)).append("`=");
                    strings.add(method.invoke(t).toString());
                    query.append("?,");
                }
            }
            query.delete(query.lastIndexOf(","), query.length());
            if (tClass.equals(UserInfo.class))
                query.append(" where user_id=").append(id.toString());
            else
                query.append(" where id=").append(id.toString());
            System.out.println(query);
            System.out.println(strings);
            runner.update(query.toString(), strings.toArray());
        } catch (NoSuchMethodException | IllegalAccessException
                | InvocationTargetException | SQLException e1) {
            e1.printStackTrace();
        }


    }
/*
    public List<T> getListById(Class<T> tClass) {
        String tableName = getTableName(tClass);
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        RowProcessor processor = new BasicRowProcessor(new GenerousBeanProcessor());
        try {
            ResultSetHandler<List<T>> h = new BeanListHandler<>(tClass, processor);
            return runner.query("select * from " + tableName, h);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    public void add(T t) {
        Class tClass = t.getClass();
        String tableName = getTableName(tClass);
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        StringBuilder sql = new StringBuilder("insert into ");
        sql.append(tableName).append(" values(");
        try {
            ArrayList<String> strings = new ArrayList<>();
            Field[] args = t.getClass().getDeclaredFields();
            for (Field arg :
                    args) {
                String col = arg.getName();
                String methodN = "get" + col.substring(0, 1).toUpperCase() + col.substring(1);
                Method method = t.getClass().getDeclaredMethod(methodN);
                if (method.invoke(t) == null)
                    sql.append("null,");
                else {
                    strings.add(method.invoke(t).toString());
                    sql.append("?,");
                }
            }
            sql.delete(sql.lastIndexOf(","), sql.length());
            sql.append(")");
            System.out.println(sql.toString());
            runner.update(sql.toString(), strings.toArray());

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
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null, statement, connection);
        }
    }


    public boolean checkExsistence(String colName, Object o, Class<T> tClass) {
        String tableName = getTableName(tClass);
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        RowProcessor processor = new BasicRowProcessor(new GenerousBeanProcessor());
        try {
            T query = runner.query(
                    "select * from " + tableName + " where " + colName + "=?"
                    , new BeanHandler<>(tClass, processor), o);
            if (query != null)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public T rowQuery(String colName, Object o, Class<T> tClass) {
        //String tableName = getTableName(tClass);
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        TableAnnotation annotation = tClass.getAnnotation(TableAnnotation.class);
        String tableName = annotation.value();
        System.out.println(tableName + "," + colName + "," + o);
        RowProcessor processor = new BasicRowProcessor(new GenerousBeanProcessor());
        try {
            T query = runner.query(
                    "select * from " + tableName + " where " + colName + " = ? "
                    , new BeanHandler<>(tClass, processor), o);
            System.out.println(query);
            if (query != null)
                return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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


    private String getTableName(Class<T> tClass) {
        TableAnnotation annotation = tClass.getAnnotation(TableAnnotation.class);
        return annotation.value();
    }

}
