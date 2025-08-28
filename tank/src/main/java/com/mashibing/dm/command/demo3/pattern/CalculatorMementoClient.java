package com.mashibing.dm.command.demo3.pattern;

import com.mashibing.dm.command.demo3.pattern.impl.AddCommand;
import com.mashibing.dm.command.demo3.pattern.impl.Calculator;
import com.mashibing.dm.command.demo3.pattern.impl.Operation;
import com.mashibing.dm.command.demo3.pattern.impl.SubstractCommand;

/**
 * description：计算器客户端
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 23:31
 */
public class CalculatorMementoClient {

    public static void main(String[] args) {
        // 1.组装命令和接收者
        Operation operation = new Operation();
        AddCommand addCmd = new AddCommand(operation, 5);
        SubstractCommand subCmd = new SubstractCommand(operation, 3);

        // 2.把命令设置到持有者
        Calculator calculator = new Calculator();
        calculator.setAddCmd(addCmd);
        calculator.setSubCmd(subCmd);

        // 3.模拟按下按钮，测试一下
        calculator.pressAdd();
        System.out.println("一次加法运算后的结果为：" + operation.getResult());

        calculator.pressSub();
        System.out.println("一次减法运算后的结果为：" + operation.getResult());

        // 4.测试撤销
        calculator.pressUndo();
        System.out.println("撤销一次后的结果为：" + operation.getResult());

        calculator.pressUndo();
        System.out.println("再撤销一次后的结果为：" + operation.getResult());

        // 5.测试恢复
        calculator.pressRedo();
        System.out.println("恢复操作一次后的结果为：" + operation.getResult());

        calculator.pressRedo();
        System.out.println("再恢复操作一次后的结果为：" + operation.getResult());

        /**
         * 一次加法运算后的结果为：5
         * 一次减法运算后的结果为：2
         * 撤销一次后的结果为：5
         * 再撤销一次后的结果为：0
         * 恢复操作一次后的结果为：5
         * 再恢复操作一次后的结果为：2
         */
    }
}
