package com.mashibing.dm.templateMethod;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/4/4 21:19
 * @version: 1.0
 ***********************/
public class TemplateMethodMain {


    public static void main(String[] args) {
        F f= new C1();
        f.m();
    }
}

abstract class F{
    void m(){
        op1();
        op2();
    }

    abstract void op1();
    abstract void op2();
}

class C1 extends F{

    @Override
    void op1() {
        System.out.println("op1");
    }

    @Override
    void op2() {
        System.out.println("op2");
    }
}
