package com.mashibing.dm.strategy.quotestrategy;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/1/27 14:30
 * @version: 1.0
 ***********************/
public class Price {

    private ICalcPriceStrategy strategy;

    public Price(ICalcPriceStrategy strategy) {
        this.strategy = strategy;
    }

    public double quote(double goodsPrice) {
        return this.strategy.calcPrice(goodsPrice);
    }
}
