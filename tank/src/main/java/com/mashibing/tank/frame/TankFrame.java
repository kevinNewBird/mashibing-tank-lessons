package com.mashibing.tank.frame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;

/***********************
 * Description: 继承frame窗口类 <BR>
 * @author: zhao.song
 * @date: 2020/11/13 16:10
 * @version: 1.0
 ***********************/
public class TankFrame extends Frame {

    Tank mainTank = new Tank(200, 200, 10, Dir.DOWN);


    public TankFrame() throws HeadlessException {
        // 2.设置窗口参数
        // 2.1 设置窗口大小
        setSize(800, 600);
        // 2.2 设置窗口是否可改变大小
        setResizable(true);
        // 2.3 设置窗口名
        setTitle("tank");

        // 3.设置窗口可见
        setVisible(true);

        addKeyListener(new TankFrame.MyKeyListener());
        // 4.添加一个window监听器
        addWindowListener(new WindowAdapter() {

            // 4.1监听windowclosing这个事件
            @Override
            public void windowClosing(WindowEvent e) {
//                super.windowClosing(e);
                // 系统退出
                System.exit(0);
            }
        });
    }

    //窗口需要重新绘制的时候,自动调用该方法(1.窗口第一次显示的时候,2.窗口被别人盖住又显示出来的时候,3.窗口改变大小的时候)
    @Override
    public void paint(Graphics g) {
        // tip: 面向对象的思维:应该是将画笔递给坦克,坦克最知道该如何移动
        // ,而不是把tank的属性获取到再去设置
        mainTank.paint(g);
    }


    public class MyKeyListener extends KeyAdapter {

        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        // 1.键按下
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        // 2.键释放
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (bL) dir = Dir.LEFT;
            if (bU) dir = Dir.UP;
            if (bR) dir = Dir.RIGHT;
            if (bD) dir = Dir.DOWN;
        }
    }


}
