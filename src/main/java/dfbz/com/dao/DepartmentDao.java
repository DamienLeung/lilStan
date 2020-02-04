package dfbz.com.dao;

import dfbz.com.annotation.TableAnnotation;
import dfbz.com.dao.base.BaseDao;
import dfbz.com.pojo.Department;
import dfbz.com.pojo.User;
import dfbz.com.pojo.UserInfo;
import dfbz.com.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DepartmentDao extends BaseDao<Department> {

    public List<Map<String, Object>> getDepartments() {
        StringBuilder sql = new StringBuilder();
        String tableName = User.class.getAnnotation(TableAnnotation.class).value();
        sql.append("select d.id, d.name, ");
        sql.append("(select count(*) from ").append(tableName).append(" where dept_id = d.id) as amount ");
        tableName = Department.class.getAnnotation(TableAnnotation.class).value();
        sql.append(" from ").append(tableName).append(" d ");
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            return runner.query(sql.toString(), new MapListHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Map<String, Object>> getMembers(Integer departmentId) {
        StringBuilder sql = new StringBuilder();
        String tableName = User.class.getAnnotation(TableAnnotation.class).value();
        sql.append("select ui.real_name as realName, u.id, u.username from ").append(tableName).append(" u ");
        tableName = UserInfo.class.getAnnotation(TableAnnotation.class).value();
        sql.append("join ").append(tableName).append(" ui ");
        sql.append("on ui.user_id = u.id ");
        sql.append("where u.dept_id = ?");
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            return runner.query(sql.toString(), new MapListHandler(), departmentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
