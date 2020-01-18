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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsSecret() {
        return isSecret;
    }

    public void setIsSecret(String isSecret) {
        this.isSecret = isSecret;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Integer id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.isSecret = "0";
        this.deptId = null;
    }

    public User(Integer id, String username, String password, String isSecret, String email, Integer deptId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isSecret = isSecret;
        this.email = email;
        this.deptId = deptId;
    }

    public User() {}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isSecret=" + isSecret +
                ", email='" + email + '\'' +
                ", deptId=" + deptId +
                '}';
    }
}
