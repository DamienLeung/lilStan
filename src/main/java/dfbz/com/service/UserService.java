package dfbz.com.service;

import dfbz.com.dao.UserDao;
import dfbz.com.dao.UserInfoDao;
import dfbz.com.pojo.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserService {

    private UserDao dao = new UserDao();
    private UserInfoDao infoDao = new UserInfoDao();

    public Integer validateUser(User user) {
        return dao.validateUser(user);
    }

    public void register(User user) {
        dao.register(user);
        infoDao.register(user);
    }

    public void updateInfo(Integer id) {
        infoDao.update(id);
    }

    public int getId() {
        return dao.getMaxId();
    }

    public Map<String, Object> getUserInfo(Integer id) {
        return dao.getUserById(id);
    }

    public boolean checkExsistence(String colName, Object o) {
        return dao.checkExsistence(colName, o, User.class);
    }

    public List<Map<String, Object>> findUserList(int page) {
        List<Map<String, Object>> map = dao.listMap(page);
        for (Map<String, Object> row :
                map) {
            if (row.get("realName") == null) {
                row.put("realName", row.get("username"));
            }
        }
        return map;
    }
}
