package top.okay3r.mybatis.framework.executor.impl;

import top.okay3r.mybatis.framework.config.BoundSql;
import top.okay3r.mybatis.framework.config.Configuration;
import top.okay3r.mybatis.framework.config.MapperStatement;
import top.okay3r.mybatis.framework.config.ParameterMapping;
import top.okay3r.mybatis.framework.handler.ParameterHandler;
import top.okay3r.mybatis.framework.handler.StatementHandler;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2019/12/7
 * Time: 15:00
 * Explain: 默认Executor的实现
 */
public class SimpleExecutor extends BaseExecutor {

    public <E> List<E> queryFromDataBase(Configuration configuration, MapperStatement mapperStatement, Object param) throws
            IllegalAccessException, InstantiationException, NoSuchFieldException, SQLException {
        //获取数据源
        DataSource dataSource = configuration.getDataSource();
        //根据数据源获取连接
        Connection connection = dataSource.getConnection();
        //通过configuration获取该Statement的处理器
        StatementHandler statementHandler = configuration.newStatementHandler(mapperStatement);
        //通过Statement处理器获取Statement
        Statement statement = statementHandler.prepare(connection, param);
        //为statement设置参数
        statementHandler.parameterize(statement, param);
        //通过Statement处理器进行查询，并获取结果集
        List<Object> resultList = statementHandler.query(statement);
        return (List<E>) resultList;
    }

    @Override
    public Integer insert(Configuration configuration, MapperStatement mapperStatement, Object param) throws SQLException, NoSuchFieldException, IllegalAccessException {
        return null;
    }

    @Override
    public Integer updateOrDelete(Configuration configuration, MapperStatement mapperStatement, Object param) throws SQLException, NoSuchFieldException, IllegalAccessException {
        return null;
    }


    /*public Integer insert(Configuration configuration, MapperStatement mapperStatement, Object param) throws SQLException, NoSuchFieldException, IllegalAccessException {
        Connection connection = configuration.getDataSource().getConnection();
        String statementType = mapperStatement.getStatementType();
        Integer res = null;
        if ("prepared".equals(statementType)) {
            PreparedStatement preparedStatement = setParam2Statement(param, mapperStatement, connection);
            int i = preparedStatement.executeUpdate();
            if (i == 0) {
                return null;
            }
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                res = resultSet.getInt(1);
            }
        }
        return res;
    }

    @Override
    public Integer updateOrDelete(Configuration configuration, MapperStatement mapperStatement, Object param) throws SQLException, NoSuchFieldException, IllegalAccessException {
        Connection connection = configuration.getDataSource().getConnection();
        String parameterType = mapperStatement.getStatementType();
        int i = 0;
        if ("prepared".equals(parameterType)) {
            PreparedStatement preparedStatement = setParam2Statement(param, mapperStatement, connection);
            i = preparedStatement.executeUpdate();
        }
        return i == 0 ? null : i;
    }*/

}
