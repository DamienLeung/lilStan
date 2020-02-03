package dfbz.com.dao;

import dfbz.com.annotation.TableAnnotation;
import dfbz.com.dao.base.BaseDao;
import dfbz.com.pojo.Article;
import dfbz.com.pojo.Favorite;
import dfbz.com.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ArticleColDao extends BaseDao<Article> {
    public List<Map<String, Object>> getFavedArticles(Integer userId) {
        StringBuilder sql = new StringBuilder();
        String tableName = Favorite.class.getAnnotation(TableAnnotation.class).value();
        sql.append("select f.id as fId, a.id as aId, a.title, a.browse_count as view, a.content, " +
                "a.publish_date as publishDate, a.publish_username as author, " +
                "(select count(*) from ").append(tableName).append(" where a_id = f.a_id) as favCount ");
        tableName = Article.class.getAnnotation(TableAnnotation.class).value();
        sql.append("from ").append(tableName).append(" a ");
        tableName = Favorite.class.getAnnotation(TableAnnotation.class).value();
        sql.append("join ").append(tableName).append(" f ");
        sql.append("on f.a_id = a.id ");
        sql.append("where f.u_id = ?");

        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            return runner.query(sql.toString(), new MapListHandler(), userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
