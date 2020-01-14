package top.okay3r.mybatis.framework.handler.impl;

import top.okay3r.mybatis.framework.config.BoundSql;
import top.okay3r.mybatis.framework.config.Configuration;
import top.okay3r.mybatis.framework.config.MapperStatement;
import top.okay3r.mybatis.framework.handler.ParameterHandler;
import top.okay3r.mybatis.framework.handler.ResultSetHandler;
import top.okay3r.mybatis.framework.handler.StatementHandler;

import java.sql.*;
import java.util.List;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/1/14
 * Time: 22:11
 * Explain:
 */
public class PreparedStatementHandler implements StatementHandler {

    private MapperStatement mapperStatement;

    private ParameterHandler parameterHandler;

    private ResultSetHandler resultSetHandler;

    public PreparedStatementHandler(Configuration configuration, MapperStatement mapperStatement) {
        this.mapperStatement = mapperStatement;
        parameterHandler = configuration.newParameterHandler(mapperStatement);
        resultSetHandler = configuration.newResultSetHandler(mapperStatement);
    }

    @Override
    public Statement prepare(Connection connection, Object param) {
        Statement statement = null;
        String sqlText = mapperStatement.getSqlSource().getBoundSql(param).getSqlText();
        try {
            statement = connection.prepareStatement(sqlText, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    public void parameterize(Statement statement, Object param) {
        parameterHandler.handleParameter(statement, param);
    }

    @Override
    public <E> List<E> query(Statement statement)  {
        PreparedStatement preparedStatement = (PreparedStatement) statement;
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
            return resultSetHandler.handleResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
