package com.mashibing.dm.mediator.demo1.base.impl;

import com.mashibing.dm.mediator.demo1.base.Colleague;
import com.mashibing.dm.mediator.demo1.base.Mediator;

/**
 * 显卡
 */
public class VideoCard extends Colleague {

    public VideoCard(Mediator mediator) {
        super(mediator);
    }

    public void showData(String data) {
        System.out.println("您正观看的是：" + data);
    }
}
