package top.okay3r.mybatis.sqlsession;

import top.okay3r.mybatis.config.Configuration;

import java.sql.SQLException;

/**
 * Created By Darius.
 * top.okay3r.mybatis.sqlsession.ExecuteHandler
 * User: okay3r
 * Date: 2019/12/7
 * Time: 15:01
 * Info:
 */
public interface ExecuteHandler {
    <T> T query(Configuration configuration, String statementId, Object param) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException;
}
