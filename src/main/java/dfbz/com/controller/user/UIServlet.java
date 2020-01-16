package dfbz.com.controller.user;

import dfbz.com.controller.BaseServlet;
import dfbz.com.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/user/home")
public class UIServlet extends BaseServlet {

    UserService service = new UserService();

    public void home(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Map<String, Object> userInfo = (Map<String, Object>) req.getSession().getAttribute("userInfo");
        resp.sendRedirect(req.getContextPath() + "/html/home.jsp");
    }
}
