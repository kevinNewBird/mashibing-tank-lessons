package com.mashibing.dm.mediator;

public class ColleagueA extends Colleague {

    private String data = "A";

    public ColleagueA(Mediator mediator) {
        super(mediator);
    }

    public void someOperation() {
        getMediator().changed(this);
    }

    public String getData() {
        return data;
    }
}
