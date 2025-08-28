package com.mashibing.dm.memento.demo1.pattern.v2.impl;


import lombok.Getter;

/**
 * description：模拟运行流程A，只是一个示意，代指某个具体流程
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/28 18:10
 */
public class FlowAMockPrototype implements Cloneable {

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
    public FlowAMockPrototype(String flowName) {
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

    /**
     * 创建保存原发器对象状态的备忘录对象
     *
     * @return FlowAMockMemento：创建好的备忘录对象
     */
    public FlowAMockMemento createMemento() {
        try {
            return new MementoPrototypeImpl((FlowAMockPrototype) this.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 重新设置原发器对象的状态，让其回到备忘录对象记录的状态
     *
     * @param memento：记录有原发器状态的备忘录对象
     */
    public void setMemento(FlowAMockMemento memento) {
        MementoPrototypeImpl mementoImpl = (MementoPrototypeImpl) memento;
        this.tempResult = mementoImpl.getFlowA().tempResult;
        this.tempState = mementoImpl.getFlowA().tempState;
    }

//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        // 都是基础类型，无需处理
//        return super.clone();
//    }

    /**
     * 真正的备忘录对象，实现备忘录窄接口
     * 实现成私有的内部类，不让外部访问
     */
    private static class MementoPrototypeImpl implements FlowAMockMemento {

        @Getter
        private final FlowAMockPrototype flowA;

        public MementoPrototypeImpl(FlowAMockPrototype flowA) {
            this.flowA = flowA;
        }
    }

}
