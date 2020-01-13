package top.okay3r.mybatis.framework.sqlsource.impl;

import top.okay3r.mybatis.framework.config.DynamicContext;
import top.okay3r.mybatis.framework.parser.SqlSourceParser;
import top.okay3r.mybatis.framework.sqlnode.SqlNode;
import top.okay3r.mybatis.framework.config.BoundSql;
import top.okay3r.mybatis.framework.sqlsource.SqlSource;
import top.okay3r.mybatis.framework.utils.GenericTokenParser;
import top.okay3r.mybatis.framework.utils.ParameterMappingTokenHandler;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/1/12
 * Time: 23:06
 * Explain:
 */
public class RawSqlSource implements SqlSource {
    private SqlSource sqlSource;

    public RawSqlSource(SqlNode mixedSqlNode) {
        // #{}处理的时候，不需要入参对象的支持
        DynamicContext context = new DynamicContext(null);
        // 处理SqlNode
        mixedSqlNode.apply(context);
        SqlSourceParser parser = new SqlSourceParser(context);
        sqlSource = parser.parse();
    }

    @Override
    public BoundSql getBoundSql(Object param) {
        return sqlSource.getBoundSql(param);
    }
}
