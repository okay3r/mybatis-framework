package top.okay3r.mybatis.config.sql;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.config.sql.ParameterMapping
 * User: okay3r
 * Date: 2019/12/7
 * Time: 14:52
 * Info:
 */
public class ParameterMapping {
    private String name;

    public ParameterMapping(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
