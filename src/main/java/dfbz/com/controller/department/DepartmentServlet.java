package dfbz.com.controller.department;

import dfbz.com.controller.BaseServlet;
import dfbz.com.service.DepartmentService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/department/*")
public class DepartmentServlet extends BaseServlet {

    private DepartmentService service = new DepartmentService();

    public void showMembers(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.sendRedirect(req.getContextPath() + "/html/department.jsp");
    }
}
