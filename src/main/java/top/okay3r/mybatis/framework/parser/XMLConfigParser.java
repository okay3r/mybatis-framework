package top.okay3r.mybatis.framework.parser;

import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;
import top.okay3r.mybatis.framework.config.Configuration;
import top.okay3r.mybatis.framework.utils.DocumentUtils;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2019/12/7
 * Time: 14:10
 * Explain:
 */
public class XMLConfigParser {
    private Configuration configuration;

    public XMLConfigParser(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parseConfig(Element root) {
        //解析全局配置
        Element environments = root.element("environments");

        //解析mapper配置文件
        Element mappers = root.element("mappers");
        parseEnvironments(environments);
        parseMappers(mappers);
    }

    public void parseEnvironments(Element environments) {
        String defaultId = environments.attributeValue("default");
        List<Element> environmentList = environments.elements();
        for (Element environment : environmentList) {
            String id = environment.attributeValue("id");
            if (id.equals(defaultId)) {

                //解析数据库配置
                parseDataSource(environment.element("dataSource"));
            }
        }
    }

    public void parseDataSource(Element dataSourceElement) {
        String type = dataSourceElement.attributeValue("type");
        if (type == null || type.equals("")) {
            type = "DBCP";
        }
        List<Element> ps = dataSourceElement.elements();
        Properties properties = new Properties();
        for (Element property : ps) {
            properties.setProperty(property.attributeValue("name"), property.attributeValue("value"));
        }
        BasicDataSource dataSource = new BasicDataSource();
        //TODO 多种连接池匹配
        if ("DBCP".equals(type)) {
            dataSource.setDriverClassName(properties.getProperty("driver"));
            dataSource.setUrl(properties.getProperty("url"));
            dataSource.setUsername(properties.getProperty("username"));
            dataSource.setPassword(properties.getProperty("password"));
        }
        configuration.setDataSource(dataSource);
    }

    public void parseMappers(Element mappersElement) {
        List<Element> mapperList = mappersElement.elements();
        for (Element mapper : mapperList) {
            String resource = mapper.attributeValue("resource");
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
            Document document = DocumentUtils.readDocument(inputStream);
            //逐个解析mapper.xml文件
            new XMLMapperParser(configuration).parseMapper(document.getRootElement());
        }
    }
}
