package com.mashibing.netty.s01.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * description  NIOServer14_MultiplexingMoreThreads <BR>
 * <p>
 * author: zhao.song
 * date: created in 17:59  2021/7/1
 * company: TRS信息技术有限公司
 * version 1.0
 */
public class NIOServer14_MultiplexingMoreThreadsV1 {

    private ServerSocketChannel server = null;

    private Selector selector1 = null; // linux多路复用器(select/poll  epoll)
    private Selector selector2 = null; // linux多路复用器(select/poll  epoll)
    private Selector selector3 = null; // linux多路复用器(select/poll  epoll)

    int port = 9090;

    public void initServer() {
        try {
            server = ServerSocketChannel.open();
            server.bind(new InetSocketAddress(port));
            server.configureBlocking(false);

            // 多路复用器
            selector1 = Selector.open();
            selector2 = Selector.open();
            selector3 = Selector.open();

            server.register(selector1, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NIOServer14_MultiplexingMoreThreadsV1 service = new NIOServer14_MultiplexingMoreThreadsV1();
        service.initServer();

        NioThread t1 = new NioThread(service.selector1, 2);
        NioThread t2 = new NioThread(service.selector2);
        NioThread t3 = new NioThread(service.selector3);

        t1.start();

        TimeUnit.SECONDS.sleep(1);

        t2.start();
        t3.start();

        System.out.println("服务器启动了");


    }

    static class NioThread extends Thread {
        private Selector selector;
        static int selectors = 0;

        int id = 0;

        volatile static BlockingQueue<SocketChannel>[] queue;

        static AtomicInteger idx = new AtomicInteger();

        public NioThread(Selector selector, int n) {
            this.selector = selector;
            this.selectors = n;

            queue = new LinkedBlockingQueue[selectors];
            for (int i = 0; i < n; i++) {
                queue[i] = new LinkedBlockingQueue<>();
            }
            System.out.println("Boss 启动");
        }

        public NioThread(Selector selector) {
            this.selector = selector;
            id = idx.getAndIncrement() % selectors;
            System.out.println("worker: " + id + " 启动");
        }

        @Override
        public void run() {
            try {
                while (true) {
                    // 只有第一个线程存在为true的情况,其注册了listen
                    while (selector.select(10) > 0) {
                        Set<SelectionKey> selectionKeys = selector.selectedKeys();
                        Iterator<SelectionKey> iter = selectionKeys.iterator();
                        while (iter.hasNext()) {
                            SelectionKey key = iter.next();
                            iter.remove();
                            if (key.isAcceptable()) {
                                acceptHandler(key);
                            } else if (key.isReadable()) {
                                readHandler(key);
                            }
                        }
                    }

                    if (!queue[id].isEmpty()) {
                        ByteBuffer buffer = ByteBuffer.allocate(8192);
                        SocketChannel client = queue[id].take();
                        client.register(selector, SelectionKey.OP_READ, buffer);
                        System.out.println("----------------------------------------");
                        System.out.println("新客户端: " + client.socket().getPort() + "分配到");
                        System.out.println("----------------------------------------");

                    }
                }

            } catch (Exception e) {
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
                    System.out.println("接收:" + new String(buffer.array(), 0, len));
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

                int num = idx.getAndIncrement() % selectors;
                queue[num].add(client);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
