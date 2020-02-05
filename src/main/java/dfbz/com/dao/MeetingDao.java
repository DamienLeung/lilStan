package dfbz.com.dao;

import dfbz.com.annotation.TableAnnotation;
import dfbz.com.dao.base.BaseDao;
import dfbz.com.pojo.Article;
import dfbz.com.pojo.ConJoin;
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

    public List<Map<String, Object>> getConferences(String pattern, Integer deptId, int page) {
        StringBuilder sql = new StringBuilder();
        String tableName = Conference.class.getAnnotation(TableAnnotation.class).value();
        sql.append("select id, dept_name as deptName, dept_id as deptId, title, " +
                "content, publish_date as publishDate, status from ").append(tableName).append(" ");
        if (pattern != null) {
            sql.append("where title like ? ");
            if (deptId != null)
                sql.append("and dept_id = ? ");
            sql.append("order by id ");
            sql.append("limit ?, ?");
            QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
            try {
                if (deptId != null)
                    return runner.query(sql.toString(), new MapListHandler(),
                            "%" + pattern + "%", deptId, (page - 1) * 5, 5);
                else
                    return runner.query(sql.toString(), new MapListHandler(),
                            "%" + pattern + "%", (page - 1) * 5, 5);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            if (deptId != null)
                sql.append("where dept_id = ? ");
            sql.append("order by id ");
            sql.append("limit ?, ?");
            QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
            try {
                if (deptId != null)
                    return runner.query(sql.toString(), new MapListHandler(),
                            deptId, (page - 1) * 5, 5);
                else
                    return runner.query(sql.toString(), new MapListHandler(),
                            (page - 1) * 5, 5);
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
            if (query.size() > 0)
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

    public int getConJoinId() {
        StringBuilder sql = new StringBuilder();
        String tableName = ConJoin.class.getAnnotation(TableAnnotation.class).value();
        sql.append("select * from ").append(tableName);
        try {
            QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
            List<Map<String, Object>> query = runner.query(sql.toString(), new MapListHandler());
            if (query.size() > 0)
                return Integer.parseInt(query.get(query.size() - 1).get("id").toString()) + 1;
            else
                return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public Integer getConJoinId(Integer id, Integer meetingId) {
        StringBuilder sql = new StringBuilder();
        String tableName = ConJoin.class.getAnnotation(TableAnnotation.class).value();
        sql.append("select * from ").append(tableName).append(" where u_id = ? and c_id = ? ");
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            Map<String, Object> query = runner.query(sql.toString(), new MapHandler(), id, meetingId);
            if (query != null)
                return Integer.parseInt(query.get("id").toString());
            else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getMeetingNum(String pattern, Integer deptId) {
        StringBuilder sql = new StringBuilder();
        String tableName = Conference.class.getAnnotation(TableAnnotation.class).value();
        sql.append("select * from ").append(tableName).append(" ");
        if (pattern != null) {
            sql.append("where title like ? ");
            QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
            if (deptId != null) {
                sql.append("and dept_id = ?");
                try {
                    return runner.query(sql.toString(), new MapListHandler(), "%" + pattern + "%", deptId).size();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    return runner.query(sql.toString(), new MapListHandler(), "%" + pattern + "%").size();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
            if (deptId != null) {
                sql.append("where dept_id = ?");
                try {
                    return runner.query(sql.toString(), new MapListHandler(), deptId).size();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    return runner.query(sql.toString(), new MapListHandler()).size();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }
}
