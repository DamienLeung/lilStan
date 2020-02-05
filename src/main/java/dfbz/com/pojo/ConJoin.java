package dfbz.com.pojo;

import dfbz.com.annotation.FieldAnnotation;
import dfbz.com.annotation.TableAnnotation;

/**
 *  con_join
 * @author  2020-01-15
 */
@TableAnnotation("con_join")
public class ConJoin {

    /**
     * id
     */
    private Integer id;

    /**
     * Partition id
     */
    @FieldAnnotation("u_id")
    private Integer uId;

    /**
     * Conference id
     */
    @FieldAnnotation("c_id")
    private Integer cId;

    /**
     * Status: 0:Not joining 1:Joined
     */
    private Integer status;

    public ConJoin() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUId() {
        return uId;
    }

    public void setUId(Integer uId) {
        this.uId = uId;
    }

    public Integer getCId() {
        return cId;
    }

    public void setCId(Integer cId) {
        this.cId = cId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ConJoin(Integer id, Integer uId, Integer cId, Integer status) {
        this.id = id;
        this.uId = uId;
        this.cId = cId;
        this.status = status;
    }
}

