package dfbz.com.controller.view;

import dfbz.com.controller.BaseServlet;
import dfbz.com.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/view/home")
public class UIServlet extends BaseServlet {

    UserService service = new UserService();

    public void home(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Map<String, Object> userInfo =
                service.getUserInfo((Integer)req.getSession().getAttribute("userId"));
        System.out.println(userInfo);
        req.getSession().setAttribute("userInfo", userInfo);
        resp.sendRedirect(req.getContextPath() + "/html/home.jsp");
    }
}
