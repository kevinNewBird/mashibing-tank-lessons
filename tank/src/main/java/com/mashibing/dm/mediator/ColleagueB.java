package com.mashibing.dm.mediator;

public class ColleagueB extends Colleague {
    public ColleagueB(Mediator mediator) {
        super(mediator);
    }

    /**
     * @description:
     * @create by: zhaosong 2023/3/16 9:35

     * @return {@link null}
     */
    public void someOperation(String data) {
        data += "B";
        System.out.println(data);
    }
}
