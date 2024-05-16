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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * description  reactor模型  <BR>
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/7 16:26
 **/
public class NIOServerReactor {


    //https://blog.csdn.net/pontuss/article/details/116903384
    public static void main(String[] args) throws IOException {
        new Thread(new Reactor(9090)).start();
    }

    static class Reactor implements Runnable {
        private final Selector selector;
        private final ServerSocketChannel serverSocket;

        public Reactor(int port) throws IOException {
            serverSocket = ServerSocketChannel.open();  // 创建服务端的ServerSocketChannel
            serverSocket.configureBlocking(false);  // 设置为非阻塞模式
            selector = Selector.open();  // 创建一个Selector多路复用器
            SelectionKey key = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            serverSocket.bind(new InetSocketAddress(port));  // 绑定服务端端口
            key.attach(new Acceptor(serverSocket));  // 为服务端Channel绑定一个Acceptor
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    selector.select();  // 服务端使用一个线程不断等待客户端的连接到达
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keys.iterator();
                    while (iterator.hasNext()) {
                        dispatch(iterator.next());  // 监听到客户端连接事件后将其分发给Acceptor
                        iterator.remove();
                    }

                    selector.selectNow();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void dispatch(SelectionKey key) throws IOException {
            // 这里的attachement也即前面为服务端Channel绑定的Acceptor，调用其run()方法进行
            // 客户端连接的获取，并且进行分发
            Runnable attachment = (Runnable) key.attachment();
            attachment.run();
        }

    }

    static class Acceptor implements Runnable {

        private final ExecutorService executor = Executors.newFixedThreadPool(20);

        private final ServerSocketChannel serverSocket;

        public Acceptor(ServerSocketChannel serverSocket) {
            this.serverSocket = serverSocket;
        }

        @Override
        public void run() {
            try {
                SocketChannel channel = serverSocket.accept();//获取客户端连接
                if (null != channel) {
                    executor.execute(new Handler(channel));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class Handler implements Runnable {
        private volatile static Selector selector;
        private final SocketChannel channel;
        private SelectionKey key;
        private volatile ByteBuffer input = ByteBuffer.allocate(1024);
        private volatile ByteBuffer output = ByteBuffer.allocate(1024);


        public Handler(SocketChannel channel) throws IOException {
            this.channel = channel;
            channel.configureBlocking(false);  // 设置客户端连接为非阻塞模式
            selector = Selector.open();  // 为客户端创建一个新的多路复用器
            key = channel.register(selector, SelectionKey.OP_READ);  // 注册客户端Channel的读事件
        }

        @Override
        public void run() {
            try {
                while (selector.isOpen() && channel.isOpen()) {
                    Set<SelectionKey> keys = select();  // 等待客户端事件发生
                    Iterator<SelectionKey> iterator = keys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();

                        // 如果当前是读事件，则读取数据
                        if (key.isReadable()) {
                            read(key);
                        } else if (key.isWritable()) {
                            // 如果当前是写事件，则写入数据
                            write(key);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 这里处理的主要目的是处理Jdk的一个bug，该bug会导致Selector被意外触发，但是实际上没有任何事件到达，
        // 此时的处理方式是新建一个Selector，然后重新将当前Channel注册到该Selector上
        private Set<SelectionKey> select() throws IOException {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            if (keys.isEmpty()) {
                int interestOps = key.interestOps();
                selector = Selector.open();
                key = channel.register(selector, interestOps);
                return select();
            }

            return keys;
        }

        // 读取客户端发送的数据
        private void read(SelectionKey key) throws IOException {
            channel.read(input);
            if (input.position() == 0) {
                return;
            }

            input.flip();
            process();  // 对读取的数据进行业务处理
            input.clear();
            key.interestOps(SelectionKey.OP_WRITE);  // 读取完成后监听写入事件
        }

        private void write(SelectionKey key) throws IOException {
            output.flip();
            if (channel.isOpen()) {
                channel.write(output);  // 当有写入事件时，将业务处理的结果写入到客户端Channel中
                key.channel();
                channel.close();
                output.clear();
            }
        }

        // 进行业务处理，并且获取处理结果。本质上，基于Reactor模型，如果这里成为处理瓶颈，
        // 则直接将其处理过程放入线程池即可，并且使用一个Future获取处理结果，最后写入客户端Channel
        private void process() {
            byte[] bytes = new byte[input.remaining()];
            input.get(bytes);
            String message = new String(bytes);
            System.out.println("receive message from client: \n" + message);

            output.put("hello client".getBytes());
        }

    }

}
