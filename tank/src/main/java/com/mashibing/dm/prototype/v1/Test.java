package com.mashibing.dm.prototype.v1;

/***********************
 * @Description: 对象克隆v1:地址拷贝<BR>
 * @author: zhao.song
 * @since: 2021/4/2 0:48
 * @version: 1.0
 ***********************/
public class Test {

    public static void main(String[] args) throws CloneNotSupportedException {
        //浅克隆
        Person p1 = new Person();
        Person p2 = (Person) p1.clone();
        System.out.println("outer obj compare:" + (p1 == p2));
        System.out.println(p2.age + " " + p2.score);
        System.out.println(p2.loc);

        System.out.println("inner quote obj compare: " + (p1.loc == p2.loc));
        p1.age = 4;
        p1.score = 50;
        p1.loc.street = "sh";
        p1.loc.roomNo = 23;
        System.out.println(p1);
        System.out.println(p2);

    }
}

class Person implements Cloneable {
    int age = 8;
    int score = 100;

    Location loc = new Location("bj", 202);

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", score=" + score +
                ", loc=" + loc +
                '}';
    }
}

class Location {

    String street;
    int roomNo;

    public Location(String street, int roomNo) {
        this.street = street;
        this.roomNo = roomNo;
    }

    @Override
    public String toString() {
        return "Location{" +
                "street='" + street + '\'' +
                ", roomNo=" + roomNo +
                '}';
    }
}