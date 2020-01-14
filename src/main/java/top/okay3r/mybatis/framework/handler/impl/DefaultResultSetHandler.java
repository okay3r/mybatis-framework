package top.okay3r.mybatis.framework.handler.impl;

import top.okay3r.mybatis.framework.config.MapperStatement;
import top.okay3r.mybatis.framework.handler.ResultSetHandler;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/1/14
 * Time: 23:01
 * Explain:
 */
public class DefaultResultSetHandler implements ResultSetHandler {
    private MapperStatement mapperStatement;

    public DefaultResultSetHandler(MapperStatement mapperStatement) {
        this.mapperStatement = mapperStatement;
    }

    @Override
    public <E> List<E> handleResultSet(ResultSet resultSet) {
        List<Object> resultList = new ArrayList<>();
        Class resultTypeClass = mapperStatement.getResultTypeClass();
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return (List<E>) resultList;
    }
}
