package top.okay3r.mybatis.framework.config;

import javax.sql.DataSource;
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
}
