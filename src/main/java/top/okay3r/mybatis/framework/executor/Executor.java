package top.okay3r.mybatis.framework.executor;

import top.okay3r.mybatis.framework.config.Configuration;
import top.okay3r.mybatis.framework.config.MapperStatement;

import java.sql.SQLException;
import java.util.List;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2019/12/7
 * Time: 15:01
 * Explain:
 */
public interface Executor {
    <E> List<E> query(Configuration configuration, MapperStatement mapperStatement, Object param) throws SQLException,
            IllegalAccessException,
            InstantiationException, NoSuchFieldException;

    Integer insert(Configuration configuration, MapperStatement mapperStatement, Object param) throws SQLException, NoSuchFieldException, IllegalAccessException;

    Integer updateOrDelete(Configuration configuration, MapperStatement mapperStatement, Object param) throws SQLException, NoSuchFieldException, IllegalAccessException;

}
