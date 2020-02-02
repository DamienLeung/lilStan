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

    public Integer getUId() {
        return uId;
    }

    public void setUId(Integer uId) {
        this.uId = uId;
    }

    public Integer getAId() {
        return aId;
    }

    public void setAId(Integer aId) {
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

    public Favorite(Integer id, Integer uId, Integer aId) {
        this.id = id;
        this.uId = uId;
        this.aId = aId;
    }
}
