package top.okay3r.mybatis.sqlsession.impl;

import top.okay3r.mybatis.config.Configuration;
import top.okay3r.mybatis.config.sql.ParameterMapping;
import top.okay3r.mybatis.config.sql.StatementInfo;
import top.okay3r.mybatis.sqlsession.ExecuteHandler;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.sqlsession.impl.DefaultExecuteHandler
 * User: okay3r
 * Date: 2019/12/7
 * Time: 15:00
 * Info:
 */
public class DefaultExecuteHandler implements ExecuteHandler {
    @Override
    public <T> T query(Configuration configuration, String statementId, Object param) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        DataSource dataSource = configuration.getDataSource();
        StatementInfo statementInfo = configuration.getStatementInfoMap().get(statementId);
        Connection connection = dataSource.getConnection();
        String sql = statementInfo.getSqlSource().getBindingSql().getSql();
        Class parameterTypeClass = statementInfo.getParameterTypeClass();
        List resultList = new ArrayList<>();
        if ("prepared".equals(statementInfo.getStatementType())) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            if (parameterTypeClass == Integer.class) {
                preparedStatement.setObject(1, param);
            } else {
                List<ParameterMapping> parameterMappingList = statementInfo.getSqlSource().getBindingSql().getParameterMappingList();
                for (int i = 0; i < parameterMappingList.size(); i++) {
                    ParameterMapping parameterMapping = parameterMappingList.get(i);
                    String name = parameterMapping.getName();
                    Field field = parameterTypeClass.getDeclaredField(name);
                    field.setAccessible(true);
                    Object o = field.get(param);
                    preparedStatement.setObject(i + 1, o);
                }
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            Class resultTypeClass = statementInfo.getResultTypeClass();
            while (resultSet.next()) {
                Object res = resultTypeClass.newInstance();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = metaData.getColumnName(i + 1);
                    Field field = resultTypeClass.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(res, resultSet.getObject(i + 1));
                }
                resultList.add(res);
            }
        }
        return (T) resultList;
    }
}
