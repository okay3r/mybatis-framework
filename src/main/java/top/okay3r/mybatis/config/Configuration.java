package top.okay3r.mybatis.config;

import top.okay3r.mybatis.config.sql.StatementInfo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.config.Configuration
 * User: okay3r
 * Date: 2019/12/7
 * Time: 14:05
 * Info:
 */
public class Configuration {
    private DataSource dataSource;
    private Map<String, StatementInfo> statementInfoMap;

    public Configuration() {
        this.statementInfoMap = new HashMap<>();
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, StatementInfo> getStatementInfoMap() {
        return statementInfoMap;
    }

    public void setStatementInfoMap(Map<String, StatementInfo> statementInfoMap) {
        this.statementInfoMap = statementInfoMap;
    }

    public void addStatementInfoToStatementInfoMap(String id, StatementInfo statementInfo) {
        this.statementInfoMap.put(id, statementInfo);
    }
}
