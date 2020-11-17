package com.mashibing.tank.frame;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/***********************
 * Description: new 一个窗口 <BR>
 * @author: zhao.song
 * @date: 2020/11/13 15:27
 * @version: 1.0
 ***********************/
public class T {

    public static void main(String[] args) {
        // 1.Frame 就是一个窗口类
        Frame f = new Frame();
        // 2.设置窗口参数
        // 2.1 设置窗口大小
        f.setSize(800, 600);
        // 2.2 设置窗口是否可改变大小
        f.setResizable(true);
        // 2.3 设置窗口名
        f.setTitle("tank");

        // 3.设置窗口可见
        f.setVisible(true);

        // 4.添加一个window监听器
        f.addWindowListener(new WindowAdapter() {

            // 4.1监听windowclosing这个事件
            @Override
            public void windowClosing(WindowEvent e) {
//                super.windowClosing(e);
                // 系统退出
                System.exit(0);
            }
        });

    }
}
