package com.mashibing.netty.s01.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * description  nio 服务端-- 【单线程模型】 <BR>
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/7 16:26
 **/
public class NIOServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel nioServer = ServerSocketChannel.open();
        nioServer.socket().bind(new InetSocketAddress("127.0.0.1", 8099));
        // 设置它的阻塞态
        nioServer.configureBlocking(false);

        System.out.println("server started, listening on: " + nioServer.getLocalAddress());
        // 大管家概念
        Selector selector = Selector.open();
        // SelectionKey.OP_ACCEPT, 表示注册到channel通道里的selector只关心连接上来的线程并触发一些事件
        nioServer.register(selector, SelectionKey.OP_ACCEPT);

        // 处理的逻辑:
        while (true) {
            // 阻塞方法(selector关心的channel上有事件发生,阻塞消失继续往下执行)
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                it.remove();
                handle(key);
            }
        }
    }

    private static void handle(SelectionKey key){
        // 判断客户端申请连接上来了,是否时可以接收的
        if (key.isAcceptable()) {
            try {
                ServerSocketChannel nioServer = (ServerSocketChannel) key.channel();
                // 迎接这个客人
                SocketChannel channel = nioServer.accept();
                channel.configureBlocking(false);

                channel.register(key.selector(), SelectionKey.OP_READ);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (key.isReadable()) {
            SocketChannel sc = null;
            try{
                sc = (SocketChannel) key.channel();
                ByteBuffer buffer = ByteBuffer.allocate(512);
                buffer.clear();
                int len = sc.read(buffer);
                if (len != -1) {
                    System.out.println(new String(buffer.array(), 0, len));
                }
                ByteBuffer bufferToWrite = ByteBuffer.wrap("hello client, i'm nio server!".getBytes());
                sc.write(bufferToWrite);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (sc != null) {
                    try {
                        sc.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
