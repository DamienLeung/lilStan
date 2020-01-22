package dfbz.com.service;

import dfbz.com.dao.MyUserDao;
import dfbz.com.dao.UserFocusDao;
import dfbz.com.pojo.UserFocus;

import java.util.List;
import java.util.Map;

public class MyUserService {

    UserFocusDao focusDao = new UserFocusDao();
    MyUserDao dao = new MyUserDao();
    public List<Map<String, Object>> getUsers(int page, Integer id) {
        if (id != null) {
            return null;
        } else {
            return null;
        }
    }

    public void unfollow(Integer id) {
        focusDao.removeFav(id);
    }

    public int getListSize(Integer id) {
        return dao.getListSize(id);
    }
}
