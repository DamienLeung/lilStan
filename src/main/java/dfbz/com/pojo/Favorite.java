package dfbz.com.pojo;


import dfbz.com.annotation.FieldAnnotation;
import dfbz.com.annotation.TableAnnotation;

/**
 *  favorite
 * @author  2020-01-15
 */

@TableAnnotation("favorite")
public class Favorite {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    /**
     * id
     */
    private Integer id;

    /**
     * Fav user id
     */
    @FieldAnnotation("u_id")
    private Integer uId;

    /**
     * Article id
     */
    @FieldAnnotation("a_id")
    private Integer aId;

    public Favorite() {
    }

}
