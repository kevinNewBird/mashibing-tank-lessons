package com.mashibing.dm.strategy.quotestrategy;

import java.util.Optional;

/***********************
 * @Description: 用户客户端 <BR>
 * @author: zhao.song
 * @since: 2021/1/27 15:26
 * @version: 1.0
 ***********************/
public class CustomerClient {

    public static void main(String[] args) {
        ICalcPriceStrategy oStrategy = new OldCustomerStrategy();
        Optional.of(String.join(",", "折扣价:", String.valueOf(new Price(oStrategy)
                .quote(100)))).ifPresent(System.out::println);

    }
}
