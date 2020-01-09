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


    public void parseStatement(String namespace, List<Element> selectStatementList) {
        for (Element element : selectStatementList) {

            //statementId采用namespace+id的方式
            String statementId = namespace + "." + element.attributeValue("id");
            String parameterType = element.attributeValue("parameterType");
            String resultType = element.attributeValue("resultType");
            String statementType = element.attributeValue("statementType");
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
            SqlSource sqlSource = new SqlSource(element.getTextTrim());
            //将各个信息封装进一个新创建的StatementInfo
            StatementInfo statementInfo = new StatementInfo(statementId, parameterTypeClass, resultTypeClass, statementType, sqlSource);
            //将StatementInfo存入Configuration全局配置文件中
            configuration.addStatementInfoToStatementInfoMap(statementId, statementInfo);
        }
    }
}
