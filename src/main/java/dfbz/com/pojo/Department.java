package dfbz.com.pojo;


import dfbz.com.annotation.TableAnnotation;

/**
 *  dept
 * @author  2020-01-15
 */
@TableAnnotation("dept")
public class Department {

    /**
     * Department id
     */
    private Integer id;

    /**
     * Department name
     */
    private String name;

    public Department() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

