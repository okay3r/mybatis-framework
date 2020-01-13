package top.okay3r.mybatis.framework.sqlsessionfactory.impl;

import top.okay3r.mybatis.framework.config.Configuration;
import top.okay3r.mybatis.framework.sqlsession.impl.DefaultSqlSession;
import top.okay3r.mybatis.framework.sqlsession.SqlSession;
import top.okay3r.mybatis.framework.sqlsessionfactory.SqlSessionFactory;

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
