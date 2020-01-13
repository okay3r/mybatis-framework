package top.okay3r.mybatis.framework.sqlsource;

import top.okay3r.mybatis.framework.config.BoundSql;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/1/12
 * Time: 22:58
 * Explain:
 */
public interface SqlSource {
    BoundSql getBoundSql(Object param);
}
