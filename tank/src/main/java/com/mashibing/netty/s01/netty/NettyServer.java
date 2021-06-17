package com.mashibing.netty.s01.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * description  netty 服务端 <BR>
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/11 14:09
 **/
public class NettyServer {

    private static Logger logger = LoggerFactory.getLogger(NettyClient.class);

    // TIP: 使用默认通道组处理通道上的事件
    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public void run() {
        // 0.构建领导和工作线程池
        NioEventLoopGroup leader = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        // 1.构建服务端启动器,并配置
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap
                    .group(leader, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel serverSocketChannel) throws Exception {
                            //ChannelPipeline
                            serverSocketChannel.pipeline().addLast(new NettyServerChannelHandler());
                        }
                    })
                    // 1.4.用来配置一些channel的参数, 配置的参数会被ChannelConfig使用
                    // BACKLOG 用于构造服务端 套接字 ServerSocket
                    // 标识服务器请求处理线程全满时,用于临时存放已完成三次握手的请求的队列的最大长度 ,
                    // 如果未设置或所设置的值小于1, Java 将使用默认值 50
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 1.5.是否启用心跳保活机制.
                    // 在双方TCP套接字建立连接后(即都进入ESTABLISHED状态),并且在两个小时左右上层没有任何数据传输的情况下,这套机制才会被激活
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future = bootstrap.bind(NettyConstant.PORT).sync();
            if (future.isSuccess()) {
                logger.info("netty server running");
            }
            // 获取到server上的channel, closeFuture的作用就是如果有人调用了close()方法它的返回值是一个channelFuture,否则会永远等待在这里
            // 也就是说,上面的ChannelFuture不会获取到
            future.channel().closeFuture().sync();
            logger.info("netty client ready to shutdown...");
        } catch (InterruptedException e) {
            logger.error("netty client occur exception", e);
        } finally {
            worker.shutdownGracefully();
            leader.shutdownGracefully();
            logger.info("netty server shutdown");
        }

    }
}
