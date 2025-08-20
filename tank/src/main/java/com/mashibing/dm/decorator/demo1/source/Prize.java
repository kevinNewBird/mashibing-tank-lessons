package com.mashibing.dm.decorator.demo1.source;

import com.mashibing.dm.decorator.demo1.base.TempDB;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * 计算奖金的对象
 */
public class Prize {

    /**
     * 计算默认在某段时间内的奖金，有些参数在演示中并不会使用
     * 但是在实际业务实现上是会用的，为了表示这是个具体的业务方法，因此这些参数被保留了
     *
     * @param user：被计算奖金的人员
     * @param begin：计算奖金的开始时间
     * @param end：计算奖金的结束时间
     * @return 某人在某段时间内的奖金
     */
    public double calcPrize(String user, Date begin, Date end) {
        double prize = 0.0;
        // 计算当月业务奖金，所有人都会计算
        prize = this.monthPrize(user, begin, end);
        // 计算累计奖金
        prize += this.sumPrize(user, begin, end);

        // 需要判断该人员是普通人员还是业务经理，团队奖金只有业务经理才有
        if (this.isManager(user)) {
            prize += this.groupPrize(user, begin, end);
        }

        return prize;
    }

    /**
     * 计算某人的当月业务奖金
     *
     * @param user
     * @param begin
     * @param end
     * @return
     */
    private double monthPrize(String user, Date begin, Date end) {
        // 计算当月业务奖金，按照人员去获取当月的业务额，然后乘以 3%
        double prize = TempDB.mapMonthSaleMoney.get(user) * 0.03;
        System.out.printf("%s当月业务奖金%s\n", user, prize);
        return prize;
    }

    private double sumPrize(String user, Date begin, Date end) {
        // 计算累计奖金,其实按照人员去获取累计的业务额，然后乘以0.1%
        // 简单演示一下，嘉定大家的累计业务额都是1000 000
        double prize = 1000_000 * 0.001;
        System.out.printf("%s累计奖金%s\n", user, prize);
        return prize;
    }

    private double groupPrize(String user, Date begin, Date end) {
        // 计算当月团队业务奖金，先计算出团队总的业务额，然后再乘以1%
        // 假设都是一个团队
        double group = 0.0;
        for (Double d : TempDB.mapMonthSaleMoney.values()) {
            group += d;
        }
        double prize = group * 0.01;
        System.out.printf("%s当月团队业务奖金%s\n", user, prize);
        return prize;
    }

    /**
     * 判断人员是普通人员还是业务经理
     *
     * @param user
     * @return true表示业务经理
     */
    private boolean isManager(String user) {
        // 应该从数据库中获取人员对应的职务
        // 为了演示，简单点判断，只有王五是经理
        return StringUtils.equals("王五", user);
    }

}
