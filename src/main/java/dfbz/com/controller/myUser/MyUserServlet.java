package dfbz.com.controller.myUser;

import dfbz.com.controller.BaseServlet;
import dfbz.com.service.MyUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@WebServlet("/myUser/*")
public class MyUserServlet extends BaseServlet {

    MyUserService service = new MyUserService();

    public void searchUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        int page = 1;
        String pageString = req.getParameter("page");
        if (pageString != null)
            if (!pageString.equals(""))
                page = Integer.parseInt(pageString);
        String id = req.getParameter("userId");
        try {
            String userId = req.getSession().getAttribute("userId").toString();
            List<Map<String, Object>> maps = service.getUsers(page, Integer.parseInt(userId));
            int pageSize = service.getListSize(Integer.parseInt(id)) % 5 == 0 ?
                    service.getListSize(Integer.parseInt(id)) / 5 :
                    service.getListSize(Integer.parseInt(id)) / 5 + 1;
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
            req.getRequestDispatcher(req.getContextPath() + "/html/user.jsp").forward(req, resp);
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

}
