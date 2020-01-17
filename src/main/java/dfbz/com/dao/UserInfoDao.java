package dfbz.com.dao;

import dfbz.com.annotation.FieldAnnotation;
import dfbz.com.annotation.TableAnnotation;
import dfbz.com.dao.base.BaseDao;
import dfbz.com.pojo.User;
import dfbz.com.pojo.UserInfo;

import javax.xml.crypto.Data;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public void update(Integer id) {
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

    private String getTableName(Class<UserInfo> tClass) {
        TableAnnotation annotation = tClass.getAnnotation(TableAnnotation.class);
        return annotation.value();
    }
}
