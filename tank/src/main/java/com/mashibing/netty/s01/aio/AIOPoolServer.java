package com.mashibing.netty.s01.aio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * description  nio 服务端-- 【多程模型】 <BR>
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/7 18:09
 **/
public class AIOPoolServer {

    private static Logger logger = LoggerFactory.getLogger(AIOPoolServer.class);


    public static void main(String[] args) throws IOException, InterruptedException {
        // AIO线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        AsynchronousChannelGroup threadGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);

        // 设置线程池
        AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open(threadGroup)
                .bind(new InetSocketAddress(8099));

        serverChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(AsynchronousSocketChannel client, Object attachment) {
                serverChannel.accept(null, this);
                try {
                    logger.info("客户端ip:{}", client.getRemoteAddress());
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    client.read(buf, buf, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer attachment) {
                            attachment.flip();
                            logger.info("接收消息:{}", new String(attachment.array(), 0, result));
                            client.write(ByteBuffer.wrap("hello client,i'm aio.".getBytes()));
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {
                            exc.printStackTrace();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            /**
             * Invoked when an operation fails.
             *
             * @param exc        The exception to indicate why the I/O operation failed
             * @param attachment
             */
            @Override
            public void failed(Throwable exc, Object attachment) {
                exc.printStackTrace();
            }
        });

        // 防止AIO直接结束
        while (true) {
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
