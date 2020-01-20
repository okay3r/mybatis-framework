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
 * Explain: 封装带有${}或者动态SQL标签的SQL信息
 *          解析工作发生在每一次调用getBoundSql方法的时候
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

        SqlSourceParser sqlSourceParser = new SqlSourceParser(context);
        SqlSource sqlSource = sqlSourceParser.parse();
        return sqlSource.getBoundSql(param);
    }
}
