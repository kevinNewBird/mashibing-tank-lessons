package test.netty.main;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import test.netty.SayHelloClient;

import java.util.Scanner;

/**
 * description  Netty 客户端main方法类 <BR>
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/4 11:21
 **/
public class MainNettyClient {

    public static void main(String[] args) {
        /**
         * 创建netty客户端
         */
        SayHelloClient client = new SayHelloClient(8686);
        /**
         * 新建一个线程让它单独去跑,我们可以main方法测试一下发送消息和接受消息
         */
        Thread clientThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    client.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        clientThread.start();
        /**
         * 如果不新建一个线程去跑客户端的话, 以下的代码就执行不到
         * 这里用while是因为客户端的channel并不能立马生成, 会在client启动后一段时间才生成获取到
         * 所以需要延迟一点获取channel, 否则channel为null
         */
        Channel channel = null;
        boolean isStart = false;
        while (!isStart) {
            if (null != client.getChannel()) {
                channel = client.getChannel();
                isStart = true;
            }
        }
        String helo = "你好呀!我这里是客户端, 收到请回答";
        ByteBuf byteBuf = Unpooled.wrappedBuffer(helo.getBytes());
        channel.writeAndFlush(byteBuf);
        /**
         * 我们通过控制台输入来给服务端发送消息
         * 此处只做模拟使用
         */
        for (int i = 0; i < 10 ; i++) {
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine();
            channel.writeAndFlush(Unpooled.wrappedBuffer(text.getBytes()));
        }
    }
}
