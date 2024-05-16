package com.mashibing.dm.mediator;

public class Client {


    public static void main(String[] args) {

        ConcreteMediator mediator = new ConcreteMediator();

        ColleagueA a = new ColleagueA(mediator);
        ColleagueB b = new ColleagueB(mediator);

        mediator.setColleagueA(a);
        mediator.setColleagueB(b);

        a.someOperation();
    }
}
