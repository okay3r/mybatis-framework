package top.okay3r.mybatis.framework.sqlnode.impl;

import top.okay3r.mybatis.framework.config.DynamicContext;
import top.okay3r.mybatis.framework.sqlnode.SqlNode;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/1/12
 * Time: 23:12
 * Explain: 封装带有#{}的文本字符串
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
