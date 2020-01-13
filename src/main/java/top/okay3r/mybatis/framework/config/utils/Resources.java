package top.okay3r.mybatis.framework.config.utils;

import java.io.InputStream;

/**
 * Created By okay3r.top
 * top.okay3r.mybatis.framework.config.utils.Resources
 * User: okay3r
 * Date: 2020/1/13
 * Time: 13:08
 * Explain:
 */
public class Resources {
    public static InputStream getResourcesAsStream(String location) {
        return Resources.class.getClassLoader().getResourceAsStream(location);
    }
}
