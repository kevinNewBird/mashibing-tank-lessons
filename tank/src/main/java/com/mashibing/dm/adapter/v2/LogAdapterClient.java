package com.mashibing.dm.adapter.v2;

import com.mashibing.dm.adapter.v1.LogFileOperate;
import com.mashibing.dm.adapter.v1.LogModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***********************
 * @Description: 日志适配器客户端 <BR>
 * @author: zhao.song
 * @since: 2021/3/31 16:20
 * @version: 1.0
 ***********************/
public class LogAdapterClient {


    public static void main(String[] args) {
        //准备日志内容,也就是测试数据
        LogModel lm = new LogModel.LogModelBuilder()
                .basicInfo("004", "admin4", "2021-03-31 16:30:56", "我是适配器测试4!")
                .build();
        List<LogModel> list = new ArrayList<>(Arrays.asList(lm));

        //创建操作日志文件对象
        LogFileOperate lfo = new LogFileOperate();
        LogDbOperate ldo = new LogDbOperate();

        //创建双向适配器
        TwoDirectAdapter tda0 = new TwoDirectAdapter(lfo, ldo);
        TwoDirectAdapter tda1 = new TwoDirectAdapter(lfo, ldo);

        //先测试从文件操作适配到第二版
        //虽然调用的时第二版的接口,其实时文件操作在实现
        tda0.createLog(lm);
        System.out.println(tda0.getAllLog());

        //再测试从数据库存储适配第一版的接口
        //也就是调用第一版的接口,其实就是数据库实现
        tda1.writeLogFile(list);
        tda1.readLogFile();

    }

}
