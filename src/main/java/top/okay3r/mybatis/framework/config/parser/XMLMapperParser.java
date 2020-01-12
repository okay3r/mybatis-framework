package top.okay3r.mybatis.framework.config.parser;

import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;
import top.okay3r.mybatis.framework.config.configuration.Configuration;
import top.okay3r.mybatis.framework.config.configuration.MapperStatement;
import top.okay3r.mybatis.framework.config.sqlnode.SqlNode;
import top.okay3r.mybatis.framework.config.sqlnode.impl.IfSqlNode;
import top.okay3r.mybatis.framework.config.sqlnode.impl.MixedSqlNode;
import top.okay3r.mybatis.framework.config.sqlnode.impl.TextSqlNode;
import top.okay3r.mybatis.framework.config.sqlsource.SqlSource;
import top.okay3r.mybatis.framework.config.sqlsource.impl.DynamicSqlSource;
import top.okay3r.mybatis.framework.config.sqlsource.impl.RawSqlSource;
import top.okay3r.mybatis.framework.config.sqlsource.impl.StaticSqlSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.framework.config.parser.XMLMapperParser
 * User: okay3r
 * Date: 2019/12/7
 * Time: 14:31
 * Explain:
 */
public class XMLMapperParser {
    private Configuration configuration;

    private boolean isDynamic = false;

    public XMLMapperParser(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parseMapper(Element mapperElement) {
        //获取namespace
        String namespace = mapperElement.attributeValue("namespace");

        //逐个解析CRUD标签
        List<Element> selectStatementList = mapperElement.elements("select");
        parseStatement(namespace, selectStatementList);

        List<Element> insertStatementList = mapperElement.elements("insert");
        parseStatement(namespace, insertStatementList);

        List<Element> updateStatementList = mapperElement.elements("update");
        parseStatement(namespace, updateStatementList);

        List<Element> deleteStatementList = mapperElement.elements("delete");
        parseStatement(namespace, deleteStatementList);
    }


    public void parseStatement(String namespace, List<Element> statementList) {
        for (Element statementElement : statementList) {
            //statementId采用namespace+id的方式
            String statementId = namespace + "." + statementElement.attributeValue("id");
            String parameterType = statementElement.attributeValue("parameterType");
            String resultType = statementElement.attributeValue("resultType");
            String statementType = statementElement.attributeValue("statementType");
            Class<?> parameterTypeClass = null;
            Class<?> resultTypeClass = null;
            try {
                //反射创建参数、返回值类型
                parameterTypeClass = Class.forName(parameterType);
                resultTypeClass = Class.forName(resultType);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //获取sql语句
            SqlSource sqlSource = createSqlSource(statementElement);
            //将各个信息封装进一个新创建的StatementInfo
            MapperStatement mapperStatement = new MapperStatement(statementId, parameterTypeClass, resultTypeClass, statementType, sqlSource);
            //将StatementInfo存入Configuration全局配置文件中
            configuration.addStatementInfoToStatementInfoMap(statementId, mapperStatement);
        }
    }

    private SqlSource createSqlSource(Element statementElement) {
        SqlSource sqlSource = parseScriptNode(statementElement);
        return sqlSource;
    }

    private SqlSource parseScriptNode(Element statementElement) {
        MixedSqlNode rootSqlNode = parseDynamicTags(statementElement);
        SqlSource sqlSource;
        if (isDynamic) {
            sqlSource = new DynamicSqlSource(rootSqlNode);
        } else {
            sqlSource = new RawSqlSource(rootSqlNode);
        }
        return sqlSource;
    }

    private MixedSqlNode parseDynamicTags(Element statementElement) {
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
