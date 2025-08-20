package com.mashibing.dm.decorator.demo1.pattern;

import com.mashibing.dm.decorator.demo1.pattern.base.Decorator;
import com.mashibing.dm.decorator.demo1.pattern.base.impl.ConcreteComponent;
import com.mashibing.dm.decorator.demo1.pattern.base.impl.GroupPrizeDecorator;
import com.mashibing.dm.decorator.demo1.pattern.base.impl.MonthPrizeDecorator;
import com.mashibing.dm.decorator.demo1.pattern.base.impl.SumPrizeDecorator;

public class PrizeDecoratorClient {


    public static void main(String[] args) {
        // 1.先创建计算基本奖金的类，这也是被装饰的对象
        ConcreteComponent c1 = new ConcreteComponent();

        // 2.先组合普通业务人员的奖金计算
        Decorator d1 = new MonthPrizeDecorator(c1);
        Decorator d2 = new SumPrizeDecorator(d1);

        // 注意：这里只需使用最后组合好的对象调用业务方法即可
        double zs = d2.calcPrize("张三", null, null);
        System.out.printf("================张三应得奖金：%s\n", zs);

        double ls = d2.calcPrize("李四", null, null);
        System.out.printf("================李四应得奖金：%s\n", ls);

        // 3.如果是业务经理，还需要一个计算团队的奖金计算
        GroupPrizeDecorator d3 = new GroupPrizeDecorator(d2);
        double ww = d3.calcPrize("王五", null, null);
        System.out.printf("================王五应得奖金：%s\n", ww);

        /**
         * Connected to the target VM, address: '127.0.0.1:58142', transport: 'socket'
         * 张三当月业务奖金300.0
         * 张三累计奖金1000.0
         * ================张三应得奖金：1300.0
         * 李四当月业务奖金600.0
         * 李四累计奖金1000.0
         * ================李四应得奖金：1600.0
         * 王五当月业务奖金900.0
         * 王五累计奖金1000.0
         * 王五当月团队业务奖金600.0
         * ================王五应得奖金：2500.0
         */
    }
}
