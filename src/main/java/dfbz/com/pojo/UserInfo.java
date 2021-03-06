package dfbz.com.pojo;

import dfbz.com.annotation.FieldAnnotation;
import dfbz.com.annotation.TableAnnotation;

import java.sql.Timestamp;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
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
    private String gender;

    /**
     * Description
     */
    private String desc;

    /**
     * Register time
     */
    @FieldAnnotation("register_time")
    private Timestamp registerTime;

    /**
     * Last login time
     */
    @FieldAnnotation("login_time")
    private Timestamp loginTime;

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

    public UserInfo(Integer userId, String realName, Integer age, String phone, String gender, String desc,
                    Timestamp registerTime, Timestamp loginTime, String pic, Integer look) {
        this.userId = userId;
        this.realName = realName;
        this.age = age;
        this.phone = phone;
        this.gender = gender;
        this.desc = desc;
        this.registerTime = registerTime;
        this.loginTime = loginTime;
        this.pic = pic;
        this.look = look;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", realName='" + realName + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", gender=" + gender +
                ", desc='" + desc + '\'' +
                ", registerTime=" + registerTime +
                ", loginTime=" + loginTime +
                ", pic='" + pic + '\'' +
                ", look=" + look +
                '}';
    }
}
