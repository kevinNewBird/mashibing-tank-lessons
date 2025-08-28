package com.mashibing.dm.command.demo1.pattern;

import com.mashibing.dm.command.demo1.pattern.impl.Box;
import com.mashibing.dm.command.demo1.pattern.impl.GigaMainBoard;
import com.mashibing.dm.command.demo1.pattern.impl.OpenCommand;
import com.mashibing.dm.command.demo1.pattern.impl.ResetCommand;

/**
 * description：机箱客户端
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 22:29
 */
public class BoxClient {

    public static void main(String[] args) {
        // 1.创建机箱绑定的开机命令对象，命令对象持有真正执行命令的接收者Receiver-主板对象
        // 相当于在组装机起，把机箱傻姑娘按钮的连线插到主板上
        GigaMainBoard mainBoard = new GigaMainBoard();
        OpenCommand openCommand = new OpenCommand(mainBoard);
        ResetCommand resetCommand = new ResetCommand(mainBoard);

        // 2.新建机箱对象-Invoker，并为其设置对应的命令
        Box box = new Box();
        box.setOpenCommand(openCommand);
        box.setResetCommand(resetCommand);

        // 3.按下开机按钮
        System.out.println("正确配置下-------------------------->");
        System.out.println(">>>按下开机按钮：>>>");
        box.pressOpenButton();
        System.out.println(">>>按下重启按钮：>>>");
        box.pressResetButton();

        /**
         * 正确配置下-------------------------->
         * >>>按下开机按钮：>>>
         * 技嘉主板现在正在开机，请等候
         * 接通电源......
         * 设备检查......
         * 装载系统......
         * 机器正常运转起来......
         * 机器已经正常打开，请操作
         * >>>按下重启按钮：>>>
         * 技嘉主板现在正在重新启动机器，请等候
         * 机器已经正常打开，请操作
         */


        System.out.println();
        box.setOpenCommand(resetCommand);
        box.setResetCommand(openCommand);
        System.out.println("错误配置下-------------------------->");
        System.out.println(">>>按下开机按钮：>>>");
        box.pressOpenButton();
        System.out.println(">>>按下重启按钮：>>>");
        box.pressResetButton();

        /**
         * 错误配置下-------------------------->
         * >>>按下开机按钮：>>>
         * 技嘉主板现在正在重新启动机器，请等候
         * 机器已经正常打开，请操作
         * >>>按下重启按钮：>>>
         * 技嘉主板现在正在开机，请等候
         * 接通电源......
         * 设备检查......
         * 装载系统......
         * 机器正常运转起来......
         * 机器已经正常打开，请操作
         */
    }
}
