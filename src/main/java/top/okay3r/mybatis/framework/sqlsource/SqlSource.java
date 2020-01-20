package top.okay3r.mybatis.framework.sqlsource;

import top.okay3r.mybatis.framework.config.BoundSql;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/1/12
 * Time: 22:58
 * Explain: 封装sql语句和参数信息
 */
public interface SqlSource {
    BoundSql getBoundSql(Object param);
}
