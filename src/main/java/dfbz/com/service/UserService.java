package dfbz.com.service;

import dfbz.com.dao.user.UserDao;
import dfbz.com.pojo.User;

import java.util.Map;

public class UserService {

    private UserDao dao = new UserDao();

    public Integer validateUser(User user) {
        return dao.validateUser(user);
    }

    public void register(User user) {
        dao.register(user);
    }

    public int getId() {
        return dao.getMaxId();
    }

    public Map<String, Object> getUserInfo(Integer id) {
        return dao.getUserById(id);
    }
}
