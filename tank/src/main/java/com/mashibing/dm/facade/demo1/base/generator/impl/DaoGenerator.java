package com.mashibing.dm.facade.demo1.base.generator.impl;

import com.mashibing.dm.facade.demo1.base.generator.IDaoGenerator;
import com.mashibing.dm.facade.demo1.base.model.ConfigManager;
import com.mashibing.dm.facade.demo1.base.model.ConfigModel;

/**
 * 数据层生成器
 */
public class DaoGenerator implements IDaoGenerator {

    public void generate() {
        ConfigModel cm = ConfigManager.getInstance().getConfig();
        if (cm.isGenerateController()) {
            System.out.println("正在生成数据层代码文件...");
        }
    }
}
