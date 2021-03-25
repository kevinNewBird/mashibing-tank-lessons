package com.mashibing.dm.visitor;


/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/3/21 23:34
 * @version: 1.0
 ***********************/
public class Computer {
    ComputerPart cpu = new Cpu();
    public void accept(Visitor v) {
        this.cpu.accept(v);
    }

    public static void main(String[] args) {
        PersonalVisitor p = new PersonalVisitor();
        Computer computer = new Computer();
        computer.accept(p);
        System.out.println(p.totalPrice);
    }
}

interface ComputerPart{
    void accept(Visitor v);

    double getPrice();
}

class Cpu implements ComputerPart{

    @Override
    public void accept(Visitor v) {
        v.visitCpu(this);
    }

    @Override
    public double getPrice() {
        return 200;
    }
}
interface Visitor{
    void visitCpu(Cpu cpu);
}

class PersonalVisitor implements Visitor{
    double totalPrice=0.0;
    @Override
    public void visitCpu(Cpu cpu) {
        totalPrice = cpu.getPrice() * 0.8;
    }
}
