package com.mashibing.netty.s02;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * description  聊天netty 客户端 <BR>
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/17 13:43
 **/
public class ChatNettyClient {

    private static Logger logger = LoggerFactory.getLogger(ChatNettyClient.class);


    private ClientFrame clientFrame;

    public ChatNettyClient(ClientFrame clientFrame) {
        this.clientFrame = clientFrame;
    }

    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public void run() {
        // 1.创建处理线程池
        NioEventLoopGroup worker = new NioEventLoopGroup();

        // 2.客户端启动器
        try {
            Bootstrap bootstrap = new Bootstrap();
            ChannelFuture future = bootstrap
                    .group(worker)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ChatNettyClientInboundHandler());
                        }
                    })
                    .connect(ChatNettyConstant.IP, ChatNettyConstant.PORT)
                    .addListener(new ChannelFutureListener() {
                        @Override
                        public void operationComplete(ChannelFuture channelFuture) throws Exception {
                            if (channelFuture.isSuccess()) {
                                logger.info("client connect success.");
                            } else {
                                logger.info("client connect failed!");
                            }
                        }
                    })
                    .sync();
            // 等待关闭信号(close())
            this.channel = future.channel();
            this.channel.closeFuture().sync();
            logger.info("client ready to shutdown!");
        } catch (InterruptedException e) {
            logger.error("client occur exception!", e);
        } finally {
            worker.shutdownGracefully();
            logger.info("client ready to shutdown!");
        }
    }

    class ChatNettyClientInboundHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            ctx.writeAndFlush(Unpooled.wrappedBuffer("欢迎您光临红浪漫!!!".getBytes()));
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf) msg;
            // 接收到客户端的信息
            try {
                logger.info("客户端接收到的消息:" + buf.toString(StandardCharsets.UTF_8));
                clientFrame.ta.setText(buf.toString(StandardCharsets.UTF_8));
            } finally {
                // 释放内存(系统)
                buf.release();
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
