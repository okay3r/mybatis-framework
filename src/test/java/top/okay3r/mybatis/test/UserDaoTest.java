package top.okay3r.mybatis.test;

import org.junit.Before;
import org.junit.Test;
import top.okay3r.mybatis.dao.UserDao;
import top.okay3r.mybatis.dao.UserDaoImpl;
import top.okay3r.mybatis.framework.builder.ConfigurationBuilder;
import top.okay3r.mybatis.framework.builder.SqlSessionFactoryBuilder;
import top.okay3r.mybatis.framework.config.Configuration;
import top.okay3r.mybatis.framework.sqlsessionfactory.SqlSessionFactory;
import top.okay3r.mybatis.pojo.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoTest {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        String location = "sqlMapConfig.xml";
        Configuration configuration = ConfigurationBuilder.buildConfiguration(location);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    }

    @Test
    public void testQueryById() throws SQLException, InstantiationException, IllegalAccessException,
            NoSuchFieldException {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        User user = userDao.queryUserById(9);
        System.out.println(user);
    }

    @Test
    public void testQueryUserByName() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        List<User> userList = userDao.queryUserByName("李逵");
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
        Integer res = userDao.deleteUserByNameAndSex("张飞", "男");
        System.out.println(res);
    }
}