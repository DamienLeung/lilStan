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
            Date time = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = format.format(time);
            String now = format.format(time);
            userInfo.setRegisterTime(date);
            userInfo.setLoginTime(now);
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

            Date time = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String now = format.format(time);
            userInfo.setLoginTime(now);
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
