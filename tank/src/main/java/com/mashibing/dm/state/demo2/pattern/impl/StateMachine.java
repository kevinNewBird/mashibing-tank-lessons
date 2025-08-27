package com.mashibing.dm.state.demo2.pattern.impl;

import lombok.Data;

/**
 * description：公共状态处理机，相当于状态模式的Context
 *
 * 包含所有流程使用状态模式时的公共功能
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 17:13
 */
@Data
public class StateMachine {

    /**
     * 持有一个状态对象
     */
    private State state;

    /**
     * 包含流程处理需要的业务数据对象，不知道具体类型
     * 为了简单，不使用泛型用 Object， 方正只是传递到具体的状态对象中
     */
    private Object businessVO;

    /**
     * 执行工作，客户端处理流程的接口方法
     * 在客户完成自己的业务工作后调用
     */
    public void doWork(){
        // 转调相应的状态对象真正完成功能处理
        this.state.doWork(this);
    }


}
