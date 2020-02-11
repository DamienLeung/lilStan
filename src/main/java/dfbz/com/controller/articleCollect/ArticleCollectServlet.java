package dfbz.com.controller.articleCollect;

import dfbz.com.controller.BaseServlet;
import dfbz.com.service.ArticleColService;
import dfbz.com.service.ArticleDetailService;
import dfbz.com.service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/articleCol/*")
public class ArticleCollectServlet extends BaseServlet {
    private ArticleColService service = new ArticleColService();
    private ArticleDetailService detailService = new ArticleDetailService();

    public void showFavedArticles(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = req.getSession().getAttribute("userId").toString();
        String pattern = req.getParameter("pattern");
        String page = req.getParameter("page");
        if (pattern != null) {
            byte[] bytes = pattern.getBytes("ISO-8859-1");
            pattern = new String(bytes, "UTF-8");
            req.setAttribute("pattern", pattern);
        }
        if (page != null) {
            List<Map<String, Object>> favedArticles = service.getFavedArticle(
                    Integer.parseInt(userId), pattern, Integer.parseInt(page));
            req.getSession().setAttribute("favArticles", favedArticles);
            int listSize = service.getListSize(Integer.parseInt(userId), pattern);
            int pageSize = listSize % 5 == 0 ?
                    listSize / 5 :
                    listSize / 5 + 1;
            int startPage = (Integer.parseInt(page) - 1) / 5 * 5 + 1;
            req.setAttribute("startPage", startPage);
            if ((startPage + 4) < pageSize)
                req.setAttribute("endPage", startPage + 4);
            else
                req.setAttribute("endPage", pageSize);
            req.setAttribute("maxPage", pageSize);
        } else {
            List<Map<String, Object>> favedArticles = service.getFavedArticle(
                    Integer.parseInt(userId), pattern, 1);
            int listSize = service.getListSize(Integer.parseInt(userId), pattern);
            req.getSession().setAttribute("favArticles", favedArticles);
            int pageSize = listSize % 5 == 0 ?
                    listSize / 5 :
                    listSize / 5 + 1;
            req.setAttribute("startPage", 1);
            if ((1 + 4) < pageSize)
                req.setAttribute("endPage", 1 + 4);
            else
                req.setAttribute("endPage", pageSize);
            req.setAttribute("maxPage", pageSize);
        }
        try {
            req.getRequestDispatcher("/html/article_collect.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    public void unfav(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fId = req.getParameter("fId");
        detailService.removeFav(Integer.parseInt(fId));
        resp.getWriter().write("success");
    }
}
