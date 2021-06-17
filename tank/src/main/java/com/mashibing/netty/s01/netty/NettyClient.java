package com.mashibing.netty.s01.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * description  netty 客户端 <BR>
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/11 13:36
 **/
public class NettyClient {

    private static Logger logger = LoggerFactory.getLogger(NettyClient.class);

    private Channel channel;

    public Channel getChannel() {
        return this.channel;
    }

    public void run() {
        // 0.client 客户端工作线程池
        EventLoopGroup clientWorker = new NioEventLoopGroup();

        // 1.创建启动引导器,并配置
        Bootstrap bootstrap = new Bootstrap();
        // 配置
        try {
            bootstrap
                    // 1.1.把事件处理线程池添加进启动引导器
                    .group(clientWorker)
                    // 1.2.设置通道的建立方式, 这里采用Nio 的通道方式来建立请求连接
                    .channel(NioSocketChannel.class)
                    // 1.3.构造一个由通道处理器构成的通道管道流水线
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyClientChannelHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect(NettyConstant.IP, NettyConstant.PORT).sync();
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (future.isSuccess()) {
                        logger.info("netty client running");
                    } else {
                        logger.error("netty connect failed!");
                    }
                }
            });

            this.channel = future.channel();

            this.channel.closeFuture().sync();
            logger.info("netty client ready to shutdown...");
        } catch (InterruptedException e) {
            logger.error("netty client occur exception", e);
        } finally {
            clientWorker.shutdownGracefully();
            logger.info("netty client shutdown");
        }


    }
}
