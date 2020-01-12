package top.okay3r.mybatis.framework.config.sqlnode.impl;

import top.okay3r.mybatis.framework.config.sqlnode.DynamicContext;
import top.okay3r.mybatis.framework.config.sqlnode.SqlNode;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.framework.config.sqlnode.impl.StaticTextSqlNode
 * User: okay3r
 * Date: 2020/1/12
 * Time: 23:12
 * Explain:
 */
public class StaticTextSqlNode implements SqlNode {

    private String sqlText;

    public StaticTextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    @Override
    public void apply(DynamicContext context) {
        context.appendSql(sqlText);
    }
}
