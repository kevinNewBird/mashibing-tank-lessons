package com.mashibing.dm.observer.demo1.pattern.base;

import lombok.Getter;
import lombok.Setter;

public class Reader implements Observer {
    /**
     * 读者的姓名
     */
    @Getter
    @Setter
    private String name;

    @Override
    public void update(Subject subject) {
        // 这是采用拉的方式
        System.out.printf("%s收到报纸了，阅读它，内容是====%s\n", name, ((NewsPaper) subject).getContent());
    }
}
