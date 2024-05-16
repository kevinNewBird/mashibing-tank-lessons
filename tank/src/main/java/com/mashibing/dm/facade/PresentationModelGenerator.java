package com.mashibing.dm.facade;

public class PresentationModelGenerator {

    public void process() {
        if (ConfigManager.getInstance().getConfigData().isGenPresentation()) {
            System.out.println("生成表现层代码。。。。");
        }

    }
}
