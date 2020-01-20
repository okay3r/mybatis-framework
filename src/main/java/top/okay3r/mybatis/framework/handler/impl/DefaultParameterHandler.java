package top.okay3r.mybatis.framework.handler.impl;

import top.okay3r.mybatis.framework.config.MapperStatement;
import top.okay3r.mybatis.framework.config.ParameterMapping;
import top.okay3r.mybatis.framework.handler.ParameterHandler;
import top.okay3r.mybatis.framework.utils.SimpleTypeRegistry;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/1/14
 * Time: 22:39
 * Explain: 入参处理器的默认实现
 */
public class DefaultParameterHandler implements ParameterHandler {
    private MapperStatement mapperStatement;

    public DefaultParameterHandler(MapperStatement mapperStatement) {
        this.mapperStatement = mapperStatement;
    }

    //对入参进行处理
    @Override
    public void handleParameter(Statement statement, Object param) {
        //类型转换为PreparedStatement
        PreparedStatement preparedStatement = (PreparedStatement) statement;
        //获取入参类型
        Class parameterTypeClass = mapperStatement.getParameterTypeClass();
        //由connection创建PreparedStatement，Statement.RETURN_GENERATED_KEYS：设置返回主键
        //如果是基本类型，则将参数直接设置到preparedStatement中
        if (SimpleTypeRegistry.isSimpleType(parameterTypeClass)) {
            try {
                preparedStatement.setObject(1, param);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            //获取mapper.xml配置文件中sql语句的参数集合
            List<ParameterMapping> parameterMappingList =
                    mapperStatement.getSqlSource().getBoundSql(param).getParameterMappingList();
            for (int i = 0; i < parameterMappingList.size(); i++) {
                try {
                    ParameterMapping parameterMapping = parameterMappingList.get(i);
                    //获取对应pojo的属性名
                    String name = parameterMapping.getName();
                    //根据属性名获取属性
                    Field field = null;
                    field = parameterTypeClass.getDeclaredField(name);
                    //设置允许访问
                    field.setAccessible(true);
                    //获取传入参数param中对应属性的值
                    Object o = field.get(param);
                    //将参数值设置到preparedStatement中
                    preparedStatement.setObject(i + 1, o);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
