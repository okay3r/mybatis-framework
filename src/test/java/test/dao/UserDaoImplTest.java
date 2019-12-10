package test.dao;

import org.junit.Before;
import org.junit.Test;
import test.pojo.User;
import top.okay3r.mybatis.sqlsession.SqlSessionFactory;
import top.okay3r.mybatis.sqlsession.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoImplTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        String resource = "sqlMapConfig.xml";
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().initConfiguration(inputStream);
    }

    @Test
    public void testQueryById() throws SQLException, InstantiationException, IllegalAccessException,
            NoSuchFieldException {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        User user = userDao.queryUserById(1);
        System.out.println(user);
    }

    @Test
    public void testQueryUserByName() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        List<User> userList = userDao.queryUserByName("李逵", "女");
        for (User u : userList) {
            System.out.println(u);
        }
    }

    @Test
    public void testInsertUser() throws SQLException, NoSuchFieldException, IllegalAccessException {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        Integer newLine = userDao.insertUser("张飞", new Date(new java.util.Date().getTime()), "男", "北京市");
        System.out.println(newLine);
    }

    @Test
    public void testUpdateUserNameById() throws IllegalAccessException, NoSuchFieldException, SQLException {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        Integer res = userDao.updateUserNameById(9, "吕布");
        System.out.println(res);
    }

    @Test
    public void testDeleteUserById() throws IllegalAccessException, NoSuchFieldException, SQLException {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        Integer res = userDao.deleteUserById(12);
        System.out.println(res);
    }

    @Test
    public void testDeleteUserByNameAndSex() throws IllegalAccessException, NoSuchFieldException, SQLException {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        Integer res = userDao.deleteUserByNameAndSex("李逵", "男");
        System.out.println(res);
    }
}