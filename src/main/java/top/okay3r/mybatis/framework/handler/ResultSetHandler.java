package top.okay3r.mybatis.framework.handler;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/1/14
 * Time: 22:58
 * Explain: 返回值处理器
 */
public interface ResultSetHandler {
    <E> List<E> handleResultSet(ResultSet resultSet);
}
