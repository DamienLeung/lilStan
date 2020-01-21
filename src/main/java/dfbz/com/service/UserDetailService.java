package dfbz.com.service;

import dfbz.com.dao.UserDetailDao;
import dfbz.com.pojo.UserInfo;

import java.util.Map;

public class UserDetailService {
    private UserDetailDao dao = new UserDetailDao();

    public Map<String, Object> getUserDetail(Integer id) {
        return dao.getUserDetail(id);
    }

    public void viewIncretment (Integer id) {
        UserInfo userInfo = dao.rowQuery("user_id", id, UserInfo.class);
        userInfo.setLook(userInfo.getLook() + 1);
        dao.save(userInfo);
    }
}
