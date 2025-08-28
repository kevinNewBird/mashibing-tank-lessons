package com.mashibing.dm.command.demo4.pattern.impl;

/**
 * description：命令接口，声明执行的操作
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/28 09:32
 */
public interface Command {

    /**
     * 执行命令对应的操作
     */
    public void execute();

    /**
     * 设置命令的接收者
     *
     * @param cookApi：命令的接收者
     */
    public void setCookApi(CookApi cookApi);

    /**
     * 返回发起请求的桌号，就是点菜的桌号
     *
     * @return：发起请求的桌号
     */
    public int getTableNum();
}
