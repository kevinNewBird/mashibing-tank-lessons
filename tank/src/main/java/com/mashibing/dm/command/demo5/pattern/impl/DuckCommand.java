package com.mashibing.dm.command.demo5.pattern.impl;

/**
 * description：命令对象，酸菜老鸭汤
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/28 09:34
 */
public class DuckCommand implements Command {

    /**
     * 持有具体做菜的厨师对象（Receiver）
     */
    private CookApi cookApi;

    /**
     * 设置具体做菜的厨师对象
     *
     * @param cookApi： 具体做菜的厨师对象
     */
    public void setCookApi(CookApi cookApi) {
        this.cookApi = cookApi;
    }

    public DuckCommand(int tableNum) {
        this.tableNum = tableNum;
    }

    /**
     * 点菜桌号
     */
    private int tableNum;

    @Override
    public int getTableNum() {
        return tableNum;
    }

    @Override
    public void execute() {
        this.cookApi.cook(this.tableNum, "酸菜老鸭汤");
    }
}
