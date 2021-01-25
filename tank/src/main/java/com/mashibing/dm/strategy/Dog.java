package com.mashibing.dm.strategy;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @date: 2021/1/25 22:22
 * @version: 1.0
 ***********************/
public class Dog implements MineComparable<Dog>{

    int food;

    public Dog(int food) {
        this.food = food;
    }

    @Override
    public int compareTo(Dog dog) {
        if (this.food > dog.food) {
            return -1;
        }else{
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Dog{" +
                "food=" + food +
                '}';
    }
}
