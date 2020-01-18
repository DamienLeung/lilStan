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
        List<Map<String, Object>> users = service.getUsers(page);
        req.setAttribute("currentPage", page);
//        int pageN = 2;
        int pageSize = service.getInfoListSize() % 5 == 0 ?
                service.getInfoListSize() / 5 : service.getInfoListSize() / 5 + 1;
        int startPage = (page - 1) / 5 * 5 + 1;
        req.setAttribute("startPage", startPage);
        if ((startPage + 4) < pageSize)
            req.setAttribute("endPage", startPage + 4);
        else
            req.setAttribute("endPage", pageSize);
        req.setAttribute("currentPage", page);
        req.getSession().setAttribute("userList", users);
//        resp.sendRedirect(req.getContextPath() + "/html/user.jsp");
        req.getRequestDispatcher(req.getContextPath() + "/html/user.jsp").forward(req, resp);
    }

    public void page(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int pageNum = 1;
        String page = req.getParameter("page");
        if (page != null)
            if (!page.equals(""))
                pageNum = Integer.parseInt(page);
        showUsers(req, resp, pageNum);

    }
}
