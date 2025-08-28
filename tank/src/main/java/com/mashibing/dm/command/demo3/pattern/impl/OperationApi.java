package com.mashibing.dm.command.demo3.pattern.impl;

/**
 * description：操作运算的接口
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 23:04
 */
public interface OperationApi {

    /**
     * 获取计算完成后的结果
     * @return
     */
    int getResult();

    /**
     * 设置计算开始的初始值
     * @param result: 计算开始的初始值
     */
    void setResult(int result);

    /**
     * 执行加法
     * @param num： 需要加的数
     */
    void add(int num);

    /**
     * 执行减法
     * @param num：需要减的数
     */
    void substract(int num);

    // 重新设置原发器对象的状态，让其回到备忘录对象记录的状态
    void setMemento(CalcMemento memento);

    // 创建保存原发器对象状态的备忘录对象
    CalcMemento createMemento();
}
