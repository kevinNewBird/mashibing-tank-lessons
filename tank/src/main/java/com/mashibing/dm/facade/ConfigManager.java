package com.mashibing.dm.facade;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

public class ConfigManager {

    private ConfigModel configData;

    private ConfigManager() {
        // 省略，加载配置文件到内存
        configData = new ConfigModel();
    }

    public static ConfigManager getInstance() {
        return ConfigManagerHolder.INSTANCE;
    }

    public ConfigModel getConfigData() {
        return this.configData;
    }

    private static class ConfigManagerHolder {
        public static final ConfigManager INSTANCE = new ConfigManager();
    }
}
