package dfbz.com.service;

import dfbz.com.dao.ArticleColDao;

import java.util.List;
import java.util.Map;

public class ArticleColService {
    private ArticleColDao dao = new ArticleColDao();

    public List<Map<String, Object>> getFavedArticle(Integer userId, String pattern, int page) {
        return dao.getFavedArticles(userId, pattern, page);
    }

    public int getListSize(Integer userId, String pattern) {
        return dao.getListSize(userId, pattern);
    }
}
