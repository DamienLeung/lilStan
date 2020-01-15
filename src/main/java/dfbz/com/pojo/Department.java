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

}

