package com.mashibing.dm.memento.demo1.pattern.v2;

import com.mashibing.dm.memento.demo1.pattern.v2.impl.FlowAMementoCareTaker;
import com.mashibing.dm.memento.demo1.pattern.v2.impl.FlowAMockPrototype;
import com.mashibing.dm.memento.demo1.pattern.v2.impl.FlowAMockMemento;

/**
 * description：模拟运行流程客户端（备忘录模式）
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/28 22:32
 */
public class FlowAMementoPrototypeClient {

    public static void main(String[] args) {
        // 1.创建模拟运行流程的对象
        FlowAMockPrototype mock = new FlowAMockPrototype("TestFlow");
        // 2.运行流程的第一个阶段
        mock.runPhaseOne();
        // 3.创建备忘录管理者, 并保存备忘录
        FlowAMementoCareTaker manager = new FlowAMementoCareTaker();
        FlowAMockMemento memento = mock.createMemento();
        manager.saveMemento(memento);

        // 4.按照方案一来运行流程后半部分
        mock.schema1();

        // 5.恢复备忘录保存状态
        mock.setMemento(manager.retrieveMemento());

        // 6.按照方案二来运行流程后半部分
        mock.schema2();

        /**
         * PhaseOne,Schema1: now run 3
         * PhaseOne,Schema2: now run 3
         */

    }
}
