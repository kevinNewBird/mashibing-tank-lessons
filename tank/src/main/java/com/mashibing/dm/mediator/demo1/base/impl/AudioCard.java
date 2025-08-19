package com.mashibing.dm.mediator.demo1.base.impl;

import com.mashibing.dm.mediator.demo1.base.Colleague;
import com.mashibing.dm.mediator.demo1.base.Mediator;

public class AudioCard extends Colleague {

    public AudioCard(Mediator mediator) {
        super(mediator);
    }

    public void showData(String data) {
        System.out.println("画外音：" + data);
    }
}
