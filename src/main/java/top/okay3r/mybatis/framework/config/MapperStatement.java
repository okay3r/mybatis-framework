package top.okay3r.mybatis.framework.config;

import top.okay3r.mybatis.framework.sqlsource.SqlSource;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2019/12/7
 * Time: 14:40
 * Explain: mapper.xml中每一个Statement标签对应的信息
 */
public class MapperStatement {
    //作为全局唯一标识，使用namespace+id
    private String id;
    //入参类型
    private Class parameterTypeClass;
    //返回值类型
    private Class resultTypeClass;
    //Statement类型
    private String statementType;
    //sql信息
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
