package com.mashibing.dm.strategy.quotestrategy;

import java.util.Optional;

/***********************
 * @Description: 老客户策略 类描述<BR>
 * @author: zhao.song
 * @since: 2021/1/27 15:25
 * @version: 1.0
 ***********************/
public class OldCustomerStrategy implements ICalcPriceStrategy {
    @Override
    public double calcPrice(double goodsPrice) {
        Optional.of("对于老客户,统一折扣%5").ifPresent(System.out::println);
        return goodsPrice * (1 - 0.05);
    }
}
