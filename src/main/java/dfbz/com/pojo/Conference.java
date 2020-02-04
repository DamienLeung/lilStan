package dfbz.com.pojo;

import dfbz.com.annotation.FieldAnnotation;
import dfbz.com.annotation.TableAnnotation;

import java.sql.Date;
import java.sql.Time;

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
    private Time startTime;

    /**
     * Conference status 0:Planning 1:Starting 2:Ended
     */
    private Integer status;

    public Conference() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
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

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Conference(Integer id, String deptName, Integer deptId, String title,
                      String content, Date publishDate, Time startTime, Integer status) {
        this.id = id;
        this.deptName = deptName;
        this.deptId = deptId;
        this.title = title;
        this.content = content;
        this.publishDate = publishDate;
        this.startTime = startTime;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Conference{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                ", deptId=" + deptId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publishDate=" + publishDate +
                ", startTime=" + startTime +
                ", status=" + status +
                '}';
    }
}

