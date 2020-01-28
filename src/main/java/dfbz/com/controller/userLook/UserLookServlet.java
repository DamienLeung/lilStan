package dfbz.com.controller.userLook;

import dfbz.com.controller.BaseServlet;
import dfbz.com.pojo.User;
import dfbz.com.pojo.UserInfo;
import dfbz.com.service.UserLookService;

import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

@WebServlet("/userLook/*")
public class UserLookServlet extends BaseServlet {

    private UserLookService service = new UserLookService();

    public void showDetail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getSession().getAttribute("userId").toString();
        if (id != null) {
            Map<String, Object> details = service.getUserDetail(Integer.parseInt(id));
            if (details != null)
                req.getSession().setAttribute("userDetail", details);
            else
                System.out.println("ID錯誤！");
            resp.sendRedirect("/html/user_look.jsp");
        }
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Map<String, String[]> parameterMap = req.getParameterMap();
        User user;
        try {
            user = new User(Integer.parseInt(req.getSession().getAttribute("userId").toString()),
                    null, null, parameterMap.get("isSecret")[0], null,
                    Integer.parseInt(parameterMap.get("deptName")[0]));
        } catch (NumberFormatException e) {
            user = new User(Integer.parseInt(req.getSession().getAttribute("userId").toString()),
                    null, null, parameterMap.get("isSecret")[0], null,
                    null);
        }

        UserInfo info;
        System.out.println(parameterMap.get("gender")[0]);
        info = new UserInfo(Integer.parseInt(req.getSession().getAttribute("userId").toString()),
                parameterMap.get("realName")[0],
                Integer.parseInt(parameterMap.get("age")[0]),
                parameterMap.get("phone")[0],
                parameterMap.get("gender")[0],
                null, null,
                null, null, null);

        service.updateDetail(user, info);


    }
}
