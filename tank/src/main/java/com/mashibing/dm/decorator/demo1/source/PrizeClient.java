package com.mashibing.dm.decorator.demo1.source;

public class PrizeClient {

    public static void main(String[] args) {
        // 先创建计算奖金的对象
        Prize p = new Prize();

        // 日期对象都没有用上，所以传null
        double zs = p.calcPrize("张三", null, null);
        System.out.printf("================张三应得奖金：%s\n", zs);

        double ls = p.calcPrize("李四", null, null);
        System.out.printf("================李四应得奖金：%s\n", ls);

        double ww = p.calcPrize("王五", null, null);
        System.out.printf("================王五应得奖金：%s\n", ww);

        /**
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
