package com.mashibing.dm.decorator.demo1.pattern.base.impl;

import com.mashibing.dm.decorator.demo1.pattern.base.Component;
import com.mashibing.dm.decorator.demo1.pattern.base.Decorator;

import java.util.Date;

public class SumPrizeDecorator extends Decorator {
    public SumPrizeDecorator(Component c) {
        super(c);
    }

    @Override
    public double calcPrize(String user, Date begin, Date end) {
        // 1.先获取前面运算出来的奖金
        double money = super.calcPrize(user, begin, end);
        // 2.然后计算累计奖金，按人员和时间去获取累计的业务额，然后乘以0.1%
        // 简单演示一下，嘉定大家的累计业务额都是1000 000
        double prize = 1000_000 * 0.001;
        System.out.printf("%s累计奖金%s\n", user, prize);
        return money + prize;
    }
}
