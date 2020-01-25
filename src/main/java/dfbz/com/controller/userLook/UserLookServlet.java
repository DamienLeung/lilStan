package dfbz.com.controller.userLook;

import dfbz.com.controller.BaseServlet;
import dfbz.com.service.UserLookService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/userLook/*")
public class UserLookServlet extends BaseServlet {

    private UserLookService service = new UserLookService();

    public void showDetail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getSession().getAttribute("id").toString();
        if (id != null) {
            Map<String, Object> details = service.getUserDetail(Integer.parseInt(id));
            if (details != null)
                req.getSession().setAttribute("userDetail", details);
            else
                System.out.println("ID錯誤！");
            resp.sendRedirect("/html/user_look.jsp");
        }
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) {

    }
}
