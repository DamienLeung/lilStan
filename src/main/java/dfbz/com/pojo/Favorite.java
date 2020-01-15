package dfbz.com.pojo;


import dfbz.com.annotation.FieldAnnotation;
import dfbz.com.annotation.TableAnnotation;

/**
 *  favorite
 * @author  2020-01-15
 */

@TableAnnotation("favorite")
public class Favorite {

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
