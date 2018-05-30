package com.gua.homework.common.util;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigUtil
{

    private static Properties properties = new Properties();
    private final static String configFile = "/config/meishanshi.properties";
    final static Logger logger = LoggerFactory.getLogger(ConfigUtil.class);

    private static void loadConfig()
    {

        if (!properties.isEmpty())
        {
            return;
        }

        try
        {
            InputStream in = ConfigUtil.class.getResourceAsStream(configFile);
            if (in != null)
            {
                properties.load(in);
            }
            in.close();
        }
        catch (Exception e)
        {
            logger.error(">> Config load config:" + configFile + " encounter error", e);
        }
    }

    public static String getValue(String key)
    {

        loadConfig();

        String value = (String) properties.get(key);
        logger.debug(">> Config value >> {}:{}", new Object[] {key, value});

        return value;
    }
    
    public static String getValueWithParameter(String key, String[] parameterArr)
    {
    	String value = getValue(key);
		
		value = WarningUtil.packingErrorMsg(value, parameterArr);
    	
    	return value;
    }

    public static int getIntValue(String key)
    {

        loadConfig();

        String value = (String) properties.get(key);

        int x = 0;
        try
        {
            x = Integer.parseInt(value);
        }
        catch (Exception e)
        {
            logger.error("Config parse config value >> {}:{} encounter error, exception:{}",
                    new Object[] {key, value, e.toString()});
        }

        logger.debug(">> Config value >> {}:{}", new Object[] {key, value});
        return x;
    }
    
    public static boolean getBooleanValue(String key) {
    	
    	loadConfig();

        String value = (String) properties.get(key);

        logger.debug(">> Config value >> {}:{}", new Object[] {key, value});
        
		if (value != null && value.trim().length() > 0) {
			return "YES".equalsIgnoreCase(value);
		}
		return false;
	}
}
