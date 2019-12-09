package test.dao;

import test.pojo.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created By Darius.
 * test.dao.UserDao
 * User: okay3r
 * Date: 2019/12/7
 * Time: 13:59
 * Info:
 */
public interface UserDao {
    User queryUserById(Integer id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

    List<User> queryUserByName(String username) throws SQLException, InstantiationException, IllegalAccessException,
            NoSuchFieldException;
}
