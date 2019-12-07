package top.okay3r.mybatis.config.sql;

import java.util.List;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.config.sql.BindingSql
 * User: okay3r
 * Date: 2019/12/7
 * Time: 14:41
 * Info:
 */
public class BindingSql {
    private String sql;
    private List<ParameterMapping> parameterMappingList;

    public BindingSql(String sql, List<ParameterMapping> parameterMappingList) {
        this.sql = sql;
        this.parameterMappingList = parameterMappingList;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }
}
