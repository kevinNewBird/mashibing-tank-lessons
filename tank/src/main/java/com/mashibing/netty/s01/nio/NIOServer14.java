package com.mashibing.netty.s01.nio;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

/**
 * description  NIOServer14 <BR>
 * <p>
 * author: zhao.song
 * date: created in 14:46  2021/6/30
 * company: TRS信息技术有限公司
 * version 1.0
 */
public class NIOServer14 {


    public static void main(String[] args) throws Exception {
        LinkedList<SocketChannel> clients = new LinkedList<>();

        ServerSocketChannel severSocket = ServerSocketChannel.open();
        severSocket.bind(new InetSocketAddress(9090));
        severSocket.configureBlocking(false);//重点 OS  NONBLOCKING!!!

        while (true) {
            Thread.sleep(1000);
            SocketChannel client = severSocket.accept();// 非阻塞

            if (client == null) {
                System.out.println("null...");
            } else {
                client.configureBlocking(false);// 重点 socket(服务端的listen socket<连接请求三次握手后,往我>)
                int port = client.socket().getPort();
                System.out.println("client...port:" + port);
                clients.add(client);
            }

            ByteBuffer buffer = ByteBuffer.allocateDirect(4096); //可以在堆里或堆外
            // 遍历已经连接进来的客户端能不能读写数据client.configureBlocking(false);
            // 所以读取数据不会阻塞,只会有读取到数据和没有读取到数据
            for (SocketChannel channel : clients) {
                // 由于设置了
                int num = channel.read(buffer);  //>0    -1   0 //不会阻塞
                if (num > 0) {
                    buffer.flip();
                    byte[] aaa = new byte[buffer.limit()];
                    buffer.get(aaa);
                    System.out.println(channel.socket().getPort() + ":" + new String(aaa));
                    buffer.clear();
                }
            }
        }


    }
}
