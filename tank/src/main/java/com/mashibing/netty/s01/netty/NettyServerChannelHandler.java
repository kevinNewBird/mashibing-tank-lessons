package com.mashibing.netty.s01.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * description  TODO <BR>
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/11 14:23
 **/
public class NettyServerChannelHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(NettyClient.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        logger.info("接收到消息:{}", buf.toString(CharsetUtil.UTF_8));
        try {
            NettyServer.clients.writeAndFlush(Unpooled
                    .wrappedBuffer(String.format("服务端收到!消息{%s}", buf.toString(CharsetUtil.UTF_8)).getBytes()));
//            logger.info("{}", buf);
//            logger.info("{}", buf.refCnt());
//            ctx.writeAndFlush(Unpooled.wrappedBuffer(String.format("服务端收到!消息{%s}", buf.toString(CharsetUtil.UTF_8)).getBytes()));
//            logger.info("{}", buf);
//            logger.info("{}", buf.refCnt());
        } finally {
//            buf.release();
            ReferenceCountUtil.release(buf);
            logger.info("{}", buf);
            // 0表示释放,1表示未释放
            logger.info("{}", buf.refCnt());
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("服务端首次处理消息");
        ctx.writeAndFlush(Unpooled.wrappedBuffer("服务端首次处理消息".getBytes()));
        NettyServer.clients.add(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        // 会通知NettyClient的closeFuture关闭,继续往下执行
        ctx.close();
    }
}
