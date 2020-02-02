package dfbz.com.service;

import dfbz.com.dao.ArticleDetailDao;
import dfbz.com.dao.base.BaseDao;
import dfbz.com.pojo.Favorite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleDetailService {

    private ArticleDetailDao dao = new ArticleDetailDao();

    public Map<String, Object> getArticleDetails(Integer articleId) {
        Map<String, Object> map = dao.getArticleDtails(articleId);
        map.put("FavCount", getFavCount(articleId));
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

    public Integer getFavId(Integer userId, Integer articleId) {
        return dao.getFavId(userId, articleId);
    }

    public void removeFav(Integer fId) {
        new BaseDao<Favorite>().delById(fId, Favorite.class);
    }

    public int getFavId() {
        if (dao.getFavId() != null)
            return dao.getFavId() + 1;
        else
            return 1;
    }

    public void saveFav(Favorite f) {
        new BaseDao<Favorite>().add(f);
    }
}
