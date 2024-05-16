package com.mashibing.netty.s02;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import org.apache.commons.lang.StringUtils;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

/**
 * description  客户端界面 <BR>
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/17 8:43
 **/
public class ClientFrame extends Frame {
    TextArea ta = new TextArea();
    TextField tf = new TextField();
    ChatNettyClient chatClient = new ChatNettyClient(this);

    public ClientFrame() throws HeadlessException {
        this.setSize(600, 400);
        this.setLocation(100, 20);
        this.setVisible(true);
        this.add(ta, BorderLayout.CENTER);
        this.add(tf, BorderLayout.SOUTH);

        // 开启netty 客户端
        new Thread(chatClient::run).start();

        tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String oldText = ta.getText();
                String curText = tf.getText();
                String fullText = StringUtils.isBlank(oldText) ? curText
                        : oldText + "\n" + curText;
//                ta.setText(StringUtils.isBlank(oldText) ? curText
//                        : oldText + "\n" + curText);
                tf.setText("");
                // 多客户端字符串信息同步
                // 这样做存在的问题, 消息过多会导致内存泄露问题
                while (true) {
                    Channel channel = chatClient.getChannel();
                    if (!Objects.isNull(channel)) {
                        channel.writeAndFlush(Unpooled.wrappedBuffer(fullText.getBytes()));
                        break;
                    }
                }
            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        new ClientFrame();
    }
}
