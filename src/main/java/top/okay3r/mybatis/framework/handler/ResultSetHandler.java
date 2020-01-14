package top.okay3r.mybatis.framework.handler;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/1/14
 * Time: 22:58
 * Explain:
 */
public interface ResultSetHandler {
    <E> List<E> handleResultSet(ResultSet resultSet);
}
