import dfbz.com.service.UserDetailService;
import dfbz.com.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class test01 {

    @Test
    public void mappingName() {
        String s = "registerTime";
        StringBuilder name = new StringBuilder(s);
        char[] chars = s.toCharArray();
        int i = 0;
        while (i < chars.length) {
            if (Character.isUpperCase(chars[i])) {
                name.insert(name.indexOf(String.valueOf(chars[i])), "_");
                name.setCharAt(name.indexOf(String.valueOf(chars[i])), Character.toLowerCase(chars[i]));

            }
            i ++;
        }
        System.out.println(chars);
        System.out.println(name.toString());
    }

    @Test
    public void getUserDetail() {
        UserDetailService detailService = new UserDetailService();
        Integer userId = 1;
        Map<String, Object> userDetail = detailService.getUserDetail(userId);
        detailService.viewIncretment(userId);
        System.out.println(userDetail);
    }

    @Test
    public void getUsers() {
        StringBuilder sql = new StringBuilder();
        sql.append("select u.id, u.username, ui.real_name as realName ");
        sql.append("from user u ");
        sql.append("join user_info ui on u.id = ui.user_id ");
        sql.append("join user_focus uf on ui.user_id = uf.user_focus_id ");
        sql.append("and uf.user_id = ? ");
        sql.append("order by u.id");
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            List<Map<String, Object>> maps =  runner.query(sql.toString(), new MapListHandler(), 1);
            System.out.println(maps);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
