package top.okay3r.mybatis.dao;

import top.okay3r.mybatis.pojo.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.dao.UserDao
 * User: okay3r
 * Date: 2019/12/7
 * Time: 13:59
 * Explain:
 */
public interface UserDao {
    User queryUserById(Integer id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

    List<User> queryUserByName(String username,String sex) throws SQLException, InstantiationException,
            IllegalAccessException,
            NoSuchFieldException;

    Integer insertUser(String username, Date date, String sex, String address) throws SQLException, NoSuchFieldException, IllegalAccessException;

    Integer updateUserNameById(Integer id,String username) throws IllegalAccessException, NoSuchFieldException,
            SQLException;

    Integer deleteUserById(Integer id) throws IllegalAccessException, NoSuchFieldException, SQLException;

    Integer deleteUserByNameAndSex(String username, String sex) throws IllegalAccessException, NoSuchFieldException, SQLException;
}
