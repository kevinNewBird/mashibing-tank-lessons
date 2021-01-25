package com.mashibing.dm.strategy;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

/***********************
 * Description: TODO <BR>
 * author: zhao.song
 * date: 2021/1/25 16:34
 * version: 1.0
 ***********************/
public class Main {

    public static void main(String[] args) {
//        Integer[] array = Stream.of(9, 1, 5, 3, 456, 76).toArray(Integer[]::new);
        Cat c0 = new Cat(4, 4);
        Cat c1 = new Cat(2, 2);
        Cat c2 = new Cat(3, 3);
//        Cat[] array = Stream.of(c0, c1, c2).toArray(Cat[]::new);
        Cat[] array = Stream.of(c0, c1, c2).toArray(size->{
            System.out.println(size);
            return new Cat[size];
        });

        Sorter.sort(array);
        System.out.println(Arrays.toString(array));


        Dog d0 = new Dog(1);
        Dog d1 = new Dog(3);
        Dog d2 = new Dog(2);
        Dog d3 = new Dog(0);

        Dog[] array1 = Stream.of(d0, d1, d2, d3).toArray(Dog[]::new);
        Dog[] array2 = Arrays.copyOf(array1, array1.length);
        Sorter.sort(array1);
        System.out.println(Arrays.toString(array1));
        System.out.println(String.join(":", "比较策略前:",Arrays.toString(array2)));

        //上述两种方式仍不是策略模式,对于比较而言仍不够灵活(比如我想使用猫的高度进行排序,就必须改变compareTo方法)

        //比较策略:
        Sorter<Dog> sorter = new Sorter<>();
        sorter.sort(array2,(o1,o2)->{
            if (o1.food < o2.food) {
                return -1;
            }else {
                return 1;
            }
        });
        System.out.println(String.join(":", "比较策略后:",Arrays.toString(array2)));
    }
}
