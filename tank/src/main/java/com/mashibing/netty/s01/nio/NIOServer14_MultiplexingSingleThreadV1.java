package com.mashibing.netty.s01.nio;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * description  NIOServer14_MultiplexingSingleThreadV1 <BR>
 * <p>
 * author: zhao.song
 * date: created in 22:26  2021/6/30
 * company: TRS信息技术有限公司
 * version 1.0
 */
public class NIOServer14_MultiplexingSingleThreadV1 {


    private ServerSocketChannel server = null;

    private Selector selector = null; // linux多路复用器(select/poll  epoll)

    int port = 9090;

    public static void main(String[] args) {
        new NIOServer14_MultiplexingSingleThreadV1().start();
    }

    public void initServer() {
        try {
            // 1.完成了 listen                        -> fd3
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));

            // 2.完成了 epoll_create                 -> fd4
            // 如果在epoll模型下, open -> epoll_create
            selector = Selector.open(); // 优先选择epoll, 但是可以 -D修正

            // 3.完成epoll_ctl                      -> epoll_ctl(fd4,ADD,fd3,EPOLLIN)
            // epoll: 将fd3放进开辟在内核空间的文件描述符空间fd4中
            // select/poll: jvm里开辟一个数组,将fd3放进去
            server.register(selector, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        initServer();

        System.out.println("服务器启动了");

        try {
            while (true) {
                Set<SelectionKey> keys = selector.keys();
//                System.out.println(keys.size() + "  size");

                // 1.调用多路复用器(select/poll  epoll(epoll_wait))
                /*
                select()是啥意思:
                ①.select/poll: 内核的select(fd3), poll(fd3)
                ②.epoll: epoll_wait
                 */
                // 是否存在有状态的fd集合
                while (selector.select(500) > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();// 返回有状态的fd集合
                    Iterator<SelectionKey> iter = selectionKeys.iterator();
                    // 逐一遍历每一个IO
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        iter.remove();//不移除会重复循环处理
                        //
                        if (key.isAcceptable()) {
                            //这里是重点,如果要去接收一个新的连接
                            //语义上,accept接收连接且返回新连接的fd对吧?
                            //那新的fd怎么办
                            // select/poll: 因为他们内核没有空间,那么在jvm中保存和前边的fd3那个listen的一起
                            // epoll: 通过epoll_ctl把新的客户端fd注册到内核空间
                            acceptHandler(key);
                        } else if (key.isReadable()) {
                            readHandler(key);
                            //在当前线程,这个方法可能会阻塞, 如果阻塞时间过长,其他的IO早就没电了
                            // 所以   为什么提出了IO Threads
                        }
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readHandler(SelectionKey key) {
        try {
            SocketChannel client = (SocketChannel) key.channel();
            // 在acceptHandler 方法中注册的数组
            ByteBuffer buffer = (ByteBuffer) key.attachment();
            buffer.clear();
            int len = client.read(buffer);
            while (len != -1) {
                System.out.println("接收:"+new String(buffer.array(), 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void acceptHandler(SelectionKey key) {
        try {
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            SocketChannel client = ssc.accept();// 目的是调用accept接收客户端
            client.configureBlocking(false);

            ByteBuffer buffer = ByteBuffer.allocateDirect(8192);
            //把接收到的
            client.register(selector, SelectionKey.OP_READ, buffer);
            System.out.println("新客户端:" + client.getRemoteAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
