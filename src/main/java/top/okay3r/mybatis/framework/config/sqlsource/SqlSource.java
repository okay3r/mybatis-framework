package top.okay3r.mybatis.framework.config.sqlsource;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.framework.config.sqlsource.SqlSource
 * User: okay3r
 * Date: 2020/1/12
 * Time: 22:58
 * Explain:
 */
public interface SqlSource {
    BoundSql getBoundSql(Object param);
}
