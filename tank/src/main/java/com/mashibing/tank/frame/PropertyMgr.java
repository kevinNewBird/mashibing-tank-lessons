package com.mashibing.tank.frame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

/***********************
 * Description: 配置文件管理类 <BR>
 * author: zhao.song
 * date: 2021/1/19 23:01
 * version: 1.0
 ***********************/
public class PropertyMgr {

    private static Logger logger = LoggerFactory.getLogger(PropertyMgr.class);

    static Properties props =  new Properties();

    static {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            logger.error("加载配置文件异常!", e);
        }
    }

    public static Optional<Object> get(String key) {
        if (props == null) {
            return Optional.empty();
        }
        return props.get(key) != null ? Optional.of(props.get(key)) : Optional.empty();
    }

    public static int getInt(String key) {
        int value = -1;
        try {
            if ((get(key).isPresent())) {
                value = Integer.parseInt(get(key).get().toString());
            }
        } catch (NumberFormatException e) {
            logger.error("getting [" + key + "] property occur exception!",e);
        }
        return value;
    }

    public static String getString(String key){
        String value = null;
        try {
            if ((get(key).isPresent())) {
                value =get(key).get().toString();
            }
        } catch (Exception e) {
            logger.error("getting [" + key + "] property occur exception!",e);
        }
        return value;
    }

    public static void main(String[] args) {
        if (get("initTankCount2").isPresent()) {
            System.out.println(get("initTankCount2").get());
        }
        System.out.println(get("initTankCount2").get());
    }
}
