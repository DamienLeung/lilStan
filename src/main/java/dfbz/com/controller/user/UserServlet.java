package dfbz.com.controller.user;

import dfbz.com.controller.BaseServlet;
import dfbz.com.pojo.User;
import dfbz.com.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/login")
public class UserServlet extends BaseServlet {

    private UserService service = new UserService();

    public void login(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("form-username");
        String password = req.getParameter("orm-password");

        System.out.println(username);
        System.out.println(password);
        String path = req.getContextPath();
        service.validateUser(new User(username, password));

    }
}
