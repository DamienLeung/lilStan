package dfbz.com.pojo;

import dfbz.com.annotation.FieldAnnotation;
import dfbz.com.annotation.TableAnnotation;


/**
 *  user
 * @author  2020-01-15
 */

@TableAnnotation("user")
public class User {

    /**
     * id
     */
    private Integer id;

    /**
     * Username
     */
    private String username;

    /**
     * Password
     */
    private String password;

    /**
     * 0：Public (Default)
     */
    @FieldAnnotation("is_secret")
    private String isSecret;

    /**
     * Email Address
     */
    private String email;

    /**
     * Department id
     */
    @FieldAnnotation("dept_id")
    private Integer deptId;

    public User() {
    }

}
