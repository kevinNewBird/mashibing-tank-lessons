package com.mashibing.dm.facade.demo1.base.generator.impl;

import com.mashibing.dm.facade.demo1.base.generator.IControllerGenerator;
import com.mashibing.dm.facade.demo1.base.model.ConfigManager;
import com.mashibing.dm.facade.demo1.base.model.ConfigModel;

/**
 * 表现层生成器
 */
public class ControllerGenerator implements IControllerGenerator {

    public void generate(){
        ConfigModel cm = ConfigManager.getInstance().getConfig();
        if (cm.isGenerateController()){
            System.out.println("正在生成表现层代码文件...");
        }
    }
}
