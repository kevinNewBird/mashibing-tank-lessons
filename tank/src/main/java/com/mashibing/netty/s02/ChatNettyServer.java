package com.mashibing.netty.s02;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * description  聊天室netty 服务端 <BR>
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/17 11:41
 **/
public class ChatNettyServer {

    private static Logger logger = LoggerFactory.getLogger(ChatNettyServer.class);


    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static void main(String[] args) {
        // 启动server端
        new ChatNettyServer().run();
    }

    public void run() {
        // 1.创建处理线程池
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup(3);
        // 2.创建服务端启动器
        try {
            ServerBootstrap boostrap = new ServerBootstrap();
            ChannelFuture future = boostrap
                    .group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ChatNettyServerInboundHandler());
                        }
                    })
                    .bind(ChatNettyConstant.PORT)
                    .addListener(new ChannelFutureListener() {
                        @Override
                        public void operationComplete(ChannelFuture channelFuture) throws Exception {
                            if (channelFuture.isSuccess()) {
                                logger.info("server connect success!");
                            } else {
                                logger.info("server connect failed!");
                            }
                        }
                    }).sync();

            future.channel().closeFuture().sync();
            logger.info("server ready to shutdown!");
        } catch (InterruptedException e) {
            logger.error("server occur exception!", e);
        } finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
            logger.info("server shutdown!");
        }

    }

    class ChatNettyServerInboundHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
//            ctx.writeAndFlush(Unpooled.wrappedBuffer("hello client".getBytes()));
            clients.add(ctx.channel());
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf) msg;
            // 接收到客户端的信息
            try {
                clients.writeAndFlush(buf);
            } finally {
                // 释放内存(系统)
//                if (buf.refCnt() == 1) {
//                    buf.release();
//                }
            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            logger.error("handle msg occur exception!", cause);
            // 关闭 -> ChatNettyClient的closeFuture()
            ctx.close();
        }
    }
}
