package top.okay3r.mybatis.framework.config.sqlsource.impl;

import top.okay3r.mybatis.framework.config.sqlsource.BoundSql;
import top.okay3r.mybatis.framework.config.sqlsource.ParameterMapping;
import top.okay3r.mybatis.framework.config.sqlsource.SqlSource;

import java.util.List;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.framework.config.sqlsource.impl.StaticSqlSource
 * User: okay3r
 * Date: 2020/1/12
 * Time: 23:06
 * Explain:
 */
public class StaticSqlSource implements SqlSource {
    private String sql;
    private List<ParameterMapping> parameterMappingList;

    public StaticSqlSource(String sql, List<ParameterMapping> parameterMappingList) {
        this.sql = sql;
        this.parameterMappingList = parameterMappingList;
    }

    @Override
    public BoundSql getBoundSql(Object param) {
        return new BoundSql(sql, parameterMappingList);
    }
}
