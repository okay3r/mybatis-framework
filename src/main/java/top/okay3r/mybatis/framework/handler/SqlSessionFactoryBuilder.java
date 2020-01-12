package top.okay3r.mybatis.framework.handler;

import org.dom4j.Document;
import top.okay3r.mybatis.framework.config.configuration.Configuration;
import top.okay3r.mybatis.framework.config.parser.XMLConfigParser;
import top.okay3r.mybatis.framework.config.utils.DocumentUtils;
import top.okay3r.mybatis.framework.handler.sqlsessionfactory.impl.DefaultSqlSessionFactory;
import top.okay3r.mybatis.framework.handler.sqlsessionfactory.SqlSessionFactory;

import java.io.InputStream;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.sqlsession.SqlSessionFactoryBuilder
 * User: okay3r
 * Date: 2019/12/7
 * Time: 14:05
 * Explain:
 */
public class SqlSessionFactoryBuilder {
    private Configuration configuration;

    public SqlSessionFactoryBuilder() {
        //创建全局唯一的Configuration配置对象
        this.configuration = new Configuration();
    }

    public SqlSessionFactory initConfiguration(InputStream inputStream) {
        Document document = DocumentUtils.readDocument(inputStream);

        //解析全局xml配置文件 sqlMapConfig.xml
        new XMLConfigParser(configuration).parseConfig(document.getRootElement());
        return build();
    }

    public SqlSessionFactory build() {
        return new DefaultSqlSessionFactory(configuration);
    }
}
