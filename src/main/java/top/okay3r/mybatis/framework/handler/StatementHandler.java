package top.okay3r.mybatis.framework.handler;

import top.okay3r.mybatis.framework.config.BoundSql;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/1/14
 * Time: 22:03
 * Explain: Statement处理器
 */
public interface StatementHandler {
    Statement prepare(Connection connection, Object param);

    void parameterize(Statement statement, Object param);

    <E> List<E> query(Statement statement);
}
