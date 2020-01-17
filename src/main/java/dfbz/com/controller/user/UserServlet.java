package dfbz.com.controller.user;

import dfbz.com.controller.BaseServlet;
import dfbz.com.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/user/*")
public class UserServlet extends BaseServlet {

    private UserService service = new UserService();

    public void showRegister(HttpServletRequest req, HttpServletResponse resp) {

    }
}
