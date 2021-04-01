package com.mashibing.dm.command;

import java.util.Optional;

/***********************
 * @Description: 命令模式测试客户端 <BR>
 * @author: zhao.song
 * @since: 2021/4/1 9:58
 * @version: 1.0
 ***********************/
public class CommandClient {

    public static void main(String[] args) {
        Content content = new Content();
        content.msg = "我是谁!";

        /*----copy----*/
        CopyCommand cc = new CopyCommand(content);
        cc.doit();
        System.out.println(content.msg);
        cc.undo();
        System.out.println(content.msg);
        Optional.of("----------------copy------------------").ifPresent(System.out::println);

        /*----insert----*/
        InsertCommand ic = new InsertCommand(content);
        ic.doit();
        System.out.println(content.msg);
        ic.undo();
        System.out.println(content.msg);
        Optional.of("----------------insert------------------").ifPresent(System.out::println);

        /*----delete----*/
        DeleteCommand dc = new DeleteCommand(content);
        dc.doit();
        System.out.println(content.msg);
        dc.undo();
        System.out.println(content.msg);
        Optional.of("----------------delete------------------").ifPresent(System.out::println);
    }
}
