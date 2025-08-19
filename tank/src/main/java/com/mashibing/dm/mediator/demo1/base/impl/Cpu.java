package com.mashibing.dm.mediator.demo1.base.impl;

import com.mashibing.dm.mediator.demo1.base.Colleague;
import com.mashibing.dm.mediator.demo1.base.Mediator;
import org.apache.commons.lang.StringUtils;

public class Cpu extends Colleague {

    private String videoData;

    private String audioData;

    public Cpu(Mediator mediator) {
        super(mediator);
    }

    public String getAudioData() {
        return audioData;
    }

    public String getVideoData() {
        return videoData;
    }

    public void exec(String data){
        String[] dataArray = StringUtils.splitByWholeSeparator(data, ",");
        this.videoData = dataArray[0];
        this.audioData = dataArray[1];

        getMediator().changed(this);
    }

}
