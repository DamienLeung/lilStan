package dfbz.com.pojo;

import dfbz.com.annotation.FieldAnnotation;
import dfbz.com.annotation.TableAnnotation;

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
    private Date publishDate;

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

}

