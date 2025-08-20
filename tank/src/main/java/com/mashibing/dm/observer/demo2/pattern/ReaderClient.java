package com.mashibing.dm.observer.demo2.pattern;

import com.mashibing.dm.observer.demo2.pattern.base.NewsPaper;
import com.mashibing.dm.observer.demo2.pattern.base.Reader;

public class ReaderClient {
    public static void main(String[] args) {
        // 1.创建一个报纸，作为被观察者
        NewsPaper subject = new NewsPaper();

        // 2.创建阅读者，也就是观察者
        Reader reader1 = new Reader();
        reader1.setName("张三");
        Reader reader2 = new Reader();
        reader2.setName("李四");

        // 3.注册阅读者
        subject.addObserver(reader1);
        subject.addObserver(reader2);

        // 4.模拟报纸出版
        subject.setContent("本期内容是观察者模式！");
    }

}
