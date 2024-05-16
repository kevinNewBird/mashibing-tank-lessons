package com.mashibing.dm.facade;

public class DAOModelGenerator {

    public void process() {
        if (ConfigManager.getInstance().getConfigData().isGenDAO()) {
            System.out.println("生成数据层代码。。。。");
        }

    }
}
