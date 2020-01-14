package top.okay3r.mybatis.framework.handler;

import java.sql.Statement;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/1/14
 * Time: 22:39
 * Explain:
 */
public interface ParameterHandler {
    void handleParameter(Statement statement, Object param);
}
