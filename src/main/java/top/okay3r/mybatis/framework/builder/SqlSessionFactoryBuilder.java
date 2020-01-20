package top.okay3r.mybatis.framework.builder;

import org.dom4j.Document;
import top.okay3r.mybatis.framework.config.Configuration;
import top.okay3r.mybatis.framework.parser.XMLConfigParser;
import top.okay3r.mybatis.framework.utils.DocumentUtils;
import top.okay3r.mybatis.framework.sqlsessionfactory.impl.DefaultSqlSessionFactory;
import top.okay3r.mybatis.framework.sqlsessionfactory.SqlSessionFactory;

import java.io.InputStream;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2019/12/7
 * Time: 14:05
 * Explain: 通过configuration中的信息创建SqlSessionFactory
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Configuration configuration) {
        return new DefaultSqlSessionFactory(configuration);
    }
}
