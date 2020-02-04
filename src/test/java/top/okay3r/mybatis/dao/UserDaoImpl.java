package top.okay3r.mybatis.dao;

import top.okay3r.mybatis.framework.sqlsession.SqlSession;
import top.okay3r.mybatis.framework.sqlsessionfactory.SqlSessionFactory;
import top.okay3r.mybatis.pojo.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.dao.UserDaoImpl
 * Author: okay3r
 * Date: 2019/12/7
 * Time: 14:00
 * Explain:
 */
public class UserDaoImpl implements UserDao {

    private SqlSessionFactory sqlSessionFactory;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public User queryUserById(Integer id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("aaa.queryUserById", id);
        return user;
    }

    @Override
    public List<User> queryUserByName(String username) throws SQLException, InstantiationException,
            IllegalAccessException
            , NoSuchFieldException {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<User> resultList = sqlSession.selectList("aaa.queryUserByName", username);
        return resultList;
    }

    @Override
    public Integer insertUser(String username, Date birthday, String sex, String address) throws SQLException,
            NoSuchFieldException,
            IllegalAccessException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setUsername(username);
        user.setBirthday(birthday);
        user.setSex(sex);
        user.setAddress(address);
        Integer id = sqlSession.insert("test.insertUser", user);
        return id;
    }

    @Override
    public Integer updateUserNameById(Integer id, String username) throws IllegalAccessException, NoSuchFieldException, SQLException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        Integer updateRows = sqlSession.update("test.updateUserNameById", user);
        return updateRows;
    }

    @Override
    public Integer deleteUserById(Integer id) throws IllegalAccessException, NoSuchFieldException, SQLException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Integer res = sqlSession.delete("test.deleteUserById", id);
        return res;
    }

    @Override
    public Integer deleteUserByNameAndSex(String username, String sex) throws IllegalAccessException, NoSuchFieldException, SQLException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setUsername(username);
        user.setSex(sex);
        Integer res = sqlSession.delete("test.deleteUserByNameAndSex", user);
        return res;
    }


}
