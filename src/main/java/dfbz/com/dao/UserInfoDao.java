package dfbz.com.dao;

import dfbz.com.annotation.TableAnnotation;
import dfbz.com.dao.base.BaseDao;
import dfbz.com.pojo.User;
import dfbz.com.pojo.UserInfo;
import dfbz.com.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
            userInfo.setRegisterTime(new Date());

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void update(Date date, Integer id) {
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        Class tClass = UserInfo.class;
        String tableName = getTableName(tClass);
        UserInfo info = new UserInfo();
        Field field;
        TableAnnotation annotation = UserInfo.class.getAnnotation(TableAnnotation.class);
        String colName = annotation.key();
        info = rowQuery(colName, id, UserInfo.class);
        info.setLoginTime(new Date());

    }

    private String getTableName(Class<UserInfo> tClass) {
        TableAnnotation annotation = tClass.getAnnotation(TableAnnotation.class);
        return annotation.value();
    }
}
