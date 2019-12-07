package top.okay3r.mybatis.sqlsession.impl;

import top.okay3r.mybatis.config.Configuration;
import top.okay3r.mybatis.config.sql.StatementInfo;
import top.okay3r.mybatis.sqlsession.ExecuteHandler;
import top.okay3r.mybatis.sqlsession.SqlSession;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.sqlsession.impl.DefaultSqlSession
 * User: okay3r
 * Date: 2019/12/7
 * Time: 14:09
 * Info:
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
}
