package top.okay3r.mybatis.sqlsession;

import org.dom4j.Document;
import top.okay3r.mybatis.config.Configuration;
import top.okay3r.mybatis.config.parser.XMLConfigParser;
import top.okay3r.mybatis.sqlsession.impl.DefaultSqlSessionFactory;

import java.io.InputStream;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.sqlsession.SqlSessionFactoryBuilder
 * User: okay3r
 * Date: 2019/12/7
 * Time: 14:05
 * Info:
 */
public class SqlSessionFactoryBuilder {
    private Configuration configuration;

    public SqlSessionFactoryBuilder() {
        this.configuration = new Configuration();
    }

    public SqlSessionFactory initConfiguration(InputStream inputStream) {
        Document document = DocumentReader.createDocument(inputStream);
        new XMLConfigParser(configuration).parseConfig(document.getRootElement());
        return build();
    }

    public SqlSessionFactory build() {
        return new DefaultSqlSessionFactory(configuration);
    }
}
