package dfbz.com.service.impl;

import dfbz.com.dao.UserDao;
import dfbz.com.pojo.User;

public class UserServiceImpl {

    private UserDao dao = new UserDao();

    public boolean validateUser(User user) {
        return dao.validateUser(user);
    }
}
