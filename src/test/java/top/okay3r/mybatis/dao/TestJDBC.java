package top.okay3r.mybatis.dao;

import org.junit.Test;

import java.sql.*;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.dao.TestJDBC
 * User: okay3r
 * Date: 2019/12/6
 * Time: 16:38
 * Explain:
 */
public class TestJDBC {
    @Test
    public void test() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kkb?characterEncoding=utf-8",
                "root", "root");
        String sql = "select username from user where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, 1);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("username"));
        }
    }
}
