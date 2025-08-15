package com.mashibing.dm.facade.demo1.base.model;

/**
 * 管理配置
 */
public class ConfigManager {

    private static ConfigManager manager;

    private static ConfigModel cm;

    public static ConfigManager getInstance(){
        if (manager == null){
            manager = SingletonHolder.instance;
            // 读取配置文件
            cm = new ConfigModel();
            cm.loadConfig();
        }
        return manager;
    }

    public ConfigModel getConfig(){
        return cm;
    }

    // 单例模式--懒加载
    private static class SingletonHolder {
        private static final ConfigManager instance = new ConfigManager();
    }
}
