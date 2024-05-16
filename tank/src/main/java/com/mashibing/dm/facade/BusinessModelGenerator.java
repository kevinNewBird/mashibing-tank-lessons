package com.mashibing.dm.facade;

public class BusinessModelGenerator {

    public void process() {
        if (ConfigManager.getInstance().getConfigData().isGenBusiness()) {
            System.out.println("生成业务层代码。。。。");
        }

    }
}
