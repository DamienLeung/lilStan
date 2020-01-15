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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserFocusId() {
        return userFocusId;
    }

    public void setUserFocusId(Integer userFocusId) {
        this.userFocusId = userFocusId;
    }

    /**
     * Fav friend id
     */
    @FieldAnnotation("user_focus_id")
    private Integer userFocusId;

    public UserFocus() {
    }

}
