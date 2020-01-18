package dfbz.com.controller.user;

import dfbz.com.controller.BaseServlet;
import dfbz.com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private UserService service = new UserService();

    public void showRegister(HttpServletRequest req, HttpServletResponse resp) {

    }

    public void showUsers(HttpServletRequest req, HttpServletResponse resp, int page) throws IOException, ServletException {
//        List<Map<String, Object>> users = service.getUsers(page);
        int pageN = 1;
//        int listSize = service.getInfoListSize();
//        int endPage = (listSize % 5 == 0) ? (listSize / 5) : (listSize / 5 + 1);
//        req.setAttribute("endPage", endPage);
        List<Map<String, Object>> users = service.getUsers(pageN);
        req.getSession().setAttribute("userList", users);
        resp.sendRedirect(req.getContextPath() + "/html/user.jsp");
//        req.getRequestDispatcher(req.getContextPath() + "/html/user.jsp").forward(req, resp);
    }

    public void page(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int pageNum = 1;
        String page = req.getParameter("page");
        if (page != null)
            pageNum = Integer.parseInt(page);
        showUsers(req, resp, pageNum);

    }
}
