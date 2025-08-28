package com.mashibing.dm.memento.demo1.source;

import com.mashibing.dm.memento.demo1.source.impl.FlowAMock;

/**
 * description：模拟运行流程客户端
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/28 20:23
 */
public class FlowAClient {


    /**
     * 存在两个问题：
     * 1.内部数据是零散着在外部存放的
     * 2.内部数据暴露出来，破坏了封装
     * @param args
     */
    public static void main(String[] args) {
        // 1.创建模拟运行流程的对象
        FlowAMock mock = new FlowAMock("TestFlow");
        // 2.运行流程的第一个阶段
        mock.runPhaseOne();
        // 3.得到第一个阶段运行所产生的数据
        int tempResult = mock.getTempResult();
        String tempState = mock.getTempState();

        // 4.按照方案一来运行流程后半部分
        mock.schema1();

        // 5.把第一个阶段运行所产生的数据重新设置回去
        mock.setTempResult(tempResult);
        mock.setTempState(tempState);

        // 6.按照方案二来运行流程后半部分
        mock.schema2();

        /**
         * PhaseOne,Schema1: now run 3
         * PhaseOne,Schema2: now run 3
         */
    }
}
