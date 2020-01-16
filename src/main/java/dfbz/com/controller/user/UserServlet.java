package dfbz.com.controller.user;

import dfbz.com.controller.BaseServlet;
import dfbz.com.pojo.User;
import dfbz.com.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private final static int MAX_COOKIE_TIME = 7 * 24 * 60 * 60;

    private UserService service = new UserService();

    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("form-username");
        String password = req.getParameter("form-password");
        String check = req.getParameter("remem-me");
        System.out.println(username);
        System.out.println(password);
        if (username != null && password != null) {
            String path = req.getContextPath();
            Integer id = service.validateUser(new User(username, password));
            if (id != null) {
                if (check != null) {
                    Cookie cookie = new Cookie("username", username);
                    cookie.setMaxAge(MAX_COOKIE_TIME);
                    cookie.setPath("/");
                    resp.addCookie(cookie);
                }
                req.getSession().setAttribute("userId", id);
                resp.sendRedirect(path + "/user/home");
            } else
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }


        //Map<String, Object> userInfo = service.getUserInfo(id);
        //req.getSession().setAttribute("userInfo", userInfo);

    }

    public void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("form-username");
        String password = req.getParameter("form-password");
        String email = req.getParameter("form-email");
        int id = service.getId() + 1;
        User user = new User(id, username, password, email);
        service.register(user);
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }

    public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies)
            if (cookie.getName().equals("username")) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                resp.addCookie(cookie);
                break;
            }
            req.getSession().removeAttribute("userId");
        resp.sendRedirect("/index.jsp");
    }
}
