package com.mashibing.tank.frame;

import com.mashibing.tank.factory.BaseExplode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/2/18 13:58
 * @version: 1.0
 ***********************/
public class RectExplode extends BaseExplode {
    private Logger logger = LoggerFactory.getLogger(Explode.class);

    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();


    TankFrame tf = null;

    private boolean isLiving;

    //主要用于确认爆炸的位置
    private int x, y;

    private int step;


    public RectExplode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Audio("audios/explode.wav").start();
        logger.info("目标被摧毁!");
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //如何画:使用一个常数记录步骤
    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
//        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        g.fillRect(x, y, 10 * step, 10 * step);
        step++;

        if (step >= 5) {
            this.tf.explodes.remove(this);
            return;
        }
        g.setColor(c);


    }
}
