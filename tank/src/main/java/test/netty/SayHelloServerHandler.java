package test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * description  服务端入站处理器适配器的继承类 <BR>
 * function: 用来处理服务端的一些事情,根据需要来实现一些方法
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/3 13:23
 **/
public class SayHelloServerHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(SayHelloServerHandler.class);


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;

        try {
            logger.info("服务端处理的信息:" + buf.toString(CharsetUtil.UTF_8));
            ByteBuf res = Unpooled.wrappedBuffer(new String("塔台收到!塔台收到!信息如下,请确认 "
                    + buf.toString(CharsetUtil.UTF_8)).getBytes());

            //给客户端回复消息
            ctx.writeAndFlush(res);
        } finally {
            buf.release();
        }
    }

    /**
     * description: 连接成功后,自动执行该方法 <BR>
     *
     * @param ctx:
     * @return
     * @author  zhao.song   2021/6/4 10:49
    */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("服务器首次处理!");
        // 这种发送的消息格式是错误的!!!!
        // 消息格式必须是 ByteBuf 才行!!!
        ByteBuf buf = Unpooled.wrappedBuffer("hello is server!".getBytes());
        ctx.writeAndFlush(buf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        logger.error("消息获取发生异常!", cause);
        ctx.close();
    }
}
