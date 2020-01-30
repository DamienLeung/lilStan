package dfbz.com.controller.article;

import dfbz.com.controller.BaseServlet;
import dfbz.com.pojo.Article;
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

//    public void showArticle(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String pageStr = req.getParameter("page");
//        int page = 1;
//        if (pageStr != null)
//            page = Integer.parseInt(pageStr);
//        List<Map<String, Object>> maps = service.getArticles(page, null);
//        req.getSession().setAttribute("articleList", maps);
//        resp.sendRedirect(req.getContextPath() + "/html/article.jsp");
//    }

    public void showArticle(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pageStr = req.getParameter("page");
        String pattern = null;
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
                req.setAttribute("maxPage", pageSize);
                req.getSession().setAttribute("articleList", articles);

                System.out.println(pattern);
                System.out.println(listSize);
                System.out.println(pageSize);
                System.out.println(startPage);
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

                System.out.println(listSize);
                System.out.println(pageSize);
                System.out.println(startPage);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/html/article.jsp");
    }

    public void postArticle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(req.getContextPath() + "/html/article_add.jsp").forward(req, resp);
    }

    public void sendArticle(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("userId");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        System.out.println("id: " + id);
        if (id != null && title != null && content != null) {
            String name = service.getName(Integer.parseInt(id));
            System.out.println("name: " + name);
            if (name != null) {
                int articleId = service.getId();
                resp.getWriter().write("success");
                Article article = new Article(articleId, title, content, 0,
                        new Timestamp(System.currentTimeMillis()), name, Integer.parseInt(id));
                System.out.println("article: " + articleId);
                service.post(article);
            } else {
                resp.getWriter().write("Post failed");
            }
            //resp.sendRedirect(req.getContextPath() + "/html/article_add.jsp");
        }
    }
}
