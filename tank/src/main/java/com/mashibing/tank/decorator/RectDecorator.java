package com.mashibing.tank.decorator;

import com.mashibing.tank.frame.GameObject;

import java.awt.*;

/***********************
 * @Description: 外壳装饰类 <BR>
 * @author: zhao.song
 * @since: 2021/3/10 23:35
 * @version: 1.0
 ***********************/
public class RectDecorator extends GODecorator {

    public RectDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.x = go.x;
        this.y = go.y;
        this.width = go.width;
        this.height = go.height;
        go.paint(g);
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawRect(this.x, this.y, this.width + 2, this.height + 2);
        g.setColor(c);
    }
}
