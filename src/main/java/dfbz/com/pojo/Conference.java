package dfbz.com.pojo;

import dfbz.com.annotation.FieldAnnotation;
import dfbz.com.annotation.TableAnnotation;

import java.util.Date;

/**
 *  conference
 * @author  2020-01-15
 */
@TableAnnotation("conference")
public class Conference{

    /**
     * id
     */
    private Integer id;

    /**
     * Department name
     */
    @FieldAnnotation("dept_name")
    private String deptName;

    /**
     * Department id
     */
    @FieldAnnotation("dept_id")
    private Integer deptId;

    /**
     * Conference title
     */
    private String title;

    /**
     * Conference content
     */
    private String content;

    /**
     * Publish date
     */
    @FieldAnnotation("publish_date")
    private Date publishDate;

    /**
     * Start time
     */
    @FieldAnnotation("start_time")
    private Date startTime;

    /**
     * Conference status 0:Planning 1:Starting 2:Ended
     */
    private Integer status;

    public Conference() {
    }

}

