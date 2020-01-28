package dfbz.com.service;

import dfbz.com.dao.ArticleDao;

import java.util.List;
import java.util.Map;

public class ArticleService {
    private ArticleDao dao = new ArticleDao();

    public List<Map<String, Object>> getArticles(Integer id) {
        return dao.getArticles(id);
    }
}
