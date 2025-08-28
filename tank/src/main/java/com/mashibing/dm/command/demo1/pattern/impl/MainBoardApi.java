package com.mashibing.dm.command.demo1.pattern.impl;

/**
 * description：主板的接口，相当于接收者对象
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 18:53
 */
public interface MainBoardApi {

    /**
     * 主板具有能开机的功能
     */
    public void open();

    /**
     * 主板具有重启的功能
     */
    public void reset();
}
