package top.okay3r.mybatis.framework.handler.impl;

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
 * Explain: Statement处理器的默认实现类
 */
public class PreparedStatementHandler implements StatementHandler {

    private MapperStatement mapperStatement;

    private ParameterHandler parameterHandler;

    private ResultSetHandler resultSetHandler;

    public PreparedStatementHandler(Configuration configuration, MapperStatement mapperStatement) {
        this.mapperStatement = mapperStatement;
        //通过configuration创建入参处理器、返回值处理器
        parameterHandler = configuration.newParameterHandler(mapperStatement);
        resultSetHandler = configuration.newResultSetHandler(mapperStatement);
    }

    /***
     * 获取PreparedStatement
     * @param connection
     * @param param
     * @return
     */
    @Override
    public Statement prepare(Connection connection, Object param) {
        Statement statement = null;
        //获取sql
        String sqlText = mapperStatement.getSqlSource().getBoundSql(param).getSqlText();
        try {
            //通过connection获取PreparedStatement
            statement = connection.prepareStatement(sqlText, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement ;
    }

    /***
     * 对入参进行映射
     * @param statement
     * @param param
     */
    @Override
    public void parameterize(Statement statement, Object param) {
        parameterHandler.handleParameter(statement, param);
    }

    /***
     * 从数据库中查询数据，并且对返回值进行处理
     * @param statement
     * @param <E>
     * @return
     */
    @Override
    public <E> List<E> query(Statement statement)  {
        PreparedStatement preparedStatement = (PreparedStatement) statement;
        ResultSet resultSet = null;
        try {
            //创建查询
            resultSet = preparedStatement.executeQuery();
            //对返回值行处理
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSetHandler.handleResultSet(resultSet);
    }
}
