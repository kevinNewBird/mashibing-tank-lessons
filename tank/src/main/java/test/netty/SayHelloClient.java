package test.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * description  sayHello 客户端 <BR>
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/4 10:51
 **/
public class SayHelloClient {

    private static Logger logger = LoggerFactory.getLogger(SayHelloServer.class);

    private int port;

    private String host = "127.0.0.1";

    private Channel channel;

    public SayHelloClient(int port) {
        this.port = port;
    }

    /**
     * description: 客户端运行方法 <BR>
     *
     * @param :
     * @return
     * @author zhao.song   2021/6/4 10:53
     */
    public void run() throws InterruptedException {
        // 0.负责装客户端的事件处理线程池
        NioEventLoopGroup clientWorker = new NioEventLoopGroup();
        try {
            // 1.netty客户端引导启动器
            Bootstrap bootstrap = new Bootstrap();
            bootstrap
                    // 1.1.把事件处理线程池添加进启动引导器
                    .group(clientWorker)
                    // 1.2.设置通道的建立方式, 这里采用Nio 的通道方式来建立请求连接
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //此处添加客户端的通道处理器
                            socketChannel.pipeline().addLast(new SayHelloClientHandler());
                        }
                    });
            // 2.客户端绑定端口并且开始发送连接请求
            ChannelFuture future = bootstrap.connect(host, port).sync();
            if (future.isSuccess()) {
                logger.info("[SayHello]客户端连接服务器成功!");
            }
            // 3.将通道设置好,以便外面获取
            this.channel = future.channel();

            // 4.关闭客户端
            channel.closeFuture().sync();
            logger.info("[SayHello]客户端即将关闭!");
        } finally {
            clientWorker.shutdownGracefully();
            logger.info("[SayHello]客户端关闭成功!");
        }

    }

    public Channel getChannel() {
        return this.channel;
    }
}
