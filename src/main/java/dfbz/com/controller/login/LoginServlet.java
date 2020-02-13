package dfbz.com.controller.login;

import dfbz.com.controller.BaseServlet;
import dfbz.com.pojo.User;
import dfbz.com.service.UserService;
import dfbz.com.util.MailUtil;
import dfbz.com.util.StrUtil;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Random;

@WebServlet("/login/*")
public class LoginServlet extends BaseServlet {

    private final static int MAX_COOKIE_TIME = 7 * 24 * 60 * 60;

    private UserService service = new UserService();

    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("form-username");
        String password = req.getParameter("form-password");
        String check = req.getParameter("remem-me");
        if (username != null && password != null) {
            String path = req.getContextPath();
            Integer id = service.validateUser(new User(username, password));
            if (id != null) {
                service.updateLoginTime(id);
                if (check != null) {
                    Cookie cookie = new Cookie("username", username);
                    cookie.setMaxAge(MAX_COOKIE_TIME);
                    cookie.setPath("/");
                    resp.addCookie(cookie);
                }
                req.getSession().setMaxInactiveInterval(7200);
                req.getSession().setAttribute("userId", id);
                resp.sendRedirect(path + "/view/home");
            } else
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }


        //Map<String, Object> userInfo = service.getUserInfo(id);
        //req.getSession().setAttribute("userInfo", userInfo);

    }

    public void register(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("form-username");
        String password = req.getParameter("form-password");
        String email = req.getParameter("form-email");
        boolean b1 = service.checkExsistence("username", username);
        boolean b2 = service.checkExsistence("email", email);
        if (b1) {
            req.getSession().setAttribute("regErrorMsg", "用戶已存在");
            req.getRequestDispatcher(req.getContextPath() + "/register.jsp").forward(req, resp);
        } else {
            if (b2) {
                req.getSession().setAttribute("regErrorMsg", "郵箱已存在");
                req.getRequestDispatcher(req.getContextPath() + "/register.jsp").forward(req, resp);
            } else {
                int id = service.getId() + 1;
                User user = new User(id, username, password, email);
                service.register(user);
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
            }
        }
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

    public void sendCodeEmail(HttpServletRequest req, HttpServletResponse resp) {
        resp.setHeader("content-Type", "text/html");
        String email = req.getParameter("Email");
        System.out.println(email);
        try {
            if (StrUtil.checkEmail(email)) {
                Object object = req.getSession().getAttribute("code");
                if (object == null) {
                    String code = StrUtil.createRandomNum();
                    String content = "你的驗證碼是：" + code;
                    req.getSession().setAttribute("code", code);
                    req.getSession().setMaxInactiveInterval(120);
                    MailUtil.send(email, "lilStan重置密碼", content, MailUtil.UNICODE);
                    resp.getWriter().write("驗證碼已發送，請查收郵箱");
                } else {
                    resp.getWriter().write("驗證碼已發送到你的郵箱，請查收");
                }
            } else {
                resp.getWriter().write("郵箱格式不符，請確認好郵箱格式");
            }
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }

    public void resetPassword(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("form-username");
        String code = req.getParameter("form-code");
        String password = req.getParameter("form-password");
        try {
            if (StrUtil.checkEmail(email)) {
                System.out.println("郵箱");
                Object object = req.getSession().getAttribute("code");
                if (object != null)
                    if (String.valueOf(object).equals(code)) {
                        System.out.println(object);
                        User user = service.getUserByEmail(email);
                        if (user != null) {
                            user.setPassword(password);
                            service.update(user);
                            resp.sendRedirect(req.getContextPath() + "/index.jsp");
                        }
                    }
            } else {

                req.getSession().setAttribute("resetErrorMsg", "重置失敗");
                resp.sendRedirect(req.getContextPath() + "/forget.jsp");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
