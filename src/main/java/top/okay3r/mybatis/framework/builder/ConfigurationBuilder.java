package top.okay3r.mybatis.framework.builder;

import org.dom4j.Document;
import top.okay3r.mybatis.framework.config.Configuration;
import top.okay3r.mybatis.framework.parser.XMLConfigParser;
import top.okay3r.mybatis.framework.sqlsessionfactory.SqlSessionFactory;
import top.okay3r.mybatis.framework.utils.DocumentUtils;
import top.okay3r.mybatis.framework.utils.Resources;

import java.io.InputStream;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/1/13
 * Time: 22:52
 * Explain: 创建全局唯一的Configuration，解析配置文件到configuration中
 */
public class ConfigurationBuilder {
    private static volatile Configuration configuration;

    private ConfigurationBuilder() {
    }

    public static Configuration buildConfiguration(String location) {
        if (configuration == null) {
            synchronized (ConfigurationBuilder.class) {
                if (configuration == null) {
                    configuration = new Configuration();
                }
            }
        }
        InputStream inputStream = Resources.getResourcesAsStream(location);
        //获取xml配置文件
        Document document = DocumentUtils.readDocument(inputStream);
        //解析全局xml配置文件 sqlMapConfig.xml
        new XMLConfigParser(configuration).parseConfig(document.getRootElement());
        return configuration;
    }
}
