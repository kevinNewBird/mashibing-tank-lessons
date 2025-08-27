package com.mashibing.dm.state.demo2.pattern.impl;

/**
 * description：公共状态接口
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 17:14
 */
public interface State {

    /**
     * 执行状态对应的功能处理
     * @param ctx： 上下文的实例对象
     */
    void doWork(StateMachine ctx);

}
