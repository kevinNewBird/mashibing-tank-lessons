package com.mashibing.dm.memento.demo1.source.impl;

import lombok.Data;

/**
 * description：模拟运行流程A，只是一个示意，代指某个具体流程
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/28 18:10
 */
@Data
public class FlowAMock {

    /**
     * 流程名称，不需要外部存储的状态数据
     */
    private String flowName;

    /**
     * 示意，代指某个中间结果，需要外部存储的状态数据
     */
    private int tempResult;

    /**
     * 示意，代指某个中间结果，需要外部存储的状态数据
     */
    private String tempState;

    /**
     * 构造方法
     *
     * @param flowName：流程名称
     */
    public FlowAMock(String flowName) {
        this.flowName = flowName;
    }

    /**
     * 示意，运行流程的第一阶段
     */
    public void runPhaseOne() {
        // 在这个阶段，可能产生了中间结果，示意一下
        this.tempResult = 3;
        this.tempState = "PhaseOne";
    }

    /**
     * 示意，按照方案一来运行流程的后半部分
     */
    public void schema1() {
        // 示意，需要使用第一阶段产生的数据
        this.tempState += ",Schema1";
        System.out.printf("%s: now run %s\n", this.tempState, this.tempResult);
        this.tempResult += 11;
    }

    /**
     * 示意，按照方案二来运行流程的后半部分
     */
    public void schema2() {
        // 示意，需要使用第一阶段产生的数据
        this.tempState += ",Schema2";
        System.out.printf("%s: now run %s\n", this.tempState, this.tempResult);
        this.tempResult += 22;
    }

}
