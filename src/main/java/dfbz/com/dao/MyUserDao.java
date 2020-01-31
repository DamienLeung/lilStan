package dfbz.com.dao;

import dfbz.com.annotation.TableAnnotation;
import dfbz.com.dao.base.BaseDao;
import dfbz.com.pojo.User;
import dfbz.com.pojo.UserFocus;
import dfbz.com.pojo.UserInfo;
import dfbz.com.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MyUserDao extends BaseDao<UserFocus> {
    public List<Map<String, Object>>getUsers(int page, Integer id) {
        StringBuilder sql = new StringBuilder();
        sql.append("select u.username, ui.real_name as realName, uf.id ");
        String tableName = User.class.getAnnotation(TableAnnotation.class).value();
        sql.append("from ").append(tableName).append(" u ");
        tableName = UserInfo.class.getAnnotation(TableAnnotation.class).value();
        sql.append("join ").append(tableName).append(" ui on u.id = ui.user_id ");
        tableName = UserFocus.class.getAnnotation(TableAnnotation.class).value();
        sql.append("join ").append(tableName).append(" uf on ui.user_id = uf.user_focus_id ");
        sql.append("and uf.user_id = ? ");
        sql.append("order by u.id ");
        sql.append("limit ?,?");
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            List<Map<String, Object>> map = runner.query(sql.toString(), new MapListHandler()
                    , id, (page - 1) * 5, page * 5);
            for (Map<String, Object> user :
                    map) {
                if (user.get("realName") == null)
                    user.put("realName", user.get("username"));
            }
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getListSize(Integer id) {
        StringBuilder sql = new StringBuilder();
        String tableName = UserFocus.class.getAnnotation(TableAnnotation.class).value();
        sql.append("select * from ").append(tableName).append(" ");
        sql.append("where user_id = ?");
        System.out.println(sql.toString());
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            List<Map<String, Object>> query = runner.query(sql.toString(), new MapListHandler(), id);
            System.out.println(query);
            return query.size();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
