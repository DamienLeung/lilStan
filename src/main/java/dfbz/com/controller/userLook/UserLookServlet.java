package dfbz.com.controller.userLook;

import dfbz.com.controller.BaseServlet;
import dfbz.com.pojo.User;
import dfbz.com.pojo.UserInfo;
import dfbz.com.service.DepartmentService;
import dfbz.com.service.UserLookService;
import dfbz.com.util.StrUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

@WebServlet("/userLook/*")
public class UserLookServlet extends BaseServlet {

    private UserLookService service = new UserLookService();

    private DepartmentService deptService = new DepartmentService();

    public void showDetail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getSession().getAttribute("userId").toString();
        if (id != null) {
            Map<String, Object> details = service.getUserDetail(Integer.parseInt(id));
            List<Map<String, Object>> departments = deptService.getDepartments();
            if (details != null)
                req.getSession().setAttribute("userDetail", details);
            else
                System.out.println("ID錯誤！");
            req.getSession().setAttribute("departments", departments);

            resp.sendRedirect("/html/user_look.jsp");
        }
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) {

        Map<String, String[]> parameterMap = req.getParameterMap();
        User user;
        try {
            user = new User(Integer.parseInt(req.getSession().getAttribute("userId").toString()),
                    null, null, parameterMap.get("isSecret")[0], null,
                    Integer.parseInt(parameterMap.get("deptName")[0]));
        } catch (NumberFormatException e) {
            user = new User(Integer.parseInt(req.getSession().getAttribute("userId").toString()),
                    null, null, parameterMap.get("isSecret")[0], null,
                    null);
        }

        UserInfo info;
        System.out.println(parameterMap.get("gender")[0]);
        info = new UserInfo(Integer.parseInt(req.getSession().getAttribute("userId").toString()),
                parameterMap.get("realName")[0],
                Integer.parseInt(parameterMap.get("age")[0]),
                parameterMap.get("phone")[0],
                parameterMap.get("gender")[0],
                null, null,
                null, null, null);

        service.updateDetail(user, info);


    }

    public void upload(HttpServletRequest req, HttpServletResponse resp) {
        ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
        servletFileUpload.setFileSizeMax(1024 * 1024 * 20L);
        try {
            FileItem fileItem = servletFileUpload.parseRequest(req).get(0);
            String[] split = fileItem.getName().split("\\.");
            if (split.length == 2) {
                String fileName = StrUtil.getUUID() + "." + split[1];
                String filePath = "I:\\IdeaProjects\\JavaWeb\\img\\" + fileName;
                File file = new File(filePath);
                if (!file.exists())
                    fileItem.write(file);
                String userId = req.getSession().getAttribute("userId").toString();
                String path = "\\img\\" + fileName;
                service.uploadImg(Integer.parseInt(userId), path);
                Map<String, Object> userDetail = service.getUserDetail(Integer.parseInt(userId));
                req.getSession().setAttribute("userDetail", userDetail);
                resp.getWriter().write(path);
            } else {
                resp.getWriter().write("上傳失敗");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
