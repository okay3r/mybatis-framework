package top.okay3r.mybatis.framework.config.sqlnode;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.framework.config.sqlnode.SqlNode
 * User: okay3r
 * Date: 2020/1/12
 * Time: 23:08
 * Explain:
 */
public interface SqlNode {
    void apply(DynamicContext context);
}
