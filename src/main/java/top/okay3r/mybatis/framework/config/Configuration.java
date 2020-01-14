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
 * Explain:
 */
public class Configuration {
    private DataSource dataSource;
    private Map<String, MapperStatement> mapperStatementMap;
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
        executor = new SimpleExecutor();
        if (isCached) {
            executor = new CacheExecutor(executor);
        }
        return executor;
    }

    public StatementHandler newStatementHandler(MapperStatement mapperStatement) {
        StatementHandler statementHandler = null;
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

    public ParameterHandler newParameterHandler(MapperStatement mapperStatement) {
        return new DefaultParameterHandler(mapperStatement);
    }

    public ResultSetHandler newResultSetHandler(MapperStatement mapperStatement) {
        return new DefaultResultSetHandler(mapperStatement);
    }
}
