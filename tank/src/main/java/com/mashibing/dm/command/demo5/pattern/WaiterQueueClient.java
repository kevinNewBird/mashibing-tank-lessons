package com.mashibing.dm.command.demo5.pattern;

import com.mashibing.dm.command.demo5.pattern.impl.*;

/**
 * description：队列请求客户端
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/28 15:23
 */
public class WaiterQueueClient {

    public static void main(String[] args) {
        // 1.先启动厨房
        CookManager.runCookManager();

        // 2.点菜
        for (int tableNum = 1; tableNum <= 3; tableNum++) {
            // 创建服务员
            Waiter waiter = new Waiter();
            // 创建命令对象，就是要点的菜
            ChopCommand chop = new ChopCommand(tableNum);
            DuckCommand duck = new DuckCommand(tableNum);
            PorkCommand pork = new PorkCommand(tableNum);

            // 通知服务员下单
            waiter.orderDish(chop);
            waiter.orderDish(duck);
            waiter.orderDish(pork);

            // 点菜完毕
            waiter.orderOver();
        }

        /**
         * 王五厨师【金牌】正在为1号桌做：酸菜老鸭汤
         * 张三厨师【银牌牌】正在为1号桌做：绿豆排骨煲
         * 李四厨师【银牌牌】正在为1号桌做：李庄白肉
         * 王五厨师【金牌】为1号桌做好了：酸菜老鸭汤，共计耗时0秒
         * 李四厨师【银牌】为1号桌做好了：李庄白肉，共计耗时8秒
         * 王五厨师【金牌】正在为2号桌做：绿豆排骨煲
         * 王五厨师【金牌】为2号桌做好了：绿豆排骨煲，共计耗时5秒
         * 张三厨师【银牌】为1号桌做好了：绿豆排骨煲，共计耗时18秒
         * 李四厨师【银牌牌】正在为2号桌做：酸菜老鸭汤
         * 李四厨师【银牌】为2号桌做好了：酸菜老鸭汤，共计耗时6秒
         * 王五厨师【金牌】正在为2号桌做：李庄白肉
         * 王五厨师【金牌】为2号桌做好了：李庄白肉，共计耗时2秒
         * 张三厨师【银牌牌】正在为3号桌做：绿豆排骨煲
         * 李四厨师【银牌牌】正在为3号桌做：酸菜老鸭汤
         * 张三厨师【银牌】为3号桌做好了：绿豆排骨煲，共计耗时9秒
         * 王五厨师【金牌】正在为3号桌做：李庄白肉
         * 李四厨师【银牌】为3号桌做好了：酸菜老鸭汤，共计耗时9秒
         * 王五厨师【金牌】为3号桌做好了：李庄白肉，共计耗时17秒
         */

    }
}
