package dfbz.com.service;

import dfbz.com.dao.ArticleDao;
import dfbz.com.pojo.Article;

import java.util.List;
import java.util.Map;

public class ArticleService {
    private ArticleDao dao = new ArticleDao();

    public List<Map<String, Object>> getArticles(int page, String pattern) {
        return dao.getArticles(page, pattern);
    }

    public int getListSize(String pattern) {
        return dao.getArticleNum(pattern);
    }

    public int getId() {
        return dao.getId();
    }

    public String getName(Integer id) {

        Map<String, Object> name = dao.getName(id);
        System.out.println(name);
        if (name.get("realName") != null) {
            return name.get("realName").toString();
        } else
            return name.get("username").toString();
    }

    public void post(Article article) {
        dao.add(article);
    }

}
