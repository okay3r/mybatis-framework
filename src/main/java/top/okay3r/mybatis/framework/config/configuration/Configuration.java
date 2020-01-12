package top.okay3r.mybatis.framework.config.configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.framework.config.configuration.Configuration
 * User: okay3r
 * Date: 2019/12/7
 * Time: 14:05
 * Explain:
 */
public class Configuration {
    private DataSource dataSource;
    private Map<String, MapperStatement> statementInfoMap;

    public Configuration() {
        this.statementInfoMap = new HashMap<>();
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MapperStatement> getStatementInfoMap() {
        return statementInfoMap;
    }

    public void setStatementInfoMap(Map<String, MapperStatement> statementInfoMap) {
        this.statementInfoMap = statementInfoMap;
    }

    public void addStatementInfoToStatementInfoMap(String id, MapperStatement mapperStatement) {
        this.statementInfoMap.put(id, mapperStatement);
    }
}
