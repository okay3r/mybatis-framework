package top.okay3r.mybatis.framework.parser;

import org.dom4j.Element;
import top.okay3r.mybatis.framework.config.Configuration;
import top.okay3r.mybatis.framework.config.MapperStatement;
import top.okay3r.mybatis.framework.sqlsource.SqlSource;

import java.util.List;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.framework.parser.XmlStatementParser
 * User: okay3r
 * Date: 2020/1/13
 * Time: 23:20
 * Explain:
 */
public class XmlStatementParser {
    private Configuration configuration;

    public XmlStatementParser(Configuration configuration) {
        this.configuration = configuration;
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
            configuration.addMapperStatementToStatementInfoMap(statementId, mapperStatement);
        }
    }

    private SqlSource createSqlSource(Element statementElement) {
        SqlSource sqlSource = new XmlScriptNodeParser().parseScriptNode(statementElement);
        return sqlSource;
    }
}
