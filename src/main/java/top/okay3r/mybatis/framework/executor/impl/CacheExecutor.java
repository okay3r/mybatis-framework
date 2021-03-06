package top.okay3r.mybatis.framework.executor.impl;

import top.okay3r.mybatis.framework.config.Configuration;
import top.okay3r.mybatis.framework.config.MapperStatement;
import top.okay3r.mybatis.framework.executor.Executor;

import java.sql.SQLException;
import java.util.List;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/1/14
 * Time: 00:05
 * Explain: 二级缓存Executor
 */
public class CacheExecutor implements Executor {
    private Executor delegate;

    public CacheExecutor(Executor executor) {
        this.delegate = executor;
    }

    @Override
    public <E> List<E> query(Configuration configuration, MapperStatement mapperStatement, Object param) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        // 如果二级缓存中没有则从一级缓存中查找
        // TODO 二级缓存
        return delegate.query(configuration, mapperStatement, param);
    }

    @Override
    public Integer insert(Configuration configuration, MapperStatement mapperStatement, Object param) throws SQLException, NoSuchFieldException, IllegalAccessException {
        return delegate.insert(configuration, mapperStatement, param);
    }

    @Override
    public Integer updateOrDelete(Configuration configuration, MapperStatement mapperStatement, Object param) throws SQLException, NoSuchFieldException, IllegalAccessException {
        return delegate.updateOrDelete(configuration, mapperStatement, param);
    }
}
