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
    public List<Map<String, Object>> getFavedArticles(Integer userId, String pattern, int page) {
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
        if (pattern != null) {
            sql.append("where a.title like ? and f.u_id = ? ");
            sql.append("order by a.id ");
            sql.append("limit ?, ?");
            QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
            try {
                return runner.query(sql.toString(), new MapListHandler(),
                        "%" + pattern + "%", userId, (page - 1) * 5, 5);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            sql.append("where f.u_id = ? ");
            sql.append("order by a.id ");
            sql.append("limit ?, ?");
            QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
            try {
                return runner.query(sql.toString(), new MapListHandler(),
                        userId, (page - 1) * 5, 5);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public int getListSize(Integer userId, String pattern) {
        StringBuilder sql = new StringBuilder();
        String tableName = Article.class.getAnnotation(TableAnnotation.class).value();
        sql.append("select * from ").append(tableName).append(" a ");
        tableName = Favorite.class.getAnnotation(TableAnnotation.class).value();
        sql.append("join ").append(tableName).append(" f ");
        sql.append("on f.a_id = a.id ");
        if (pattern != null) {
            sql.append("where a.title like ? and f.u_id = ?");
            QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
            try {
                return runner.query(sql.toString(), new MapListHandler(), "%" + pattern + "%", userId).size();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            sql.append("where f.u_id = ?");
            QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
            try {
                return runner.query(sql.toString(), new MapListHandler(), userId).size();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

}
