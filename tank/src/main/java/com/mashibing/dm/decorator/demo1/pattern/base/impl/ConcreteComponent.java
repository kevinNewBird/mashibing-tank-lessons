package com.mashibing.dm.decorator.demo1.pattern.base.impl;

import com.mashibing.dm.decorator.demo1.pattern.base.Component;

import java.util.Date;

public class ConcreteComponent extends Component {
    /**
     * 基本的实现计算奖金的类，也是被装饰器装饰的对象
     *
     * @param user：被计算奖金的人员
     * @param begin：计算奖金的开始时间
     * @param end：计算奖金的结束时间
     * @return
     */
    @Override
    public double calcPrize(String user, Date begin, Date end) {
        return 0;
    }
}
