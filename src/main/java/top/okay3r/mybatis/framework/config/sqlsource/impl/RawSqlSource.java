package top.okay3r.mybatis.framework.config.sqlsource.impl;

import top.okay3r.mybatis.framework.config.sqlnode.DynamicContext;
import top.okay3r.mybatis.framework.config.sqlnode.SqlNode;
import top.okay3r.mybatis.framework.config.sqlsource.BoundSql;
import top.okay3r.mybatis.framework.config.sqlsource.SqlSource;
import top.okay3r.mybatis.framework.config.utils.GenericTokenParser;
import top.okay3r.mybatis.framework.config.utils.ParameterMappingTokenHandler;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.framework.config.sqlsource.impl.RawSqlSource
 * User: okay3r
 * Date: 2020/1/12
 * Time: 23:06
 * Explain:
 */
public class RawSqlSource implements SqlSource {
    private StaticSqlSource staticSqlSource;

    public RawSqlSource(SqlNode mixedSqlNode) {
        // #{}处理的时候，不需要入参对象的支持
        DynamicContext context = new DynamicContext(null);
        // 处理SqlNode
        mixedSqlNode.apply(context);

        ParameterMappingTokenHandler handler = new ParameterMappingTokenHandler();
        // 将#{}解析为?并保存参数信息
        GenericTokenParser tokenParser = new GenericTokenParser("#{", "}", handler);
        // 获取真正可以执行的SQL语句
        String sql = tokenParser.parse(context.getSql());

        // 该SqlSource就是封装已经解析完成的Sql语句
        staticSqlSource = new StaticSqlSource(sql, handler.getParameterMappings());

    }

    @Override
    public BoundSql getBoundSql(Object param) {
        return staticSqlSource.getBoundSql(param);
    }
}
