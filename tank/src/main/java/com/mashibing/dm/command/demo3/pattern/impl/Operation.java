package com.mashibing.dm.command.demo3.pattern.impl;

import lombok.Getter;

/**
 * description：运算类，真正实现加减法运算
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 23:07
 */
public class Operation implements OperationApi {

    /**
     * 记录运算的结果
     */
    private int result;


    @Override
    public int getResult() {
        return this.result;
    }

    @Override
    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public void add(int num) {
        // 实现加法功能
        result += num;
    }

    @Override
    public void substract(int num) {
        // 实现减法的功能
        result -= num;
    }


    @Override
    public CalcMemento createMemento() {
        return new MementoImpl(this.result);
    }

    @Override
    public void setMemento(CalcMemento memento) {
        this.result = ((MementoImpl) memento).getResult();
    }


    private static class MementoImpl implements CalcMemento {
        @Getter
        private final int result;

        public MementoImpl(int result) {
            this.result = result;
        }
    }

}
