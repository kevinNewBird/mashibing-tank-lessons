package com.mashibing.dm.observer.demo2.pattern.base;

import lombok.Getter;
import lombok.Setter;

import java.util.Observable;
import java.util.Observer;

public class Reader implements Observer {

    /**
     * 读者的姓名
     */
    @Getter
    @Setter
    private String name;


    /**
     * 被通知的方法
     * @param o     the observable object： 拉方式的数据
     * @param obj   an argument passed to the <code>notifyObservers</code>
     *                 method： 推方式的数据
     */
    @Override
    public void update(Observable o, Object obj) {
        // 这是获取拉的数据
        System.out.printf("%s收到报纸了，阅读先。主动到目标对象去拉的内容是====%s\n", name, ((NewsPaper) o).getContent());

        // 这是采用推的方式
//        System.out.printf("%s收到报纸了，阅读先。目标推过来的内容是====%s\n", name, obj);
    }
}
