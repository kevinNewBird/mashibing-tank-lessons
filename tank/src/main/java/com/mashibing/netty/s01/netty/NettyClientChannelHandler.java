package com.mashibing.netty.s01.netty;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * description  TODO <BR>
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/11 13:54
 **/
public class NettyClientChannelHandler extends ChannelInboundHandlerAdapter { //SimpleChannelInboundHandler  Codec

    private static Logger logger = LoggerFactory.getLogger(NettyClientChannelHandler.class);

    /**
     * description: 接收到的消息处理 <BR>
     *
     * @param ctx:
     * @param msg:
     * @return
     * @author zhao.song   2021/6/11 13:57
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 使用了Direct Memory, 直接访问了系统内存(跳过了JVM内存,也就意味着放弃了JVM的垃圾回收)
        // 效率提升的后果导致的是垃圾回收得自己处理
        ByteBuf buf = (ByteBuf) msg;
        try {
            logger.info("接收到消息:{}", buf.toString(CharsetUtil.UTF_8));
            // writeAndFlush 这个方法会自动释放内存
//            ctx.writeAndFlush(Unpooled.wrappedBuffer(String.format("客户端收到!消息{%s}", buf.toString(CharsetUtil.UTF_8)).getBytes()));
        } finally {
            // 内存释放
            buf.release();
        }

    }

    /**
     * description: 连接成功后 <BR>
     *
     * @param ctx:
     * @return
     * @author zhao.song   2021/6/11 14:07
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("首次连接成功!");
        ctx.channel().writeAndFlush(Unpooled.wrappedBuffer("客户端:首次连接成功".getBytes()));
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
