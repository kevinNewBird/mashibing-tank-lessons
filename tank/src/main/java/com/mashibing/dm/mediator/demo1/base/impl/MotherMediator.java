package com.mashibing.dm.mediator.demo1.base.impl;

import com.mashibing.dm.mediator.demo1.base.Colleague;
import com.mashibing.dm.mediator.demo1.base.Mediator;
import lombok.Setter;


public class MotherMediator implements Mediator {

    @Setter
    private CdDriver cdDriver;
    @Setter
    private Cpu cpu;
    @Setter
    private VideoCard videoCard;
    @Setter
    private AudioCard audioCard;

    @Override
    public void changed(Colleague colleague) {
        if (colleague instanceof CdDriver) {
            // 表示光驱读完数据了
            handleCdDriver((CdDriver) colleague);
        }else if (colleague instanceof Cpu) {
            // 表示cpu处理好数据了
            handleCpu((Cpu) colleague);
        }
    }

    private void handleCdDriver(CdDriver cd) {
        // 1.获取光驱读取的数据
        String data = cd.getData();
        // 2.把这些数据传递给cpu 进行处理
        this.cpu.exec(data);
    }

    private void handleCpu(Cpu cpu) {
        // 1.先获取cpu处理后的数据
        String audioData = cpu.getAudioData();
        String videoData = cpu.getVideoData();
        // 2.把这些数据传递给显卡和声卡展示出来
        this.videoCard.showData(videoData);
        this.audioCard.showData(audioData);
    }
}
