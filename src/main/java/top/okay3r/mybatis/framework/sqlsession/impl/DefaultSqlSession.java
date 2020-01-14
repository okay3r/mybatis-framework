package top.okay3r.mybatis.framework.sqlsession.impl;

import top.okay3r.mybatis.framework.config.Configuration;
import top.okay3r.mybatis.framework.config.MapperStatement;
import top.okay3r.mybatis.framework.executor.impl.SimpleExecutor;
import top.okay3r.mybatis.framework.executor.Executor;
import top.okay3r.mybatis.framework.sqlsession.SqlSession;

import java.sql.SQLException;
import java.util.List;

/**
 * Created By okay3r.top
 * Author: okay3r
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
        Executor executor = configuration.newExecutor();
        MapperStatement mapperStatement = configuration.getMapperStatementById(statementId);
        List<T> resultList = executor.query(configuration, mapperStatement, param);
        return resultList;
    }

    @Override
    public Integer insert(String statementId, Object param) throws SQLException, NoSuchFieldException, IllegalAccessException {
        Executor executor = configuration.newExecutor();
        MapperStatement mapperStatement = configuration.getMapperStatementById(statementId);
        Integer res = executor.insert(configuration, mapperStatement, param);
        return res;
    }

    @Override
    public Integer update(String statementId, Object param) throws NoSuchFieldException, IllegalAccessException, SQLException {
        Executor executor = configuration.newExecutor();
        MapperStatement mapperStatement = configuration.getMapperStatementById(statementId);
        Integer updateRows = executor.updateOrDelete(configuration, mapperStatement, param);
        return updateRows;
    }

    @Override
    public Integer delete(String statementId, Object param) throws NoSuchFieldException, IllegalAccessException, SQLException {
        Executor executor = configuration.newExecutor();
        MapperStatement mapperStatement = configuration.getMapperStatementById(statementId);
        Integer updateRows = executor.updateOrDelete(configuration, mapperStatement, param);
        return updateRows;
    }


}
