package top.okay3r.mybatis.framework.config.sqlsource.impl;

import top.okay3r.mybatis.framework.config.sqlnode.DynamicContext;
import top.okay3r.mybatis.framework.config.sqlnode.SqlNode;
import top.okay3r.mybatis.framework.config.sqlsource.BoundSql;
import top.okay3r.mybatis.framework.config.sqlsource.SqlSource;
import top.okay3r.mybatis.framework.config.utils.GenericTokenParser;
import top.okay3r.mybatis.framework.config.utils.ParameterMappingTokenHandler;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.framework.config.sqlsource.impl.DynamicSqlSource
 * User: okay3r
 * Date: 2020/1/12
 * Time: 23:06
 * Explain:
 */
public class DynamicSqlSource implements SqlSource {
    private SqlNode rootSqlNode;

    public DynamicSqlSource(SqlNode mixedSqlNode) {
        this.rootSqlNode = mixedSqlNode;
    }

    @Override
    public BoundSql getBoundSql(Object param) {
        // #{}处理的时候，不需要入参对象的支持
        DynamicContext context = new DynamicContext(param);
        // 处理SqlNode，先去处理动态标签和${}，拼接成一条SQL文本，该SQL文本还包含#{}
        rootSqlNode.apply(context);

        // 处理#{}
        ParameterMappingTokenHandler handler = new ParameterMappingTokenHandler();
        // 将#{}解析为?并保存参数信息
        GenericTokenParser tokenParser = new GenericTokenParser("#{", "}", handler);
        // 获取真正可以执行的SQL语句
        String sql = tokenParser.parse(context.getSql());

        return new BoundSql(sql, handler.getParameterMappings());
    }
}
