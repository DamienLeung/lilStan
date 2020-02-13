import dfbz.com.annotation.TableAnnotation;
import dfbz.com.dao.ArticleColDao;
import dfbz.com.dao.ArticleDao;
import dfbz.com.dao.ArticleDetailDao;
import dfbz.com.pojo.*;
import dfbz.com.service.*;
import dfbz.com.util.JDBCUtil;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class test01 {

    @Test
    public void mappingName() {
        String s = "registerTime";
        StringBuilder name = new StringBuilder(s);
        char[] chars = s.toCharArray();
        int i = 0;
        while (i < chars.length) {
            if (Character.isUpperCase(chars[i])) {
                name.insert(name.indexOf(String.valueOf(chars[i])), "_");
                name.setCharAt(name.indexOf(String.valueOf(chars[i])), Character.toLowerCase(chars[i]));

            }
            i ++;
        }
        System.out.println(chars);
        System.out.println(name.toString());
    }

    @Test
    public void getUserDetail() {
        UserDetailService detailService = new UserDetailService();
        Integer userId = 1;
        Map<String, Object> userDetail = detailService.getUserDetail(userId);
        detailService.viewIncretment(userId);
        System.out.println(userDetail);
    }

    @Test
    public void getUsers() {
        StringBuilder sql = new StringBuilder();
        sql.append("select u.id, u.username, ui.real_name as realName ");
        sql.append("from user u ");
        sql.append("join user_info ui on u.id = ui.user_id ");
        sql.append("join user_focus uf on ui.user_id = uf.user_focus_id ");
        sql.append("and uf.user_id = ? ");
        sql.append("order by u.id");
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            List<Map<String, Object>> maps =  runner.query(sql.toString(), new MapListHandler(), 1);
            System.out.println(maps);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getDetails() {
        Integer id = 2;
        StringBuilder sql = new StringBuilder();
        sql.append("select u.username, u.is_secret as isSecret, u.email, d.name as deptName" +
                ", ui.real_name as realName, ui.age, ui.phone, ui.gender, ui.register_time as regTime " +
                ", ui.login_time as loginTime, ui.pic, ui.look, ");
        sql.append("(select count(*) from user_focus where user_focus_id = ui.user_id) as fansCount ");
        String tableName = UserInfo.class.getAnnotation(TableAnnotation.class).value();
        sql.append("from ").append(tableName).append(" ui ");
        tableName = User.class.getAnnotation(TableAnnotation.class).value();
        sql.append("left join ").append(tableName).append(" u ");
        sql.append("on u.id = ui.user_id ");
        tableName = Department.class.getAnnotation(TableAnnotation.class).value();
        sql.append("left join ").append(tableName).append(" d ");
        sql.append("on d.id = u.dept_id ");
        sql.append("where u.id = ?");
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            Map<String, Object> query = runner.query(sql.toString(), new MapHandler(), id);
            System.out.println(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getArticles() {
        int page = 2;
        ArticleDao dao = new ArticleDao();
        List<Map<String, Object>> articles = dao.getArticles(page, null);
        System.out.println(articles);
    }

    @Test
    public void getArticleId() {
        ArticleService service = new ArticleService();
        System.out.println(service.getId());
    }

    @Test
    public void postArticle() {
        Article article = new Article(16, "aaa", "aaa", 120,
                new Timestamp(System.currentTimeMillis()), "admin", 1);
        ArticleService service = new ArticleService();
        service.post(article);
    }

    @Test
    public void getAuthorName() {
        ArticleService service = new ArticleService();
        String name = service.getName(1);
        System.out.println(name);
    }

    @Test
    public void getMyUserNum() {
        MyUserService service = new MyUserService();
        int listSize = service.getListSize(1);
        System.out.println(listSize);
    }

    @Test
    public void getUsersFavedArticle() {
        ArticleDetailDao dao = new ArticleDetailDao();
        List<Map<String, Object>> userIdFollowed = dao.getUserIdFollowed(1);
        List<Map<String, Object>> userIdFavedArticle = dao.getFavDetail(1);
        List<Map<String, Object>> usernames = new ArrayList<>();
        for (Map<String, Object> objectMap : userIdFavedArticle) {
            for (Map<String, Object> stringObjectMap : userIdFollowed) {
                if (objectMap.get("uId") == (stringObjectMap.get("id"))) {
                    usernames.add(stringObjectMap);
                }
            }
        }
        System.out.println(usernames);
    }

    @Test
    public void getArticleDetail() {
        ArticleDetailDao dao = new ArticleDetailDao();
        Map<String, Object> map = dao.getArticleDtails(1);
        map.put("FavCount", dao.getFavDetail(1).size());
        System.out.println(map);
    }

    @Test
    public void getFavId() {
        ArticleDetailService service = new ArticleDetailService();
        int favId = service.getFavId();
        System.out.println(favId);
    }

    @Test
    public void getFavedArticles() {
        ArticleColDao dao = new ArticleColDao();
        List<Map<String, Object>> favedArticles = dao.getFavedArticles(1, null, 1);
        System.out.println(favedArticles);
    }

    @Test
    public void getFavArticleListSize() {
        ArticleColDao dao = new ArticleColDao();
        int listSize = dao.getListSize(1, null);
        System.out.println(listSize);
    }

    @Test
    public void getDepartment() {
        DepartmentService service = new DepartmentService();
        List<Map<String, Object>> departments = service.getDepartments();
        System.out.println(departments);
    }

    @Test
    public void getMembers() {
        DepartmentService service = new DepartmentService();
        List<Map<String, Object>> members = service.getMembers(5);
        System.out.println(members);
    }

    @Test
    public void getConferences() {
        MeetingService service = new MeetingService();
        List<Map<String, Object>> conferences = service.getConferences(null, null, 1);
        System.out.println(conferences);
    }

    @Test
    public void getConference() throws SQLException {
        ResultSetHandler<Conference> h = new BeanHandler<>(Conference.class,
                new BasicRowProcessor(new GenerousBeanProcessor()));
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        Conference query = runner.query("select * from conference where id = ?", h, 1);
        System.out.println(query);
    }

    @Test
    public void getConferenceId() {
        MeetingService service = new MeetingService();
        int conferenceId = service.getConferenceId();
        System.out.println(conferenceId);
    }

    @Test
    public void getConJoinId() {
        MeetingService service = new MeetingService();
        int conJoinId = service.getConJoinId(2, 1);
        System.out.println(conJoinId);
    }

    @Test
    public void addConJoin() {
        MeetingService service = new MeetingService();
        service.attend(new ConJoin(1,1,1,1));
    }

    @Test
    public void getSize() {
        MeetingService service = new MeetingService();
        List<Map<String, Object>> first = service.getConferences(null, null, 2);
        System.out.println(first);
    }

    @Test
    public void getConJoinNum() {
        MeetingService service = new MeetingService();
        int joinNumber = service.getJoinNumber(3);
        System.out.println(joinNumber);
    }
}
