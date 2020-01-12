package top.okay3r.mybatis.framework.config.sqlnode.impl;

import top.okay3r.mybatis.framework.config.sqlnode.DynamicContext;
import top.okay3r.mybatis.framework.config.sqlnode.SqlNode;
import top.okay3r.mybatis.framework.config.utils.OgnlUtils;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.framework.config.sqlnode.impl.IfSqlNode
 * User: okay3r
 * Date: 2020/1/12
 * Time: 23:12
 * Explain:
 */
public class IfSqlNode implements SqlNode {
    private String text;
    private SqlNode rootSqlNode;

    public IfSqlNode(String text, SqlNode rootSqlNode) {
        this.text = text;
        this.rootSqlNode = rootSqlNode;
    }

    @Override
    public void apply(DynamicContext context) {
        boolean evaluateBoolean = OgnlUtils.evaluateBoolean(text, context.getBindings().get("_parameter"));
        if (evaluateBoolean) {
            rootSqlNode.apply(context);
            context.appendSql(context.getSql());
        }
    }
}
