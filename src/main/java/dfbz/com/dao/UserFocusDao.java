package dfbz.com.dao;

import dfbz.com.dao.base.BaseDao;
import dfbz.com.pojo.UserFocus;

public class UserFocusDao extends BaseDao<UserFocus> {

    public void del(String uId) {
        super.delById(Integer.parseInt(uId), UserFocus.class);
    }

}
