package top.okay3r.mybatis.framework.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.framework.config.DynamicContext
 * User: okay3r
 * Date: 2020/1/12
 * Time: 23:10
 * Explain:
 */
public class DynamicContext {
    private StringBuffer sb = new StringBuffer();
    private Map<String, Object> bindings = new HashMap<>();

    public DynamicContext(Object param) {
        bindings.put("_parameter", param);
    }

    public void appendSql(String sqlText) {
        this.sb.append(sqlText);
        this.sb.append(" ");
    }

    public String getSql() {
        return sb.toString();
    }

    public Map<String, Object> getBindings() {
        return bindings;
    }

    public void putBindingParam(String name, Object param) {
        this.bindings.put(name, param);
    }
}
