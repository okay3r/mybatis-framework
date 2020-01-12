package top.okay3r.mybatis.framework.handler.executor;

import top.okay3r.mybatis.framework.config.configuration.Configuration;

import java.sql.SQLException;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.sqlsession.ExecuteHandler
 * User: okay3r
 * Date: 2019/12/7
 * Time: 15:01
 * Explain:
 */
public interface ExecuteHandler {
    <T> T query(Configuration configuration, String statementId, Object param) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException;

    Integer insert(Configuration configuration, String statementId, Object param) throws SQLException, NoSuchFieldException, IllegalAccessException;

    Integer updateOrDelete(Configuration configuration, String statementId, Object param) throws SQLException, NoSuchFieldException, IllegalAccessException;

}
