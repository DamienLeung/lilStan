package dfbz.com.dao;

import dfbz.com.annotation.TableAnnotation;
import dfbz.com.dao.base.BaseDao;
import dfbz.com.pojo.Article;
import dfbz.com.pojo.User;
import dfbz.com.pojo.UserInfo;
import dfbz.com.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ArticleDao extends BaseDao<Article> {
    public List<Map<String, Object>> getArticles(int page, String pattern) {
        StringBuilder sql = new StringBuilder();
        sql.append("select a.title, a.content, a.publish_date as publishDate, a.publish_username as author, " +
                "a.user_id as userId, a.browse_count as browseCount, a.id ");
        String tableName = Article.class.getAnnotation(TableAnnotation.class).value();
        sql.append("from ").append(tableName).append(" a ");
        if (pattern != null) {
            sql.append("where a.title like ? ");
            sql.append("order by a.id ");
            sql.append("limit ?, ?");
            QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
            try {
                return runner.query(sql.toString(), new MapListHandler(),
                        "%" + pattern + "%",
                        (page - 1) * 5, 5);
            } catch (SQLException e) { e.printStackTrace(); }
        } else {
            sql.append("order by a.id ");
            sql.append("limit ?, ?");
            QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
            try {
                return runner.query(sql.toString(), new MapListHandler(),
                        (page - 1) * 5, 5);
            } catch (SQLException e) { e.printStackTrace(); }
        }
        return null;
    }

    public int getArticleNum(String pattern) {
        StringBuilder sql = new StringBuilder();
        String tableName = Article.class.getAnnotation(TableAnnotation.class).value();
        sql.append("select * from ").append(tableName).append(" ");
        if (pattern != null) {
            sql.append("where title like ?");
            QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
            try {
                return runner.query(sql.toString(), new MapListHandler(), "%" + pattern + "%").size();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
            try {
                return runner.query(sql.toString(), new MapListHandler()).size();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int getId() {
        StringBuilder sql = new StringBuilder();
        String tableName = Article.class.getAnnotation(TableAnnotation.class).value();
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        sql.append("select id from ").append(tableName);
        try {
            List<Map<String, Object>> query = runner.query(sql.toString(), new MapListHandler());
            return Integer.parseInt(query.get(query.size() - 1).get("id").toString()) + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Map<String, Object> getName(Integer id) {
        StringBuilder sql = new StringBuilder();
        String tableName = User.class.getAnnotation(TableAnnotation.class).value();
        sql.append("select u.id, u.username, ui.real_name as realName from ").append(tableName).append(" u ");
        tableName = UserInfo.class.getAnnotation(TableAnnotation.class).value();
        sql.append("left join ").append(tableName).append(" ui ");
        sql.append("on u.id = ui.user_id ");
        sql.append("where u.id = ?");
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            return runner.query(sql.toString(), new MapHandler(), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
