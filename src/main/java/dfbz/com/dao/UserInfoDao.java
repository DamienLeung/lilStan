package dfbz.com.dao;

import dfbz.com.annotation.FieldAnnotation;
import dfbz.com.annotation.TableAnnotation;
import dfbz.com.dao.base.BaseDao;
import dfbz.com.pojo.User;
import dfbz.com.pojo.UserInfo;
import dfbz.com.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import javax.xml.crypto.Data;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserInfoDao extends BaseDao<UserInfo> {
    public void register(User user) {
        TableAnnotation annotation = User.class.getAnnotation(TableAnnotation.class);
        String s = annotation.keyName();
        try {
            Method getId = User.class.getMethod(s);
            String id = getId.invoke(user).toString();
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(Integer.parseInt(id));
            System.out.println(new Date().toString());
            userInfo.setRegisterTime(new Timestamp(System.currentTimeMillis()));
            userInfo.setLoginTime(new Timestamp(System.currentTimeMillis()));
            System.out.println(userInfo);
            add(userInfo);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void updateLoginTime(Integer id) {
        try {
            Field field = UserInfo.class.getDeclaredField("userId");
            String colName = field.getAnnotation(FieldAnnotation.class).value();
            UserInfo userInfo = rowQuery(colName, id, UserInfo.class);
            System.out.println(userInfo);
            Date time = new Date();
            System.out.println(time);
            userInfo.setLoginTime(new Timestamp(System.currentTimeMillis()));
            save(userInfo);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


    }

    private String getTableName(Class<?> tClass) {
        TableAnnotation annotation = tClass.getAnnotation(TableAnnotation.class);
        return annotation.value();
    }

    public int getListSize(String pattern) {
        StringBuilder sql = new StringBuilder("select ui.user_id ");
        String tableName = getTableName(UserInfo.class);
        sql.append("from ").append(tableName).append(" ui ");
        tableName = getTableName(User.class);
        sql.append("left join ").append(tableName).append(" u ");
        sql.append("on ui.user_id = u.id ");
        if (pattern != null) {
            sql.append("where u.username like ? or ");
            sql.append("ui.real_name like ? ");
        }
        sql.append("order by ui.user_id ");
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            List<Map<String, Object>> map = null;
            if (pattern == null) {
                map = runner.query(sql.toString(), new MapListHandler());
            } else {
                map = runner.query(sql.toString(), new MapListHandler()
                        , pattern + "%", pattern + "%");
            }
            return map.size();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
