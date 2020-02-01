package dfbz.com.service;

import dfbz.com.dao.ArticleDetailDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleDetailService {

    private ArticleDetailDao dao = new ArticleDetailDao();

    public Map<String, Object> getArticleDetails(Integer id) {

        Map<String, Object> map = dao.getArticleDtails(id);
        map.put("FavCount", getFavCount(id));
        return map;
    }

    public int getFavCount(Integer id) {
        return dao.getFavDetail(id).size();
    }

    public List<Map<String, Object>> getUsersFollowedFavedArticle(Integer articleId, Integer userId) {
        List<Map<String, Object>> userIdFollowed = dao.getUserIdFollowed(userId);
        List<Map<String, Object>> userIdFavedArticle = dao.getFavDetail(articleId);
        List<Map<String, Object>> usernames = new ArrayList<>();
        for (int i = 0; i < userIdFavedArticle.size(); i++)
            for (int i1 = 0; i1 < userIdFollowed.size(); i1++)
                if (userIdFavedArticle.get(i).get("uId") == (userIdFollowed.get(i1).get("id")))
                    usernames.add(userIdFollowed.get(i1));
        return usernames;
    }
}
