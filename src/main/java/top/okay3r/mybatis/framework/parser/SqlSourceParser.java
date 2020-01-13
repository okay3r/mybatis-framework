package top.okay3r.mybatis.framework.parser;

import top.okay3r.mybatis.framework.config.DynamicContext;
import top.okay3r.mybatis.framework.sqlsource.SqlSource;
import top.okay3r.mybatis.framework.sqlsource.impl.StaticSqlSource;
import top.okay3r.mybatis.framework.utils.GenericTokenParser;
import top.okay3r.mybatis.framework.utils.ParameterMappingTokenHandler;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/1/13
 * Time: 23:28
 * Explain:
 */
public class SqlSourceParser {
    private DynamicContext context;

    public SqlSourceParser(DynamicContext context) {
        this.context = context;
    }

    public SqlSource parse() {
        ParameterMappingTokenHandler handler = new ParameterMappingTokenHandler();
        // 将#{}解析为?并保存参数信息
        GenericTokenParser tokenParser = new GenericTokenParser("#{", "}", handler);
        // 获取真正可以执行的SQL语句
        String sql = tokenParser.parse(context.getSql());
        System.out.println(sql);
        // 该SqlSource就是封装已经解析完成的Sql语句
        return new StaticSqlSource(sql, handler.getParameterMappings());
    }
}
