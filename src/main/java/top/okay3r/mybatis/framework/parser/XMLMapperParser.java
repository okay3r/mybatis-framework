package top.okay3r.mybatis.framework.parser;

import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;
import top.okay3r.mybatis.framework.config.Configuration;
import top.okay3r.mybatis.framework.config.MapperStatement;
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
 * Date: 2019/12/7
 * Time: 14:31
 * Explain: 负责解析mapper.xml映射文件
 */
public class XMLMapperParser {
    private Configuration configuration;

    public XMLMapperParser(Configuration configuration) {
        this.configuration = configuration;
    }

    /***
     * 对Statement标签进行处理
     * @param mapperElement
     */
    public void parseMapper(Element mapperElement) {
        //获取namespace
        String namespace = mapperElement.attributeValue("namespace");

        XmlStatementParser xmlStatementParser = new XmlStatementParser(configuration);

        //逐个解析CRUD标签
        List<Element> selectStatementList = mapperElement.elements("select");
        xmlStatementParser.parseStatement(namespace, selectStatementList);

        List<Element> insertStatementList = mapperElement.elements("insert");
        xmlStatementParser.parseStatement(namespace, insertStatementList);

        List<Element> updateStatementList = mapperElement.elements("update");
        xmlStatementParser.parseStatement(namespace, updateStatementList);

        List<Element> deleteStatementList = mapperElement.elements("delete");
        xmlStatementParser.parseStatement(namespace, deleteStatementList);
    }

}
