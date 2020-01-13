package top.okay3r.mybatis.framework.sqlnode;

import top.okay3r.mybatis.framework.config.DynamicContext;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/1/12
 * Time: 23:08
 * Explain:
 */
public interface SqlNode {
    void apply(DynamicContext context);
}
