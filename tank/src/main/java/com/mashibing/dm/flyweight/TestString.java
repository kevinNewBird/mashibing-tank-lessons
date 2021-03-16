package com.mashibing.dm.flyweight;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/3/16 22:27
 * @version: 1.0
 ***********************/
public class TestString {

    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "abc";
        String s3 = new String("abc");
        String s4 = new String("abc");

        //字符串内部也是维护了一个池子
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s3 == s4);
        System.out.println(s3.intern() == s1);
        System.out.println(s3.intern() == s4.intern());
    }
}
