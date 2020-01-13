package top.okay3r.mybatis.framework.config.sqlnode.impl;

import top.okay3r.mybatis.framework.config.sqlnode.DynamicContext;
import top.okay3r.mybatis.framework.config.sqlnode.SqlNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.framework.config.sqlnode.impl.MixedSqlNode
 * User: okay3r
 * Date: 2020/1/12
 * Time: 23:11
 * Explain:
 */
public class MixedSqlNode implements SqlNode {
    private List<SqlNode> sqlNodeList;

    public MixedSqlNode(List<SqlNode> sqlNodeList) {
        this.sqlNodeList = sqlNodeList;
    }

    @Override
    public void apply(DynamicContext context) {
        for (SqlNode sqlNode : sqlNodeList) {
            sqlNode.apply(context);
        }
    }
}