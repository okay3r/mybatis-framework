package top.okay3r.mybatis.framework.handler.executor.impl;

import top.okay3r.mybatis.framework.config.configuration.Configuration;
import top.okay3r.mybatis.framework.config.configuration.MapperStatement;
import top.okay3r.mybatis.framework.config.sqlsource.ParameterMapping;
import top.okay3r.mybatis.framework.handler.executor.ExecuteHandler;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.sqlsession.DefaultExecuteHandler
 * User: okay3r
 * Date: 2019/12/7
 * Time: 15:00
 * Explain:
 */
public class DefaultExecuteHandler implements ExecuteHandler {
    @Override
    public <T> T query(Configuration configuration, String statementId, Object param) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        //获取数据源
        DataSource dataSource = configuration.getDataSource();
        //根据statementId获取对应的statementInfo
        MapperStatement mapperStatement = configuration.getStatementInfoMap().get(statementId);
        //根据数据源获取连接
        Connection connection = dataSource.getConnection();
        //创建集合，用于保存查询出来的结果
        List resultList = new ArrayList<>();
        if ("prepared".equals(mapperStatement.getStatementType())) {
            //初始化PreparedStatement
            PreparedStatement preparedStatement = initPreparedStatement(param, mapperStatement, connection);
            //对数据库进行查询
            ResultSet resultSet = preparedStatement.executeQuery();
            Class resultTypeClass = mapperStatement.getResultTypeClass();
            //遍历结果集
            while (resultSet.next()) {
                //创建结果实例
                Object res = resultTypeClass.newInstance();
                ResultSetMetaData metaData = resultSet.getMetaData();
                //获取结果的列数
                int columnCount = metaData.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    //将值设置到结果对象中
                    String columnName = metaData.getColumnName(i + 1);
                    Field field = resultTypeClass.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(res, resultSet.getObject(i + 1));
                }
                //将结果对象添加到结果集合
                resultList.add(res);
            }
        }
        return (T) resultList;
    }


    public Integer insert(Configuration configuration, String statementId, Object param) throws SQLException, NoSuchFieldException, IllegalAccessException {
        MapperStatement mapperStatement = configuration.getStatementInfoMap().get(statementId);
        Connection connection = configuration.getDataSource().getConnection();
        String statementType = mapperStatement.getStatementType();
        Integer res = null;
        if ("prepared".equals(statementType)) {
            PreparedStatement preparedStatement = initPreparedStatement(param, mapperStatement, connection);
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
    public Integer updateOrDelete(Configuration configuration, String statementId, Object param) throws SQLException, NoSuchFieldException, IllegalAccessException {
        Connection connection = configuration.getDataSource().getConnection();
        MapperStatement mapperStatement = configuration.getStatementInfoMap().get(statementId);
        String parameterType = mapperStatement.getStatementType();
        int i = 0;
        if ("prepared".equals(parameterType)) {
            PreparedStatement preparedStatement = initPreparedStatement(param, mapperStatement, connection);
            i = preparedStatement.executeUpdate();
        }
        return i == 0 ? null : i;
    }

    /***
     *
     * @param param sql语句的参数
     * @param mapperStatement statement信息
     * @param connection 连接
     * @return
     * @throws SQLException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public PreparedStatement initPreparedStatement(Object param, MapperStatement mapperStatement, Connection connection) throws SQLException, NoSuchFieldException, IllegalAccessException {
        //获取处理后的sql语句，即？格式的sql
        String sql = mapperStatement.getSqlSource().getBoundSql(param).getSqlText();
        //获取参数类型
        Class parameterTypeClass = mapperStatement.getParameterTypeClass();
        //由connection创建PreparedStatement，Statement.RETURN_GENERATED_KEYS：设置返回主键
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        //如果是Integer类型或String类型，则将参数直接设置到preparedStatement中
        if (parameterTypeClass == Integer.class || parameterTypeClass == String.class) {
            preparedStatement.setObject(1, param);
        } else {
            //获取mapper.xml配置文件中sql语句的参数集合
            List<ParameterMapping> parameterMappingList =
                    mapperStatement.getSqlSource().getBoundSql(param).getParameterMappingList();
            for (int i = 0; i < parameterMappingList.size(); i++) {
                ParameterMapping parameterMapping = parameterMappingList.get(i);
                //获取对应pojo的属性名
                String name = parameterMapping.getName();
                //根据属性名获取属性
                Field field = parameterTypeClass.getDeclaredField(name);
                //设置允许访问
                field.setAccessible(true);
                //获取传入参数param中对应属性的值
                Object o = field.get(param);
                //将参数值设置到preparedStatement中
                preparedStatement.setObject(i + 1, o);
            }
        }
        return preparedStatement;
    }

}
