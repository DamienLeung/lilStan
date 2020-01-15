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
    @FieldAnnotation("user_id")
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getLook() {
        return look;
    }

    public void setLook(Integer look) {
        this.look = look;
    }

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
