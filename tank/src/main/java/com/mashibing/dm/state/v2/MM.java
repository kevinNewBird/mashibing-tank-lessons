package com.mashibing.dm.state.v2;

/***********************
 * @Description: 使用状态模式<BR>
 * @author: zhao.song
 * @since: 2021/4/4 21:36
 * @version: 1.0
 ***********************/
public class MM {

    String name;

    private MMState state;

    public void smile() {
        state.smile();
    }

    public void cry() {
        state.cry();
    }

    private void say() {
        state.say();
    }
}
