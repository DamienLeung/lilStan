package dfbz.com.controller.articleCollect;

import dfbz.com.controller.BaseServlet;
import dfbz.com.service.ArticleColService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@WebServlet("/articleCol/*")
public class ArticleCollectServlet extends BaseServlet {
    private ArticleColService service = new ArticleColService();

    public void showFavedArticles(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getSession().getAttribute("userId").toString();
        List<Map<String, Object>> favedArticles = service.getFavedArticle(Integer.parseInt(userId));
        req.getSession().setAttribute("favArticles", favedArticles);
    }
}
