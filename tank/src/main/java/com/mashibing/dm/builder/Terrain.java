package com.mashibing.dm.builder;

/***********************
 * @Description: 地形实例 <BR>
 * @author: zhao.song
 * @since: 2021/3/31 0:20
 * @version: 1.0
 ***********************/
public class Terrain {

    /**
     * 坦克的地形-terrain：
     * 墙-wall
     * 堡垒-fort
     * 地雷-mine
     */
    Wall w;
    Fort f;
    Mine m;

    @Override
    public String toString() {
        return "Terrain{" +
                "w=" + w +
                ", f=" + f +
                ", m=" + m +
                '}';
    }
}

class Wall{
    int x,y,w, h;

    public Wall(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public String toString() {
        return "Wall{" +
                "x=" + x +
                ", y=" + y +
                ", w=" + w +
                ", h=" + h +
                '}';
    }
}

class Fort{
    int x,y,w, h;

    public Fort(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public String toString() {
        return "Fort{" +
                "x=" + x +
                ", y=" + y +
                ", w=" + w +
                ", h=" + h +
                '}';
    }
}

class Mine{
    int x,y,w, h;

    public Mine(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public String toString() {
        return "Mine{" +
                "x=" + x +
                ", y=" + y +
                ", w=" + w +
                ", h=" + h +
                '}';
    }
}
