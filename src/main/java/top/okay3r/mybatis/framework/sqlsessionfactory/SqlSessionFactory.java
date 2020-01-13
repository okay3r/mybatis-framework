package top.okay3r.mybatis.framework.sqlsessionfactory;

import top.okay3r.mybatis.framework.sqlsession.SqlSession;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.sqlsession.SqlSessionFactory
 * User: okay3r
 * Date: 2019/12/7
 * Time: 14:01
 * Explain:
 */
public interface SqlSessionFactory {
    SqlSession openSession();
}
