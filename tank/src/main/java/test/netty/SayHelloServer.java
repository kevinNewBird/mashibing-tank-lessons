package test.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * description  sayHello 服务器 <BR>
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/3 11:01
 **/
public class SayHelloServer {

    private static Logger logger = LoggerFactory.getLogger(SayHelloServer.class);

    private final int port;

    public SayHelloServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        // 0.Netty 定义负责 [装领导] 和 [装码农] 的事件处理线程池
        NioEventLoopGroup leaderPool = new NioEventLoopGroup();
        NioEventLoopGroup coderPool = new NioEventLoopGroup();

        try {
            // 1. 服务端启动引导器
            ServerBootstrap server = new ServerBootstrap();

            // 2.定义启动引导器
            server
                    // 2.1. 把 事件处理线程池 添加进 启动引导器 中
                    .group(leaderPool, coderPool)
                    // 2.2.设置通道的建立方式(NIO/BIO)
                    .channel(NioServerSocketChannel.class)
                    // 2.3.构造一个由通道处理器构成的通道管道流水线
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 此处添加服务端的通道处理器
                            socketChannel.pipeline().addLast(new SayHelloServerHandler());
                        }
                    })
                    // 2.4.用来配置一些channel的参数, 配置的参数会被ChannelConfig使用
                    // BACKLOG 用于构造服务端 套接字 ServerSocket
                    // 标识服务器请求处理线程全满时,用于临时存放已完成三次握手的请求的队列的最大长度 ,
                    // 如果未设置或所设置的值小于1, Java 将使用默认值 50
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 2.5.是否启用心跳保活机制.
                    // 在双方TCP套接字建立连接后(即都进入ESTABLISHED状态),并且在两个小时左右上层没有任何数据传输的情况下,这套机制才会被激活
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 3.服务端绑定端口并且开始接收进来的连接亲求
            ChannelFuture channelFuture = server.bind(port).sync();

            // 4.检查操作是否成功结束了
            if (channelFuture.isSuccess()) {
                // 如果没有成功结束就处理一些事情,结束了就执行关闭服务端等操作
                logger.info("[SayHello]服务端启动成功!");
            }

            channelFuture.channel().closeFuture().sync();
            logger.info("[SayHello]服务端即将关闭!");
        } finally {
            // 5.关闭事件处理组
            leaderPool.shutdownGracefully();
            coderPool.shutdownGracefully();
            logger.info("[SayHello]服务端关闭成功!");
        }

    }

    public static void main(String[] args) throws Exception {
        SayHelloServer sayHelloServer = new SayHelloServer(9991);
        sayHelloServer.run();
    }
}
