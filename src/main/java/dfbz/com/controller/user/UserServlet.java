package dfbz.com.controller.user;

import dfbz.com.controller.BaseServlet;
import dfbz.com.pojo.User;
import dfbz.com.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/user/login", "/user/register"})
public class UserServlet extends BaseServlet {

    private UserService service = new UserService();

    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String username = req.getParameter("form-username");
        String password = req.getParameter("form-password");
        System.out.println(1);
        System.out.println(username);
        System.out.println(password);
        String path = req.getContextPath();
        boolean b = service.validateUser(new User(username, password));
        if (b) {
            resp.sendRedirect(path + "/html/user.html");
        } else {
            resp.sendRedirect(path + "/index.jsp");
        }
    }

    public void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("form-username");
        String password = req.getParameter("form-password");
        String email = req.getParameter("form-email");
        int id = service.getId(User.class) + 1;
        User user = new User(id, username, password, email);
        System.out.println(user);
        service.register(user);
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
