package top.okay3r.mybatis.framework.config;

import top.okay3r.mybatis.framework.executor.Executor;
import top.okay3r.mybatis.framework.executor.impl.CacheExecutor;
import top.okay3r.mybatis.framework.executor.impl.SimpleExecutor;
import top.okay3r.mybatis.framework.handler.ParameterHandler;
import top.okay3r.mybatis.framework.handler.ResultSetHandler;
import top.okay3r.mybatis.framework.handler.StatementHandler;
import top.okay3r.mybatis.framework.handler.impl.DefaultParameterHandler;
import top.okay3r.mybatis.framework.handler.impl.DefaultResultSetHandler;
import top.okay3r.mybatis.framework.handler.impl.PreparedStatementHandler;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2019/12/7
 * Time: 14:05
 * Explain: 配置信息类，在全局中有且只有一个实例
 */
public class Configuration {
    //数据库连接池
    private DataSource dataSource;
    //key为namespace+id作为唯一标识，value为每个statement标签中的信息
    private Map<String, MapperStatement> mapperStatementMap;
    //是否启用二级缓存
    private boolean isCached = true;

    public Configuration() {
        this.mapperStatementMap = new HashMap<>();
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MapperStatement> getMapperStatementMap() {
        return mapperStatementMap;
    }

    public void setMapperStatementMap(Map<String, MapperStatement> mapperStatementMap) {
        this.mapperStatementMap = mapperStatementMap;
    }

    public void addMapperStatementToStatementInfoMap(String id, MapperStatement mapperStatement) {
        this.mapperStatementMap.put(id, mapperStatement);
    }

    public MapperStatement getMapperStatementById(String id) {
        return this.mapperStatementMap.get(id);
    }

    public Executor newExecutor() {
        Executor executor = null;
        //首先创建一个Executor为默认实现类型
        executor = new SimpleExecutor();
        //如果开启了二级缓存，则Executor的为CacheExecutor
        if (isCached) {
            executor = new CacheExecutor(executor);
        }
        return executor;
    }

    //创建statementHandler
    public StatementHandler newStatementHandler(MapperStatement mapperStatement) {
        StatementHandler statementHandler = null;
        //判断Statement的类型
        switch (mapperStatement.getStatementType()) {
            case "prepared":
                statementHandler = new PreparedStatementHandler(this, mapperStatement);
                break;
            case "simple":
                // statementHandler = new SimpleStatementHandler();
                break;
            default:

        }
        return statementHandler;
    }
    //创建parameterHandler
    public ParameterHandler newParameterHandler(MapperStatement mapperStatement) {
        return new DefaultParameterHandler(mapperStatement);
    }

    //床架resultSetHandler
    public ResultSetHandler newResultSetHandler(MapperStatement mapperStatement) {
        return new DefaultResultSetHandler(mapperStatement);
    }
}
