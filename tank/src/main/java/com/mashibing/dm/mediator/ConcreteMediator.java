package com.mashibing.dm.mediator;

public class ConcreteMediator implements Mediator {


    /**
     * 持有并维护同事A
     */
    private ColleagueA colleagueA;

    /**
     * 持有并维护同事B
     */
    private ColleagueB colleagueB;




    @Override
    public void changed(Colleague colleague) {
        //某个同时类发生了变化，通常需要与其它同事交互
        //具体协调相应的同事对象来实现协作行为
        if (colleague == colleagueA) {
            String data = colleagueA.getData();
            // 通知B去处理原始数据
            colleagueB.someOperation(data);
        }
    }

    /**
     * @description:
     * @create by: zhaosong 2023/3/16 9:34
     * @param null
     * @return {@link null}
     */
    public void setColleagueA(ColleagueA colleagueA) {
        this.colleagueA = colleagueA;
    }

    public void setColleagueB(ColleagueB colleagueB) {
        this.colleagueB = colleagueB;
    }
}
