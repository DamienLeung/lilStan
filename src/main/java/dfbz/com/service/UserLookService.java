package dfbz.com.service;

import dfbz.com.dao.UserLookDao;

import java.util.List;
import java.util.Map;

public class UserLookService {
    private UserLookDao dao = new UserLookDao();

    public Map<String, Object> getUserDetail(Integer id) {
        return dao.getUserDetail(id);
    }

    public void updateDetail() {
        dao.update();
    }
}
