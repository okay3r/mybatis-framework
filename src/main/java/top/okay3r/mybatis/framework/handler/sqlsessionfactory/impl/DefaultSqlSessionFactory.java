package top.okay3r.mybatis.framework.handler.sqlsessionfactory.impl;

import top.okay3r.mybatis.framework.config.configuration.Configuration;
import top.okay3r.mybatis.framework.handler.sqlsession.impl.DefaultSqlSession;
import top.okay3r.mybatis.framework.handler.sqlsession.SqlSession;
import top.okay3r.mybatis.framework.handler.sqlsessionfactory.SqlSessionFactory;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.sqlsession.DefaultSqlSessionFactory
 * User: okay3r
 * Date: 2019/12/7
 * Time: 14:08
 * Explain:
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
