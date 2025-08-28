package com.mashibing.dm.command.demo3.pattern;

import com.mashibing.dm.command.demo3.pattern.impl.ChopCommand;
import com.mashibing.dm.command.demo3.pattern.impl.PorkCommand;
import com.mashibing.dm.command.demo3.pattern.impl.Waiter;

/**
 * description：服务员客户端
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/28 14:15
 */
public class WaiterClient {

    public static void main(String[] args) {
        // 1.点菜
        PorkCommand pc = new PorkCommand();
        ChopCommand cc = new ChopCommand();

        Waiter waiter = new Waiter();
        waiter.orderDish(cc);
        waiter.orderDish(pc);

        // 2.服务员通知后厨
        waiter.orderOver();

        /**
         * 本厨师正在做：绿豆排骨煲
         * 凉菜李庄白肉已经做好，本厨师正在装盘。
         */
    }
}
