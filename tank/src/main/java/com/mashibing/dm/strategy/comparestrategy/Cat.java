package com.mashibing.dm.strategy.comparestrategy;

/***********************
 * Description: TODO <BR>
 * author: zhao.song
 * date: 2021/1/25 16:33
 * version: 1.0
 ***********************/
public class Cat implements MineComparable<Cat> {

    int height, weight;

    public Cat(int height, int weight) {
        this.height = height;
        this.weight = weight;
    }

    @Override
    public int compareTo(Cat c) {
        if (this.weight < c.weight) {
            return -1;
        } else if (this.weight > c.weight) {
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Cat{" +
                "height=" + height +
                ", weight=" + weight +
                '}';
    }
}
