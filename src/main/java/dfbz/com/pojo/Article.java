package dfbz.com.pojo;

import dfbz.com.annotation.FieldAnnotation;
import dfbz.com.annotation.TableAnnotation;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Article
 *
 * @author 2020-01-15
 */

@TableAnnotation("article")
public class Article {

    /**
     * Article id
     */
    private Integer id;

    /**
     * Article title
     */
    private String title;

    /**
     * Article content
     */
    private String content;

    /**
     * Article views
     */
    @FieldAnnotation("browse_count")
    private Integer browseCount;

    /**
     * Publish date
     */
    @FieldAnnotation("publish_date")
    private Timestamp publishDate;

    /**
     * Author
     */
    @FieldAnnotation("publish_username")
    private String publishUsername;

    /**
     * User id
     */
    @FieldAnnotation("user_id")
    private Integer userId;

    public Article() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getBrowseCount() {
        return browseCount;
    }

    public void setBrowseCount(Integer browseCount) {
        this.browseCount = browseCount;
    }

    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    public String getPublishUsername() {
        return publishUsername;
    }

    public void setPublishUsername(String publishUsername) {
        this.publishUsername = publishUsername;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Article(Integer id, String title, String content, Integer browseCount, Timestamp publishDate, String publishUsername, Integer userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.browseCount = browseCount;
        this.publishDate = publishDate;
        this.publishUsername = publishUsername;
        this.userId = userId;
    }
}

