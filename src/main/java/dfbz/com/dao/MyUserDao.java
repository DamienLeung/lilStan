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
    public List<Map<String, Object>> getUsers(int page, Integer id) {
        StringBuilder sql = new StringBuilder();
        sql.append("select u.id, u.username, ui.real_name as realName ");
        String tableName = User.class.getAnnotation(TableAnnotation.class).value();
        sql.append("from ").append(tableName).append(" u ");
        tableName = UserInfo.class.getAnnotation(TableAnnotation.class).value();
        sql.append("join ").append(tableName).append(" on u.id = ui.user_id ");
        tableName = UserFocus.class.getAnnotation(TableAnnotation.class).value();
        sql.append("join ").append(tableName).append(" uf on ui.user_id = uf.user_focus_id ");
        sql.append("and uf.user_id = ? ");
        sql.append("order by u.id");
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            return runner.query(sql.toString(), new MapListHandler(), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getListSize(Integer id) {
        StringBuilder sql = new StringBuilder();
        String tableName = UserFocus.class.getAnnotation(TableAnnotation.class).value();
        sql.append("select * from ").append(tableName).append(" uf ");
        sql.append("where uf.user_id = ?");
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            Map<String, Object> maps = runner.query(sql.toString(), new MapHandler(), id);
            return maps.size();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
