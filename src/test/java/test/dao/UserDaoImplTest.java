package test.dao;

import org.junit.Before;
import org.junit.Test;
import test.pojo.User;
import top.okay3r.mybatis.sqlsession.SqlSessionFactory;
import top.okay3r.mybatis.sqlsession.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.sql.SQLException;

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
    public void queryUserById() throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        User user = userDao.queryUserById(1);
        System.out.println(user);
    }
}