package dfbz.com.service;

import dfbz.com.dao.DepartmentDao;

import java.util.List;
import java.util.Map;

public class DepartmentService {
    private DepartmentDao dao = new DepartmentDao();

    public List<Map<String, Object>> getDepartments() {
        return dao.getDepartments();
    }

    public List<Map<String, Object>> getMembers(Integer departmentId) {
        return dao.getMembers(departmentId);
    }
}
