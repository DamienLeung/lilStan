package dfbz.com.controller.article;

import dfbz.com.controller.BaseServlet;
import dfbz.com.pojo.Article;
import dfbz.com.pojo.Favorite;
import dfbz.com.service.ArticleDetailService;
import dfbz.com.service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/article/*")
public class ArticleServlet extends BaseServlet {

    private ArticleService service = new ArticleService();
    private ArticleDetailService detailService = new ArticleDetailService();

    public void showArticle(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pageStr = req.getParameter("page");
        String pattern;
        try {
            int page = 1;
            if (pageStr != null)
                page = Integer.parseInt(pageStr);
            pattern = req.getParameter("pattern");


            if (pattern != null) {
                byte[] bytes = pattern.getBytes("ISO-8859-1");
                pattern = new String(bytes, "UTF-8");
                List<Map<String, Object>> articles = service.getArticles(page, pattern);
                int listSize = service.getListSize(pattern);
                int pageSize = listSize % 5 == 0 ?
                        listSize / 5 :
                        listSize / 5 + 1;
                int startPage = (page - 1) / 5 * 5 + 1;
                req.setAttribute("startPage", startPage);
                if ((startPage + 4) < pageSize)
                    req.setAttribute("endPage", startPage + 4);
                else
                    req.setAttribute("endPage", pageSize);
                req.setAttribute("pattern", pattern);
                req.setAttribute("maxPage", pageSize);
                req.getSession().setAttribute("articleList", articles);
            } else {
                List<Map<String, Object>> articles = service.getArticles(page, null);
                int listSize = service.getListSize(null);
                int pageSize = listSize % 5 == 0 ?
                        listSize / 5 :
                        listSize / 5 + 1;
                int startPage = (page - 1) / 5 * 5 + 1;
                req.setAttribute("startPage", startPage);
                if ((startPage + 4) < pageSize)
                    req.setAttribute("endPage", startPage + 4);
                else
                    req.setAttribute("endPage", pageSize);
                req.getSession().setAttribute("articleList", articles);
            }
            req.getRequestDispatcher(req.getContextPath() + "/html/article.jsp").forward(req, resp);
        } catch (UnsupportedEncodingException | ServletException e) {
            e.printStackTrace();
        }
    }

    public void postArticle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(req.getContextPath() + "/html/article_add.jsp").forward(req, resp);
    }

    public void sendArticle(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getSession().getAttribute("userId").toString();
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        if (id != null && title != null && content != null) {
            String name = service.getName(Integer.parseInt(id));
            if (name != null) {
                int articleId = service.getId();
                resp.getWriter().write("success");
                Article article = new Article(articleId, title, content, 0,
                        new Timestamp(System.currentTimeMillis()), name, Integer.parseInt(id));
                service.post(article);
            } else {
                resp.getWriter().write("Post failed");
            }
            //resp.sendRedirect(req.getContextPath() + "/html/article_add.jsp");
        }
    }

    public void getArticleDetail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = req.getSession().getAttribute("userId").toString();
        String articleId = req.getParameter("articleId");
        Map<String, Object> articleDetails = detailService.getArticleDetails(Integer.parseInt(articleId));
        req.getSession().setAttribute("articleDetails", articleDetails);
        List<Map<String, Object>> username =
                detailService.getUsersFollowedFavedArticle(Integer.parseInt(articleId), Integer.parseInt(userId));
        req.getSession().setAttribute("usernames", username);
        System.out.println("user id: " + userId);
        System.out.println("article id: " + articleId);
        if (!userId.equals("") || !articleId.equals("")) {
            Integer favId = detailService.getFavId(Integer.parseInt(userId), Integer.parseInt(articleId));
            System.out.println("fav id: " + favId);
            if (favId != null) {
                req.getSession().setAttribute("buttonVal", "取消收藏");
                req.getSession().setAttribute("fId", favId);
            } else {
                favId = detailService.getFavId();
                req.getSession().setAttribute("buttonVal", "收藏");
                req.getSession().setAttribute("fId", favId);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/html/article_detail.jsp");
    }

    public void unfav(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String favoriteId = req.getParameter("fId");
        if (favoriteId != null) {
            detailService.removeFav(Integer.parseInt(favoriteId));
            resp.getWriter().write("success");
        }
    }

    public void fav(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = req.getSession().getAttribute("userId").toString();
        String articleId = req.getParameter("articleId");
        if (articleId != null) {
            int favId = detailService.getFavId();
            Favorite f = new Favorite(favId, Integer.parseInt(userId), Integer.parseInt(articleId));
            System.out.println();
            detailService.saveFav(f);
            resp.getWriter().write("success");
        }
    }
}
