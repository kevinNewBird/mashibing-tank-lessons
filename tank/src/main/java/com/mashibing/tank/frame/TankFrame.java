package com.mashibing.tank.frame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.ListIterator;

/***********************
 * Description: 继承frame窗口类 <BR>
 * @author: zhao.song
 * @date: 2020/11/13 16:10
 * @version: 1.0
 ***********************/
public class TankFrame extends Frame {

    //窗口宽度
    public static final int GAME_WIDTH = 800;
    //窗口高度
    public static final int GAME_HEIGHT = 600;

    Tank mainTank = new Tank(200, 200, 10, Dir.DOWN, this);

    //子弹容器,实现多个字段的输出
    java.util.List<Bullet> bulletContainer = new ArrayList<Bullet>();

    Bullet b = new Bullet(300, 200, Dir.DOWN, this);


    public TankFrame() throws HeadlessException {
        // 2.设置窗口参数
        // 2.1 设置窗口大小
        setSize(GAME_WIDTH, GAME_HEIGHT);
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

    //定义一个图片
    Image offScreenImage = null;

    // 用双缓冲解决闪烁问题:在paint方法之前调用(repaint->update->paint)
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        //将图片渲染完成,整体输出到客户显示界面,从而达到双缓冲的效果:https://blog.csdn.net/jxw167/article/details/72157154
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        //开始真正的输出图片的描绘
        paint(gOffScreen);
        //图片描绘完成后,完整的输出到显示窗口
        g.drawImage(offScreenImage, 0, 0, null);
    }


    Tank tank = new Tank(10, 60, Dir.RIGHT, this);
    //窗口需要重新绘制的时候,自动调用该方法(1.窗口第一次显示的时候,2.窗口被别人盖住又显示出来的时候,3.窗口改变大小的时候)
    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawString("子弹数量:" + bulletContainer.size(), 30, 50);
        g.setColor(c);
        // tip: 面向对象的思维:应该是将画笔递给坦克,坦克最知道该如何移动
        // ,而不是把tank的属性获取到再去设置
        mainTank.paint(g);

        tank.setMoving(true);
        tank.paint(g);
/*        bulletContainer.forEach(bullet -> {
            //思考问题: 打出去的子弹是否该移出容器? 应该怎样移出容器?
            //ans:1.不可以,因为每次都是重画窗口,如果移出会导致子弹消失;
            // 2.如果不移出,就需要考虑内存的优化,集合不可一直增大
            bullet.paint(g);
            if (bullet.getX() > this.GAME_WIDTH || bullet.getY() > this.GAME_HEIGHT) {
                bulletContainer.remove(bullet);
            }
        });*/
        // 1.可行解决方案一
/*        ListIterator<Bullet> iterator = bulletContainer.listIterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            bullet.paint(g);
            if (bullet.getX() > this.GAME_WIDTH || bullet.getY() > this.GAME_HEIGHT) {
                iterator.remove();
            }

        }*/
        // 2.可行解决方案二:
        for (int i = 0; i < bulletContainer.size(); i++) {
            bulletContainer.get(i).paint(g);

        }

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
                case KeyEvent.VK_CONTROL:
                    mainTank.fire();
                    bulletContainer.add(b);
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
//            Tank mainTank = getMainTank();
            if (!bL && !bU && !bR && !bD) {
                mainTank.setMoving(false);
            } else {
                mainTank.setMoving(true);
                if (bL) mainTank.setDir(Dir.LEFT);
                if (bU) mainTank.setDir(Dir.UP);
                if (bR) mainTank.setDir(Dir.RIGHT);
                if (bD) mainTank.setDir(Dir.DOWN);
            }

        }
    }

/*    public Tank getMainTank(){
        return this.mainTank;
    }*/


}
