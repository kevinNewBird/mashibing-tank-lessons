package com.mashibing.dm.command.demo2.pattern.impl;

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
}
