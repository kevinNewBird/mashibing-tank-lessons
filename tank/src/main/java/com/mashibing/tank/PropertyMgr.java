package com.mashibing.tank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;

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

    public static void main(String[] args) {
        if (get("initTankCount2").isPresent()) {
            System.out.println(get("initTankCount2").get());
        }
        System.out.println(get("initTankCount2").get());
    }
}
