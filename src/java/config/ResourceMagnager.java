/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import org.apache.log4j.Logger;
import org.jconfig.Configuration;

/**
 *
 * @author TUANPLA
 */
public class ResourceMagnager {

    public static Configuration config;
    static Logger logger = Logger.getLogger(ResourceMagnager.class);

    public static int getInt(String properties, int defaultVal, String categoryName) {
        try {
            return Integer.parseInt(config.getProperty(properties, defaultVal + "", categoryName));
        } catch (Exception e) {
            logger.error(e);
            return defaultVal;
        }
    }

    public static long getLong(String properties, long defaultVal, String categoryName) {
        try {
            return Long.parseLong(config.getProperty(properties, defaultVal + "", categoryName));
        } catch (Exception e) {
            logger.error(e);
            return defaultVal;
        }
    }

    public static Double getDouble(String properties, Double defaultVal, String categoryName) {
        try {
            return Double.parseDouble(config.getProperty(properties, defaultVal + "", categoryName));
        } catch (Exception e) {
            logger.error(e);
            return defaultVal;
        }
    }

    public static String getString(String properties, String defaultVal, String categoryName) {
        try {
            return config.getProperty(properties, defaultVal, categoryName);
        } catch (Exception e) {
            logger.error(e);
            return defaultVal;
        }
    }
}
