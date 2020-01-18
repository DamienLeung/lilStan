package dfbz.com.service;

import dfbz.com.dao.UserDao;
import dfbz.com.dao.UserFocusDao;
import dfbz.com.dao.UserInfoDao;
import dfbz.com.pojo.User;
import dfbz.com.pojo.UserFocus;

import java.util.List;
import java.util.Map;

public class UserService {

    private UserDao dao = new UserDao();
    private UserInfoDao infoDao = new UserInfoDao();
    private UserFocusDao userFocusDao = new UserFocusDao();

    public Integer validateUser(User user) {
        return dao.validateUser(user);
    }

    public void register(User user) {
        dao.register(user);
        infoDao.register(user);
    }

    public void updateLoginTime(Integer id) {
        infoDao.updateLoginTime(id);
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

    public List<Map<String, Object>> getUsers(int pageN) {
        return dao.listMap(pageN);
    }

    public int getInfoListSize() {
        return infoDao.getListSize();
    }

    public void delFav(String uId) {
        userFocusDao.del(uId);
    }

    public User getUserByEmail(String email) {
        return dao.rowQuery("email", email, User.class);
    }

    public void update(User user) {
        dao.save(user);
    }
}
