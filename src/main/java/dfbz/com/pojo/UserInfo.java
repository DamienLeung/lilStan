package dfbz.com.pojo;

import dfbz.com.annotation.FieldAnnotation;
import dfbz.com.annotation.TableAnnotation;

import java.util.Date;

/**
 * user_info
 * @author  2020-01-15
 */
@TableAnnotation("user_info")
public class UserInfo {

    /**
     * User id
     */
    private Integer userId;

    /**
     * Real name of user
     */
    @FieldAnnotation("real_name")
    private String realName;

    /**
     * Age
     */
    private Integer age;

    /**
     * Phone
     */
    private String phone;

    /**
     * Gender:‘0’-M  ‘1’-F
     */
    private char gender;

    /**
     * Description
     */
    private String desc;

    /**
     * Register time
     */
    @FieldAnnotation("register_time")
    private Date registerTime;

    /**
     * Last login time
     */
    @FieldAnnotation("login_time")
    private Date loginTime;

    /**
     * Avatar address
     */
    private String pic;

    /**
     * Times been viewed
     */
    private Integer look;

    public UserInfo() {
    }

}
