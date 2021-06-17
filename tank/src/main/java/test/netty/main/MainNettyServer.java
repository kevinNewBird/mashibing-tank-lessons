package test.netty.main;

import test.netty.SayHelloServer;

/**
 * description  Netty server 使用main类 <BR>
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/4 11:20
 **/
public class MainNettyServer {

    public static void main(String[] args) throws Exception {
        SayHelloServer server = new SayHelloServer(8686);
        server.run();
    }
}
