package com.mashibing.dm.mediator.demo1.base.impl;

import com.mashibing.dm.mediator.demo1.base.Colleague;
import com.mashibing.dm.mediator.demo1.base.Mediator;

public class CdDriver extends Colleague {

    /**
     * 读取到的电影数据（视频,画外音）
     */
    private String data;

    public CdDriver(Mediator mediator) {
        super(mediator);
    }

    public String getData() {
        return data;
    }

    public void readCD(){
        // 1.视频,画外音
        // 逗号前是视频，逗号后是画外音
        this.data = "阿凡达3,为了部落";

        // 2.通知中介者，自己的状态发生了变化
        getMediator().changed(this);
    }
}
