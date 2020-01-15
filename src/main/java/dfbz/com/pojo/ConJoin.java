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

}

