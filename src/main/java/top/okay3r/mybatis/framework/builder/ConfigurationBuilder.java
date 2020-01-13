package top.okay3r.mybatis.framework.builder;

import org.dom4j.Document;
import top.okay3r.mybatis.framework.config.Configuration;
import top.okay3r.mybatis.framework.parser.XMLConfigParser;
import top.okay3r.mybatis.framework.sqlsessionfactory.SqlSessionFactory;
import top.okay3r.mybatis.framework.utils.DocumentUtils;

import java.io.InputStream;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.framework.builder.ConfigurationBuilder
 * User: okay3r
 * Date: 2020/1/13
 * Time: 22:52
 * Explain:
 */
public class ConfigurationBuilder {
    private Configuration configuration;

    public ConfigurationBuilder() {
        this.configuration = new Configuration();
    }

    public Configuration buildConfiguration(InputStream inputStream) {
        Document document = DocumentUtils.readDocument(inputStream);
        //解析全局xml配置文件 sqlMapConfig.xml
        new XMLConfigParser(configuration).parseConfig(document.getRootElement());
        return configuration;
    }
}
