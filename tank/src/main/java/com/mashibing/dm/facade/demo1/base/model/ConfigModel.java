package com.mashibing.dm.facade.demo1.base.model;

import lombok.Data;

/**
 * description: 配置文件
 */
@Data
public class ConfigModel {

    // 是否生成表现层模块
    private boolean generateController;

    // 是否生成逻辑层模块
    private boolean generateService;

    // 是否生成数据层模块
    private boolean generateDao;

    public void loadConfig(){
        this.generateController = true;
        this.generateService = true;
        this.generateDao = true;
    }
}
