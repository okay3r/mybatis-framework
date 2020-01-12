package top.okay3r.mybatis.framework.handler.sqlsession.impl;

import top.okay3r.mybatis.framework.config.configuration.Configuration;
import top.okay3r.mybatis.framework.handler.executor.impl.DefaultExecuteHandler;
import top.okay3r.mybatis.framework.handler.executor.ExecuteHandler;
import top.okay3r.mybatis.framework.handler.sqlsession.SqlSession;

import java.sql.SQLException;
import java.util.List;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.sqlsession.DefaultSqlSession
 * User: okay3r
 * Date: 2019/12/7
 * Time: 14:09
 * Explain:
 */
public class DefaultSqlSession implements SqlSession {
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statementId, Object param) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        List<Object> resultList = selectList(statementId, param);
        if (resultList != null && resultList.size() == 1) {
            return (T) resultList.get(0);
        }
        return null;
    }

    @Override
    public <T> List<T> selectList(String statementId, Object param) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        ExecuteHandler executeHandler = new DefaultExecuteHandler();
        List<T> resultList = executeHandler.query(configuration, statementId, param);
        return resultList;
    }

    @Override
    public Integer insert(String statementId, Object param) throws SQLException, NoSuchFieldException, IllegalAccessException {
        ExecuteHandler executeHandler = new DefaultExecuteHandler();
        Integer res = executeHandler.insert(configuration, statementId, param);
        return res;
    }

    @Override
    public Integer update(String statementId, Object param) throws NoSuchFieldException, IllegalAccessException, SQLException {
        ExecuteHandler executeHandler = new DefaultExecuteHandler();
        Integer updateRows = executeHandler.updateOrDelete(configuration, statementId, param);
        return updateRows;
    }

    @Override
    public Integer delete(String statementId, Object param) throws NoSuchFieldException, IllegalAccessException, SQLException {
        ExecuteHandler executeHandler = new DefaultExecuteHandler();
        Integer updateRows = executeHandler.updateOrDelete(configuration, statementId, param);
        return updateRows;
    }


}
