package top.okay3r.mybatis.sqlsession;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.sqlsession.SqlSessionFactory
 * User: okay3r
 * Date: 2019/12/7
 * Time: 14:01
 * Info:
 */
public interface SqlSessionFactory {
    SqlSession openSession();
}
