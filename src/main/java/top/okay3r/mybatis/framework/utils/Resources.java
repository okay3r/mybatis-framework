package top.okay3r.mybatis.framework.utils;

import java.io.InputStream;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/1/13
 * Time: 13:08
 * Explain:
 */
public class Resources {
    public static InputStream getResourcesAsStream(String location) {
        return Resources.class.getClassLoader().getResourceAsStream(location);
    }
}
