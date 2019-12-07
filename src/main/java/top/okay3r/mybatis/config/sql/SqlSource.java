package top.okay3r.mybatis.config.sql;

import top.okay3r.mybatis.config.utils.GenericTokenParser;
import top.okay3r.mybatis.config.utils.ParameterMappingTokenHandler;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.config.sql.SqlSource
 * User: okay3r
 * Date: 2019/12/7
 * Time: 14:41
 * Info:
 */
public class SqlSource {
    private String sqlText;

    public SqlSource(String sqlText) {
        this.sqlText = sqlText;
    }

    public BindingSql getBindingSql() {
        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", tokenHandler);
        String sql = genericTokenParser.parse(sqlText);
        return new BindingSql(sql, tokenHandler.getParameterMappings());
    }
}
