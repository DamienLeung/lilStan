package dfbz.com.service;

import dfbz.com.dao.UserDao;
import dfbz.com.dao.UserFocusDao;
import dfbz.com.dao.UserInfoDao;
import dfbz.com.pojo.User;

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

    public List<Map<String, Object>> getUsers(int page, String pattern, Integer id) {
        List<Map<String, Object>> map = dao.listMap(page, pattern, id);
        for (Map<String, Object> row :
                map) {
            if (row.get("realName") == null) {
                row.put("realName", row.get("username"));
            }
        }
        return map;
    }

    public int getInfoListSize(String pattern) {
        return infoDao.getListSize(pattern);
    }

    public void unfollow(Integer ufId) {
        userFocusDao.removeFav(ufId);
    }

    public void follow(Integer ufId, Integer id) {
        userFocusDao.addFav(ufId, id);
    }

    public User getUserByEmail(String email) {
        return dao.rowQuery("email", email, User.class);
    }

    public void update(User user) {
        dao.save(user);
    }

}
