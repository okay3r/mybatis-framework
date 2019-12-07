package top.okay3r.mybatis.sqlsession;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.sqlsession.DocumentReader
 * User: okay3r
 * Date: 2019/12/7
 * Time: 14:06
 * Info:
 */
public class DocumentReader {
    public static Document createDocument(InputStream inputStream) {
        Document document = null;
        try {
            SAXReader reader = new SAXReader();
            document = reader.read(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }
}
