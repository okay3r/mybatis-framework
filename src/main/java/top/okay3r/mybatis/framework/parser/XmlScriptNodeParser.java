package top.okay3r.mybatis.framework.parser;

import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;
import top.okay3r.mybatis.framework.sqlnode.SqlNode;
import top.okay3r.mybatis.framework.sqlnode.impl.IfSqlNode;
import top.okay3r.mybatis.framework.sqlnode.impl.MixedSqlNode;
import top.okay3r.mybatis.framework.sqlnode.impl.StaticTextSqlNode;
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
 * Explain: 负责解析Statement标签中的信息
 */
public class XmlScriptNodeParser {

    //是否为动态标签
    private boolean isDynamic = false;

    public SqlSource parseScriptNode(Element statementElement) {
        //解析出来所有的SqlNode信息
        MixedSqlNode rootSqlNode = parseDynamicTags(statementElement);
        // isDynamic是parseDynamicTags过程中，得到的值
        // 如果包含${}或者包含动态标签，则isDynamic为true
        SqlSource sqlSource;
        // 将SqlNode信息封装到SqlSource中，并且要根据是否动态节点去选择不同的SqlSource
        // 如果isDynamic为true，则说明SqlNode集合信息里面包含${}SqlNode节点信息或者动态标签的节点信息
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
        //遍历Statement标签的子标签
        for (int i = 0; i < count; i++) {
            //获取子标签
            Node node = statementElement.node(i);
            //判断是文本标签还是元素标签
            if (node instanceof Text) {  //文本标签
                String sqlText = node.getText().trim();
                if (sqlText == null || "".equals(sqlText)) {
                    continue;
                }
                TextSqlNode textSqlNode = new TextSqlNode(sqlText);
                //设置是否为动态标签
                if (textSqlNode.isDynamic()) {
                    isDynamic = true;
                    sqlNodeList.add(textSqlNode);
                } else {
                    sqlNodeList.add(new StaticTextSqlNode(sqlText));
                }
            } else if (node instanceof Element) { //元素标签
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
