package com.mashibing.dm.observer.v9;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/3/16 0:08
 * @version: 1.0
 ***********************/
public class TestFrame extends Frame {

    public static void main(String[] args) {
        new TestFrame().launch();
    }

    public void launch(){
        Button b = new Button("press me");
        b.addActionListener(new MyActionListener());
        b.addActionListener(new MyActionListener2());
        //将按钮加入到窗口
        this.add(b);
        //将按钮包裹起来(与窗口大小保持一致)
        this.pack();

        //窗口监听器
        this.addWindowListener(new WindowAdapter() {
            //监听windowclosing这个事件
            @Override
            public void windowClosed(WindowEvent e) {
                //系统退出
                System.out.println("系统退出!");
                System.exit(0);
            }
        });

        b.setLocation(400,400);
        b.setVisible(true);
    }


    class MyActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("pressed button");
        }
    }
    class MyActionListener2 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("pressed button2");
        }
    }

}
