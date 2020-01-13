package top.okay3r.mybatis.framework.executor.impl;

import top.okay3r.mybatis.framework.config.Configuration;
import top.okay3r.mybatis.framework.config.MapperStatement;
import top.okay3r.mybatis.framework.executor.Executor;

import java.sql.SQLException;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/1/14
 * Time: 00:04
 * Explain:
 */
public abstract class BaseExecutor implements Executor {
    @Override
    public <T> T query(Configuration configuration, MapperStatement mapperStatement, Object param) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        //先从一级缓存中找，如果没有则从db中查询
        //if...
        //TODO 一级缓存
        return queryFromDataBase(configuration, mapperStatement, param);
    }

    public abstract <T> T queryFromDataBase(Configuration configuration, MapperStatement mapperStatement,
                                             Object param) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException;
}
