package dfbz.com.pojo;

import dfbz.com.annotation.FieldAnnotation;
import dfbz.com.annotation.TableAnnotation;

import java.util.Date;

/**
 *  user_focus
 * @author  2020-01-15
 */
@TableAnnotation("user_focus")
public class UserFocus {

    /**
     * User focus id
     */
    private Integer id;

    /**
     * User id
     */
    @FieldAnnotation("user_id")
    private Integer userId;

    /**
     * Fav friend id
     */
    @FieldAnnotation("user_focus_id")
    private Integer userFocusId;

    public UserFocus() {
    }

}
