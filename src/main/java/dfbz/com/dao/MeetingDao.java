package dfbz.com.dao;

import dfbz.com.annotation.TableAnnotation;
import dfbz.com.dao.base.BaseDao;
import dfbz.com.pojo.Conference;
import dfbz.com.pojo.Department;
import dfbz.com.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import javax.management.Query;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MeetingDao extends BaseDao<Conference> {

    public List<Map<String, Object>> getConferences() {
        StringBuilder sql = new StringBuilder();
        String tableName = Conference.class.getAnnotation(TableAnnotation.class).value();
        sql.append("select id, dept_name as deptName, dept_id as deptId, title, " +
                "content, publish_date as publishDate, status from ").append(tableName).append(" c ");
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            return runner.query(sql.toString(), new MapListHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getConferenceId() {
        StringBuilder sql = new StringBuilder();
        String tableName = Conference.class.getAnnotation(TableAnnotation.class).value();
        sql.append("select id from ").append(tableName);
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            List<Map<String, Object>> query = runner.query(sql.toString(), new MapListHandler());
            if (query != null)
                return Integer.parseInt(query.get(query.size() - 1).get("id").toString()) + 1;
            else
                return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public String getDeptName(Integer deptId) {
        StringBuilder sql = new StringBuilder();
        String tableName = Department.class.getAnnotation(TableAnnotation.class).value();
        sql.append("select name from ").append(tableName).append(" where id = ?");
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            Map<String, Object> query = runner.query(sql.toString(), new MapHandler(), deptId);
            return query.get("name").toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
