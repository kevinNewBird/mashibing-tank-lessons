package com.mashibing.dm.observer.demo2.pattern.base;

import java.util.Observable;

/**
 * 报纸对象（实现java内置的目标实现接口）
 */
public class NewsPaper extends Observable {

    /**
     * 报纸的具体内容
     */
    private String content;

    /**
     * 获取报纸的具体内容
     * @return
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置报纸的具体内容，相当于要出版报纸了
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
        // 注意：在用java中的Observer模式的时候，下面这句话不可少
        this.setChanged();
        // 使用拉的方式（即传递整个subject对象, 观察者自行去决定获取什么内容，看起来是观察者拉取的数据）
        // 注意：推的方式，即明确知道观察者需要什么信息
        this.notifyObservers();

        // 推的方式
//        this.notifyObservers(this.content);
    }
}
