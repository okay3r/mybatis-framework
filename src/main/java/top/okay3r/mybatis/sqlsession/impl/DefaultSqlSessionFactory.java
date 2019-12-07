package top.okay3r.mybatis.sqlsession.impl;

import top.okay3r.mybatis.config.Configuration;
import top.okay3r.mybatis.sqlsession.SqlSession;
import top.okay3r.mybatis.sqlsession.SqlSessionFactory;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.sqlsession.impl.DefaultSqlSessionFactory
 * User: okay3r
 * Date: 2019/12/7
 * Time: 14:08
 * Info:
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
