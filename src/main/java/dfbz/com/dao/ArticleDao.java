package dfbz.com.dao;

import dfbz.com.annotation.TableAnnotation;
import dfbz.com.dao.base.BaseDao;
import dfbz.com.pojo.Article;
import dfbz.com.pojo.User;
import dfbz.com.pojo.UserInfo;
import dfbz.com.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ArticleDao extends BaseDao<Article> {
    public List<Map<String, Object>> getArticles(int page, String pattern) {
        StringBuilder sql = new StringBuilder();
        sql.append("select a.title, a.content, a.publish_date as publishDate, a.publish_username as author, " +
                "a.user_id as userId, a.browse_count as browseCount ");
        String tableName = Article.class.getAnnotation(TableAnnotation.class).value();
        sql.append("from ").append(tableName).append(" a ");
        if (pattern != null) {
            sql.append("where a.title like ?");
            QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
            try {
                return runner.query(sql.toString(), new MapListHandler(), "%" + pattern + "%");
            } catch (SQLException e) { e.printStackTrace(); }
        } else {
            QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
            try {
                return runner.query(sql.toString(), new MapListHandler());
            } catch (SQLException e) { e.printStackTrace(); }
        }
        return null;
    }
}
