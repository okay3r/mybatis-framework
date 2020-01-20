package top.okay3r.mybatis.framework.parser;

import org.dom4j.Element;
import top.okay3r.mybatis.framework.config.Configuration;
import top.okay3r.mybatis.framework.config.MapperStatement;
import top.okay3r.mybatis.framework.sqlsource.SqlSource;

import java.util.List;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/1/13
 * Time: 23:20
 * Explain:
 */
public class XmlStatementParser {
    private Configuration configuration;

    public XmlStatementParser(Configuration configuration) {
        this.configuration = configuration;
    }

    //解析Statement标签select、insert、update、delete
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
            //获取SqlSource
            SqlSource sqlSource = createSqlSource(statementElement);
            //将各个信息封装进一个新创建的StatementInfo
            MapperStatement mapperStatement = new MapperStatement(statementId, parameterTypeClass, resultTypeClass, statementType, sqlSource);
            //将StatementInfo存入Configuration全局配置文件中
            configuration.addMapperStatementToStatementInfoMap(statementId, mapperStatement);
        }
    }

    //创建一个Statement对应的SqlSource
    private SqlSource createSqlSource(Element statementElement) {
        //解析Statement中的SqlNode
        SqlSource sqlSource = new XmlScriptNodeParser().parseScriptNode(statementElement);
        return sqlSource;
    }
}
