package dfbz.com.service;

import dfbz.com.dao.UserLookDao;
import dfbz.com.pojo.User;
import dfbz.com.pojo.UserInfo;

import java.util.List;
import java.util.Map;

public class UserLookService {
    private UserLookDao dao = new UserLookDao();

    public Map<String, Object> getUserDetail(Integer id) {
        return dao.getUserDetail(id);
    }

    public void updateDetail(User user, UserInfo info) {
        dao.update(user, info);
    }

    public void uploadImg(Integer id, String path) {
        UserInfo userInfo = dao.rowQuery("user_id", id, UserInfo.class);
        userInfo.setPic(path);
        dao.save(userInfo);
    }
}
