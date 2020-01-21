package dfbz.com.controller.user;

import dfbz.com.controller.BaseServlet;
import dfbz.com.service.UserDetailService;
import dfbz.com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private UserService service = new UserService();

    private UserDetailService detailService = new UserDetailService();

    public void showRegister(HttpServletRequest req, HttpServletResponse resp) {

    }

    public void showUsers(HttpServletRequest req, HttpServletResponse resp, int page) throws IOException, ServletException {
        String userId = req.getSession().getAttribute("userId").toString();
        List<Map<String, Object>> users = service.getUsers(page, null, Integer.parseInt(userId));
        req.setAttribute("currentPage", page);
//        int pageN = 2;
        int pageSize = service.getInfoListSize(null) % 5 == 0 ?
                service.getInfoListSize(null) / 5 : service.getInfoListSize(null) / 5 + 1;
        int startPage = (page - 1) / 5 * 5 + 1;
        req.setAttribute("startPage", startPage);
        if ((startPage + 4) < pageSize)
            req.setAttribute("endPage", startPage + 4);
        else
            req.setAttribute("endPage", pageSize);
        req.setAttribute("currentPage", page);
        req.setAttribute("maxPage", pageSize);
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

    public void searchUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        int page = 1;
        String pageString = req.getParameter("page");
        if (pageString != null)
            if (!pageString.equals(""))
                page = Integer.parseInt(pageString);

        String pattern = null;
        try {
            byte[] bytes = req.getParameter("pattern").getBytes("ISO-8859-1");
            pattern = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            if (pattern == null) {
                resp.sendRedirect("/user/page");
            } else {
                if (pattern.equals("")) {
                    resp.sendRedirect("/user/page");
                    return;
                }
                String userId = req.getSession().getAttribute("userId").toString();
                req.setAttribute("pattern", pattern);
                List<Map<String, Object>> maps = service.getUsers(page, pattern, Integer.parseInt(userId));
                int pageSize = service.getInfoListSize(pattern) % 5 == 0 ?
                        service.getInfoListSize(pattern) / 5 :
                        service.getInfoListSize(pattern) / 5 + 1;
                int startPage = (page - 1) / 5 * 5 + 1;
                req.setAttribute("startPage", startPage);
                if ((startPage + 4) < pageSize)
                    req.setAttribute("endPage", startPage + 4);
                else
                    req.setAttribute("endPage", pageSize);
                System.out.println(maps.size());
                req.setAttribute("currentPage", page);
                req.getSession().setAttribute("userList", maps);
                req.setAttribute("maxPage", pageSize);
            }
            req.getRequestDispatcher(req.getContextPath() + "/html/user.jsp").forward(req, resp);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void follow(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.setHeader("content-Type", "text/html");
            String userId = req.getSession().getAttribute("userId").toString();
            String uId = req.getParameter("uId");
            System.out.println(userId + uId);
            if (uId != null) {
                if (userId.equals(uId))
                    resp.getWriter().write("不能關注自己");
                else {
                    service.follow(Integer.parseInt(uId), Integer.parseInt(userId));
                    resp.getWriter().write("success");
                }
            } else {
                resp.getWriter().write("關注失敗");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unfollow(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String ufId = req.getParameter("ufId");
        if (ufId != null) {
            service.unfollow(Integer.parseInt(ufId));
        } else {
            resp.getWriter().write("請輸入ufId");
        }
    }

    public void getUserDetail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = req.getParameter("id");
        detailService.viewIncretment(Integer.parseInt(userId));
        Map<String, Object> userDetail = detailService.getUserDetail(Integer.parseInt(userId));
        req.getSession().setAttribute("userDetail", userDetail);
        resp.sendRedirect(req.getContextPath() + "/html/user_detail.jsp");
    }
}