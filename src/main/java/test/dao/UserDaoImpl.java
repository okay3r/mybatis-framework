package test.dao;

import test.pojo.User;
import top.okay3r.mybatis.sqlsession.SqlSession;
import top.okay3r.mybatis.sqlsession.SqlSessionFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * Created By okay3r.top
 * test.dao.UserDaoImpl
 * User: okay3r
 * Date: 2019/12/7
 * Time: 14:00
 * Info:
 */
public class UserDaoImpl implements UserDao {

    private SqlSessionFactory sqlSessionFactory;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public User queryUserById(Integer id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("test.queryUserById", id);
        return user;
    }

    @Override
    public List<User> queryUserByName(String username) throws SQLException, InstantiationException,
            IllegalAccessException
            , NoSuchFieldException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> resultList = sqlSession.selectList("test.queryUserByName", username);
        return resultList;
    }
}
