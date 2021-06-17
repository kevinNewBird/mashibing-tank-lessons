package test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * description  客户端处理器 <BR>
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/4 11:05
 **/
public class SayHelloClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SayHelloClientHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;

        try {
            logger.info("客户端接收到的消息:{}", buf.toString(CharsetUtil.UTF_8));
            logger.info(LocalDateTime.ofInstant(new Date((long) (buf.readUnsignedInt() - 2208988822l)).toInstant()
                    , ZoneId.systemDefault()).toString());
            ctx.writeAndFlush(("客户端收到!消息:" + buf.toString(CharsetUtil.UTF_8)).getBytes());
        } finally {
            buf.release();
        }
    }

    /**
     * description: 连接成功后，自动执行该方法 <BR>
     *
     * @param ctx:
     * @return  
     * @author  zhao.song   2021/6/4 11:19
    */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /**
         * 往服务端发送消息
         * 消息格式必须是ByteBuf才行!!!!!
         * 如果是其他格式服务端是接收不到的!!!!
         */
        String helo = "你好呀!";
        ByteBuf byteBuf = Unpooled.wrappedBuffer(helo.getBytes());
        ctx.channel().writeAndFlush(byteBuf);
        logger.info("首次连接完成!");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
