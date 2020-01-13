package top.okay3r.mybatis.framework.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.framework.config.BoundSql
 * User: okay3r
 * Date: 2020/1/12
 * Time: 22:59
 * Explain:
 */
public class BoundSql {
    private String sqlText;
    private List<ParameterMapping> parameterMappingList = new ArrayList<>();

    public BoundSql(String sqlText, List<ParameterMapping> parameterMappingList) {
        this.sqlText = sqlText;
        this.parameterMappingList = parameterMappingList;
    }

    public void addParameterMappingList(ParameterMapping parameterMapping) {
        this.parameterMappingList.add(parameterMapping);
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }
}
