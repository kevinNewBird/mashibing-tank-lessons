package com.mashibing.dm.observer.demo1.pattern.base;

/**
 * 报纸对象，具体的目标实现
 */
public class NewsPaper extends Subject{

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

        notifyObservers();
    }
}
