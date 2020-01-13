package top.okay3r.mybatis.framework.config.sqlnode.impl;

import top.okay3r.mybatis.framework.config.sqlnode.DynamicContext;
import top.okay3r.mybatis.framework.config.sqlnode.SqlNode;
import top.okay3r.mybatis.framework.config.utils.GenericTokenParser;
import top.okay3r.mybatis.framework.config.utils.OgnlUtils;
import top.okay3r.mybatis.framework.config.utils.SimpleTypeRegistry;
import top.okay3r.mybatis.framework.config.utils.TokenHandler;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.framework.config.sqlnode.impl.TextSqlNode
 * User: okay3r
 * Date: 2020/1/12
 * Time: 23:10
 * Explain:
 */
public class TextSqlNode implements SqlNode {
    private String sqlText;

    public TextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    // ${}的处理，就是在这个时候
    @Override
    public void apply(DynamicContext context) {

        BindingTokenParser handler = new BindingTokenParser(context);
        // 将#{}解析为?并保存参数信息
        GenericTokenParser tokenParser = new GenericTokenParser("${", "}", handler);
        // 获取真正可以执行的SQL语句
        String sql = tokenParser.parse(sqlText);
        context.appendSql(sql);
    }

    /**
     * 对外提供保存数据的处理功能
     *
     * @return
     */
    public boolean isDynamic() {
        if (sqlText.indexOf("${") > -1) {
            return true;
        }

        return false;
    }

    private static class BindingTokenParser implements TokenHandler {
        private DynamicContext context;

        public BindingTokenParser(DynamicContext context) {
            this.context = context;
        }

        /**
         * expression：比如说${username}，那么expression就是username username也就是Ognl表达式
         */
        @Override
        public String handleToken(String expression) {
            Object paramObject = context.getBindings().get("_parameter");
            if (paramObject == null) {
                // context.getBindings().put("value", null);
                return "";
            } else if (SimpleTypeRegistry.isSimpleType(paramObject.getClass())) {
                // context.getBindings().put("value", paramObject);
                return String.valueOf(paramObject);
            }

            // 使用Ognl api去获取相应的值
            // Object value = OgnlUtils.getValue(expression, context.getBindings());
            Object value = OgnlUtils.getValue(expression, paramObject);
            String srtValue = value == null ? "" : String.valueOf(value);
            return srtValue;
        }

    }
}
