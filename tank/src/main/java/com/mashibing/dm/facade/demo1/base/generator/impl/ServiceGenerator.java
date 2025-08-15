package com.mashibing.dm.facade.demo1.base.generator.impl;

import com.mashibing.dm.facade.demo1.base.generator.IServiceGenerator;
import com.mashibing.dm.facade.demo1.base.model.ConfigManager;
import com.mashibing.dm.facade.demo1.base.model.ConfigModel;

/**
 * 逻辑层生成器
 */
public class ServiceGenerator implements IServiceGenerator {

    public void generate() {
        ConfigModel cm = ConfigManager.getInstance().getConfig();
        if (cm.isGenerateService()) {
            System.out.println("正在生成逻辑层代码文件...");
        }
    }
}
