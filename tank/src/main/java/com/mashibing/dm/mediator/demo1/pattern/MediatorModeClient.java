package com.mashibing.dm.mediator.demo1.pattern;

import com.mashibing.dm.mediator.demo1.base.impl.*;

/**
 * 标准的中介者模式。
 * 具有的特点：
 * - 1.同事对象共同实现一个公共的父类（Colleague）
 * - 2.同事类持有中介者对象
 * - 3.中介者对象持有所有的同事对象
 * - 4.中介者对象提供一个公共的方法来接受同事对象的通知
 *
 * 思考：
 * - 1.实际开发中，很多相互交互的对象是没有公共父类的
 * - 2.同事类持有中介者对象是否有必要（单例？）
 * - 3.中介者对象持有所有同事，属性强烈依赖，是否有必要？
 * - 4.在公共方法里，还是要去区分到谁调过来，这还是简单的，还没有去区分到底是什么样的业务触发调用过来的，
 *    因为不同的业务，引起的与其他对象的交互是不一样的。
 *
 * 基于上面的考虑：
 * 在实际应用开发中，经常会简化中介者模式，来使开发变得简单，比如有如下的简化。
 * 1.通常会去掉同事对象的父类，这样可以让任意的对象，只要需要相互交互，就可以成为同事。
 * 2.通常不定义Mediator接口，把具体的中介者对象实现成为单例。
 * 3.同事对象不再持有中介者，而是在需要的时候直接获取中介者对象并调用；中介者也不再持有同事对象，而是在具体处理方法里面去创建，或者获取，或者从参数传入需要的同事对象。
 */
public class MediatorModeClient {

    public static void main(String[] args) {
        // 1.创建中介者--主板对象
        MotherMediator mediator = new MotherMediator();

        // 2.创建同事（组件）类
        CdDriver cd = new CdDriver(mediator);
        Cpu cpu = new Cpu(mediator);
        VideoCard vc = new VideoCard(mediator);
        AudioCard ac = new AudioCard(mediator);

        // 3.让中介者知道所有的同事
        mediator.setCdDriver(cd);
        mediator.setCpu(cpu);
        mediator.setVideoCard(vc);
        mediator.setAudioCard(ac);

        // 4.开始看电影，把光盘放入光驱，光驱开始读盘
        cd.readCD();

        /**
         * 您正观看的是：阿凡达3
         * 画外音：为了部落
         */
    }
}
