package dfbz.com.dao;

import com.alibaba.druid.support.spring.DruidNativeJdbcExtractor;
import dfbz.com.annotation.TableAnnotation;
import dfbz.com.dao.base.BaseDao;
import dfbz.com.pojo.*;
import dfbz.com.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import javax.management.Query;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ArticleDetailDao extends BaseDao<Article> {

    public Map<String, Object> getArticleDtails(Integer id) {
        StringBuilder sql = new StringBuilder();
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        sql.append("select a.id, a.publish_username as author, a.title, a.publish_date as publishDate, " +
                "a.browse_count as view, a.content, a.user_id as userId ");
        String tableName = Article.class.getAnnotation(TableAnnotation.class).value();
        sql.append("from ").append(tableName).append(" a ");
        sql.append("where a.id = ?");
        try {
            return runner.query(sql.toString(), new MapHandler(), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Map<String, Object>> getUserIdFollowed(Integer userId) {
        if (userId != null) {
            StringBuilder sql = new StringBuilder();
            String tableName = User.class.getAnnotation(TableAnnotation.class).value();
            sql.append("select ui.real_name as realName, u.username, a.id ");
            sql.append("from ").append(tableName).append(" u ");
            tableName = UserInfo.class.getAnnotation(TableAnnotation.class).value();
            sql.append("left join ").append(tableName).append(" ui ");
            sql.append("on ui.user_id = u.id ");
            tableName = UserFocus.class.getAnnotation(TableAnnotation.class).value();
            sql.append("join (select user_focus_id as id from ").append(tableName).append(" uf ");
            sql.append("where uf.user_id = ?) a ");
            sql.append("on a.id = u.id");
            QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
            try {
                return runner.query(sql.toString(), new MapListHandler(), userId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Map<String, Object>> getFavDetail(Integer articleId) {
        if (articleId != null) {
            StringBuilder sql = new StringBuilder();
            String tableName = Favorite.class.getAnnotation(TableAnnotation.class).value();
            sql.append("select f.u_id as uId from ").append(tableName).append(" f ");
            tableName = Article.class.getAnnotation(TableAnnotation.class).value();
            sql.append("left join ").append(tableName).append(" a ");
            sql.append("on a.id = f.a_id ");
            sql.append("where f.a_id = ?");
            QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
            try {
                return runner.query(sql.toString(), new MapListHandler(), articleId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
