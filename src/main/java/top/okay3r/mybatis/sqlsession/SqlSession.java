package top.okay3r.mybatis.sqlsession;

import test.pojo.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created By Darius.
 * top.okay3r.mybatis.sqlsession.SqlSession
 * User: okay3r
 * Date: 2019/12/7
 * Time: 14:02
 * Info:
 */
public interface SqlSession {
    <T> T selectOne(String statementId,Object param) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException;

    <T> List<T> selectList(String statementId, Object param) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

    Integer insert(String statementId, Object param) throws SQLException, NoSuchFieldException, IllegalAccessException;

    Integer update(String statementId, Object param) throws NoSuchFieldException, IllegalAccessException, SQLException;

    Integer delete(String statementId, Object param) throws NoSuchFieldException, IllegalAccessException,
            SQLException;
}
