package com.mashibing.dm.observer.demo1.pattern;

import com.mashibing.dm.observer.demo1.pattern.base.NewsPaper;
import com.mashibing.dm.observer.demo1.pattern.base.Reader;

public class ObserverClient {

    public static void main(String[] args) {
        // 1.创建一个报纸，作为被观察者
        NewsPaper subject = new NewsPaper();

        // 2.创建阅读者，也就是观察者
        Reader reader1 = new Reader();
        reader1.setName("张三");
        Reader reader2 = new Reader();
        reader2.setName("李四");

        // 3.注册
        subject.attach(reader1);
        subject.attach(reader2);

        // 4.模拟报纸出版
        subject.setContent("本期内容是观察者模式！");
    }
}
