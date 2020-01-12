package top.okay3r.mybatis.framework.config.configuration;

import top.okay3r.mybatis.framework.config.sqlsource.SqlSource;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.framework.config.configuration.StatementInfo
 * User: okay3r
 * Date: 2019/12/7
 * Time: 14:40
 * Explain:
 */
public class MapperStatement {
    private String id;
    private Class parameterTypeClass;
    private Class resultTypeClass;
    private String statementType;
    private SqlSource sqlSource;

    public MapperStatement(String id, Class parameterTypeClass, Class resultTypeClass, String statementType, SqlSource sqlSource) {
        this.id = id;
        this.parameterTypeClass = parameterTypeClass;
        this.resultTypeClass = resultTypeClass;
        this.statementType = statementType;
        this.sqlSource = sqlSource;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Class getParameterTypeClass() {
        return parameterTypeClass;
    }

    public void setParameterTypeClass(Class parameterTypeClass) {
        this.parameterTypeClass = parameterTypeClass;
    }

    public Class getResultTypeClass() {
        return resultTypeClass;
    }

    public void setResultTypeClass(Class resultTypeClass) {
        this.resultTypeClass = resultTypeClass;
    }

    public String getStatementType() {
        return statementType;
    }

    public void setStatementType(String statementType) {
        this.statementType = statementType;
    }

    public SqlSource getSqlSource() {
        return sqlSource;
    }

    public void setSqlSource(SqlSource sqlSource) {
        this.sqlSource = sqlSource;
    }
}
