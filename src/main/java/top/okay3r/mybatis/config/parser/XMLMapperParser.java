package top.okay3r.mybatis.config.parser;

import org.dom4j.Element;
import top.okay3r.mybatis.config.Configuration;
import top.okay3r.mybatis.config.sql.SqlSource;
import top.okay3r.mybatis.config.sql.StatementInfo;

import java.util.List;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.config.parser.XMLMapperParser
 * User: okay3r
 * Date: 2019/12/7
 * Time: 14:31
 * Info:
 */
public class XMLMapperParser {
    private Configuration configuration;

    public XMLMapperParser(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parseMapper(Element mapperElement) {
        String namespace = mapperElement.attributeValue("namespace");

        List<Element> selectStatementList = mapperElement.elements("select");
        parseStatement(namespace, selectStatementList);

        List<Element> insertStatementList = mapperElement.elements("insert");
        parseStatement(namespace, insertStatementList);

        List<Element> updateStatementList = mapperElement.elements("update");
        parseStatement(namespace, updateStatementList);

        List<Element> deleteStatementList = mapperElement.elements("delete");
        parseStatement(namespace, deleteStatementList);
    }


    public void parseStatement(String namespace, List<Element> selectStatementList) {
        for (Element element : selectStatementList) {
            String statementId = namespace + "." + element.attributeValue("id");
            String parameterType = element.attributeValue("parameterType");
            String resultType = element.attributeValue("resultType");
            String statementType = element.attributeValue("statementType");
            Class<?> parameterTypeClass = null;
            Class<?> resultTypeClass = null;
            try {
                parameterTypeClass = Class.forName(parameterType);
                resultTypeClass = Class.forName(resultType);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            SqlSource sqlSource = new SqlSource(element.getTextTrim());
            StatementInfo statementInfo = new StatementInfo(statementId, parameterTypeClass, resultTypeClass, statementType, sqlSource);
            configuration.addStatementInfoToStatementInfoMap(statementId, statementInfo);
        }
    }
}
