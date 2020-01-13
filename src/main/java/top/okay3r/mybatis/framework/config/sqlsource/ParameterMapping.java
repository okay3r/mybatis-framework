package top.okay3r.mybatis.framework.config.sqlsource;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.framework.config.sqlsource.ParameterMapping
 * User: okay3r
 * Date: 2020/1/12
 * Time: 22:55
 * Explain:
 */
public class ParameterMapping {
    private String name;
    private Class<?> type;

    public ParameterMapping(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }
}