package dfbz.com.controller.article;

import dfbz.com.controller.BaseServlet;
import dfbz.com.service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@WebServlet("/article/*")
public class ArticleServlet extends BaseServlet {

    private ArticleService service = new ArticleService();

    public void showArticle(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pageStr = req.getParameter("page");
        int page = 1;
        if (pageStr != null)
            page = Integer.parseInt(pageStr);
        List<Map<String, Object>> maps = service.getArticles(page, null);
        req.getSession().setAttribute("articleList", maps);
        resp.sendRedirect(req.getContextPath() + "/html/article.jsp");
    }

    public void searchArticle(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pageStr = req.getParameter("page");
        String pattern = null;
        try {
            int page = 1;
            if (pageStr != null)
                page = Integer.parseInt(pageStr);
            byte[] bytes = req.getParameter("pattern").getBytes("ISO-8859-1");
            pattern = new String(bytes, "UTF-8");
            if (!pattern.equals("")) {
                List<Map<String, Object>> articles = service.getArticles(page, pattern);
                req.getSession().setAttribute("articleList", articles);
            } else {
                List<Map<String, Object>> articles = service.getArticles(page, null);
                req.getSession().setAttribute("articleList", articles);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/html/article.jsp");
    }
}
