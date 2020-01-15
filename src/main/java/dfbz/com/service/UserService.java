package dfbz.com.service;

import dfbz.com.dao.UserDao;
import dfbz.com.pojo.User;

public class UserService {

    private UserDao dao = new UserDao();

    public boolean validateUser(User user) {
        return dao.validateUser(user);
    }

    public void register(User user) {
        dao.register(user);
    }

    public int getId(Class tClass) {
        return dao.getMaxId(tClass);
    }
}
