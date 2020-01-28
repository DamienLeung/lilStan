package dfbz.com.controller.article;

import dfbz.com.controller.BaseServlet;
import dfbz.com.service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/article/*")
public class ArticleServlet extends BaseServlet {

    private ArticleService service = new ArticleService();

    public void showArticle(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = req.getSession().getAttribute("userId").toString();
        Integer id = null;
        if (userId != null) {
            id = Integer.parseInt(userId);
            List<Map<String, Object>> maps = service.getArticles(id);
            req.getSession().setAttribute("articleDetail", maps);
        }
        resp.sendRedirect(req.getContextPath() + "/html/article.jsp");
    }
}
