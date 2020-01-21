package dfbz.com.dao;

import dfbz.com.annotation.TableAnnotation;
import dfbz.com.dao.base.BaseDao;
import dfbz.com.pojo.Favorite;
import dfbz.com.pojo.UserFocus;
import dfbz.com.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserFocusDao extends BaseDao<UserFocus> {
    public void removeFav(Integer id) {
        super.delById(id, UserFocus.class);
    }

    public void addFav(Integer ufId, Integer thisId) {
        String tableName = UserFocus.class.getAnnotation(TableAnnotation.class).value();
        if (ufId == null || thisId == null) {
            System.out.println("請輸入ufId或Id");
        }

        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            List<Map<String, Object>> list = runner.query("select id from " + tableName + " order by id", new MapListHandler());
            System.out.println(list.get(list.size() - 1).get("id").toString());
            Integer id = Integer.parseInt(list.get(list.size() - 1).get("id").toString()) + 1;
            UserFocus focus = new UserFocus(id, thisId, ufId);
            super.add(focus);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}