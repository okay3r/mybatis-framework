package top.okay3r.mybatis.framework.parser;

import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;
import top.okay3r.mybatis.framework.config.Configuration;
import top.okay3r.mybatis.framework.sqlnode.SqlNode;
import top.okay3r.mybatis.framework.sqlnode.impl.IfSqlNode;
import top.okay3r.mybatis.framework.sqlnode.impl.MixedSqlNode;
import top.okay3r.mybatis.framework.sqlnode.impl.TextSqlNode;
import top.okay3r.mybatis.framework.sqlsource.SqlSource;
import top.okay3r.mybatis.framework.sqlsource.impl.DynamicSqlSource;
import top.okay3r.mybatis.framework.sqlsource.impl.RawSqlSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/1/13
 * Time: 23:21
 * Explain:
 */
public class XmlScriptNodeParser {

    private boolean isDynamic = false;

    public SqlSource parseScriptNode(Element statementElement) {
        MixedSqlNode rootSqlNode = parseDynamicTags(statementElement);
        SqlSource sqlSource;
        if (isDynamic) {
            sqlSource = new DynamicSqlSource(rootSqlNode);
        } else {
            sqlSource = new RawSqlSource(rootSqlNode);
        }
        return sqlSource;
    }

    public MixedSqlNode parseDynamicTags(Element statementElement) {
        List<SqlNode> sqlNodeList = new ArrayList<>();
        int count = statementElement.nodeCount();
        for (int i = 0; i < count; i++) {
            Node node = statementElement.node(i);
            if (node instanceof Text) {
                String sqlText = node.getText().trim();
                if (sqlText == null || "".equals(sqlText)) {
                    continue;
                }
                TextSqlNode textSqlNode = new TextSqlNode(sqlText);
                if (textSqlNode.isDynamic()) {
                    isDynamic = true;
                    sqlNodeList.add(textSqlNode);
                } else {
                    sqlNodeList.add(textSqlNode);
                }
            } else if (node instanceof Element) {
                String elementName = statementElement.getName();
                if ("if".equals(elementName)) {
                    // 封装成IfSqlNode（test信息、子标签信息）
                    Element element = (Element) node;
                    // if标签的test属性信息
                    String test = element.attributeValue("text");
                    // if标签的子标签信息
                    MixedSqlNode rootSqlNode = parseDynamicTags(element);

                    // IfSqlNode就是封装if标签信息的
                    IfSqlNode ifSqlNode = new IfSqlNode(test, rootSqlNode);
                    sqlNodeList.add(ifSqlNode);
                }
                isDynamic = true;
            }
        }
        return new MixedSqlNode(sqlNodeList);
    }
}
