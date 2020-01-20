package top.okay3r.mybatis.framework.sqlnode.impl;

import top.okay3r.mybatis.framework.config.DynamicContext;
import top.okay3r.mybatis.framework.sqlnode.SqlNode;

import java.util.List;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/1/12
 * Time: 23:11
 * Explain: 组合对象，封装SqlNode的list集合
 */
public class MixedSqlNode implements SqlNode {
    private List<SqlNode> sqlNodeList;

    public MixedSqlNode(List<SqlNode> sqlNodeList) {
        this.sqlNodeList = sqlNodeList;
    }

    /***
     * 对外提供封装数据的操作
     * @param context
     */
    @Override
    public void apply(DynamicContext context) {
        for (SqlNode sqlNode : sqlNodeList) {
            sqlNode.apply(context);
        }
    }
}
