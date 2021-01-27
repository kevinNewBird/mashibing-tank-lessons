package com.mashibing.dm.strategy.quotestrategy;

import java.util.Optional;

/***********************
 * @Description: 普通客户策略 类描述<BR>
 * @author: zhao.song
 * @since: 2021/1/27 15:25
 * @version: 1.0
 ***********************/
public class NormalCustomerStrategy implements ICalcPriceStrategy {
    @Override
    public double calcPrice(double goodsPrice) {
        Optional.of("对于新用户或者普通用户,没有折扣").ifPresent(System.out::println);
        return goodsPrice;
    }
}
