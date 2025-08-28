package com.mashibing.dm.command.demo1.pattern.impl;

/**
 * description：技嘉主板类，开机命令的真正实现者，在Command模式中充当Receiver接收者
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 18:57
 */
public class GigaMainBoard implements MainBoardApi{

    /**
     * 真正的开机命令实现
     */
    @Override
    public void open() {
        System.out.println("技嘉主板现在正在开机，请等候");
        System.out.println("接通电源......");
        System.out.println("设备检查......");
        System.out.println("装载系统......");
        System.out.println("机器正常运转起来......");
        System.out.println("机器已经正常打开，请操作");
    }

    /**
     * 真正的重启命令的实现
     */
    @Override
    public void reset() {
        System.out.println("技嘉主板现在正在重新启动机器，请等候");
        System.out.println("机器已经正常打开，请操作");
    }
}
