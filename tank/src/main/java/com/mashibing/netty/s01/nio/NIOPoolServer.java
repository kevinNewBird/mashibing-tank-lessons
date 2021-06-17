package com.mashibing.netty.s01.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.socketconn.SocketServer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * description  nio 服务端-- 【多线程模型】 <BR>
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/7 17:33
 **/
public class NIOPoolServer {

    private static Logger logger = LoggerFactory.getLogger(NIOPoolServer.class);

    ExecutorService pool = Executors.newFixedThreadPool(50);

    private Selector selector;

    public static void main(String[] args) throws IOException {

        NIOPoolServer pool = new NIOPoolServer();
        pool.initServer();
        pool.listen();
    }

    /**
     * description: 初始化服务端 <BR>
     *
     * @param :
     * @return
     * @author zhao.song   2021/6/7 17:39
     */
    private void initServer() throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress("127.0.0.1", 8099));
        server.configureBlocking(false);

        selector = Selector.open();
        server.register(selector, SelectionKey.OP_ACCEPT);
        logger.info("NIO服务端启动成功!");

    }

    private void listen() throws IOException {
        while (true) {
            selector.select();

            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                it.remove();
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel channel = server.accept();
                    channel.configureBlocking(false);

                    channel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    key.interestOps(key.interestOps() & (~SelectionKey.OP_READ));
                    pool.execute(new ThreadHandlerChannel(key));
                }
            }
        }
    }

    private class ThreadHandlerChannel implements Runnable {
        private SelectionKey key;

        public ThreadHandlerChannel(SelectionKey key) {
            this.key = key;
        }

        @Override
        public synchronized void run() {
            SocketChannel channel = (SocketChannel) key.channel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            try (ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
                int size = 0;
                while ((size = channel.read(buffer)) > 0) {
                    buffer.flip();
                    baos.write(buffer.array(), 0, size);
                    buffer.clear();
                }
                baos.close();
                byte[] content = baos.toByteArray();
                logger.info("接收到信息: {}", new String(content, 0, content.length));
                ByteBuffer writeBuf = ByteBuffer.wrap("hello, i'm nio pool server.".getBytes());
                channel.write(writeBuf);
                if (size == -1) {
                    channel.close();
                } else {
                    key.interestOps(key.interestOps() | SelectionKey.OP_READ);
                    key.selector().wakeup();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
