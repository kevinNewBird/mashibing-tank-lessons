package com.mashibing.netty.s01.netty.main;

import com.mashibing.netty.s01.netty.NettyClient;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import org.apache.commons.lang.StringUtils;

import java.nio.ByteBuffer;
import java.util.Scanner;

/**
 * description  TODO <BR>
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/11 14:18
 **/
public class NettyClientMain {

    public static void main(String[] args) {
        NettyClient client = new NettyClient();
        new Thread(client::run).start();

        boolean isStart = false;
        Channel channel = null;
        while (true) {
            if (null != client.getChannel()) {
                channel = client.getChannel();
                isStart = true;
                break;
            }
        }
        Scanner scanner = new Scanner(System.in);
        while (isStart) {
            String msg = scanner.nextLine();
            if (StringUtils.isNotBlank(msg)) {
                ByteBuf buf = Unpooled.wrappedBuffer(msg.getBytes());
                channel.writeAndFlush(buf);
            }
            // 如果调用这个, NettyClient就会被关闭掉
//            channel.close();
        }
    }
}
