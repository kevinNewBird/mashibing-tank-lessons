package com.mashibing.dm.decorator.demo1.pattern.base.impl;

import com.mashibing.dm.decorator.demo1.base.TempDB;
import com.mashibing.dm.decorator.demo1.pattern.base.Component;
import com.mashibing.dm.decorator.demo1.pattern.base.Decorator;

import java.util.Date;

public class GroupPrizeDecorator extends Decorator {
    public GroupPrizeDecorator(Component c) {
        super(c);
    }

    @Override
    public double calcPrize(String user, Date begin, Date end) {
        // 1.先获取前面运算出来的奖金
        double money = super.calcPrize(user, begin, end);
        // 2.然后计算当月团队业务奖金，按人员和时间去获取团队总的业务额，然后乘以1%
        // 简单演示一下，嘉定大家的累计业务额都是1000 000
        double group = 0.0;
        for (Double d : TempDB.mapMonthSaleMoney.values()) {
            group += d;
        }
        double prize = group * 0.01;
        System.out.printf("%s当月团队业务奖金%s\n", user, prize);
        return money + prize;
    }
}
