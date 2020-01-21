import dfbz.com.service.UserDetailService;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
}
