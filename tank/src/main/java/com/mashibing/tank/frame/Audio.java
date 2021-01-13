package com.mashibing.tank.frame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.*;
import java.io.IOException;

/***********************
 * Description: 音频效果类 <BR>
 * author: zhao.song
 * date: 2021/1/11 23:58
 * version: 1.0
 ***********************/
public class Audio extends Thread {

    private AudioFormat audioFormat = null;
    private AudioInputStream audioInputStream = null;
    private SourceDataLine sourceDataLine = null;
    private DataLine.Info dataLine_info = null;

    private static Logger logger = LoggerFactory.getLogger(Audio.class);

    private String relativePath;

    public Audio(String relativePath) {
        this.relativePath = relativePath;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(Audio.class.getClassLoader().getResource(relativePath));
            this.audioFormat = audioInputStream.getFormat();
            dataLine_info = new DataLine.Info(SourceDataLine.class, audioFormat);
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLine_info);
        } catch (Exception e) {
            // 关于异常的捕获强烈建议使用Exception, 因为RuntimeException并不会被抓取到(如NullPointerException)
            logger.error("音视频资源[" + relativePath + "]加载失败!");
        }
    }

    @Override
    public void run() {
        try {
            final byte[] bytes = new byte[1024];
            int len = 0;
            sourceDataLine.open(audioFormat, 1024);
            sourceDataLine.start();
            while ((len = audioInputStream.read(bytes)) > 0) {
                sourceDataLine.write(bytes, 0, len);
            }
            audioInputStream.close();
            sourceDataLine.drain();
            sourceDataLine.close();
/*            AudioStream as = new AudioStream(Audio.class.getClassLoader().getResourceAsStream(relativePath));
            AudioPlayer.player.start(as);*/
        } catch (Exception e) {
            logger.error("音频播放失败!", e);
        }
    }

    public static void main(String[] args) throws IOException {
/*        AudioStream as = new AudioStream(Audio.class.getClassLoader().getResourceAsStream("audios/explode.wav"));
        AudioPlayer.player.start(as);*/
        Audio audio = new Audio("audios/explode.sss");
        audio.start();
        //Thread api提供一个可以抓捕不显示异常的方法
        audio.setUncaughtExceptionHandler((t1, e) -> {
            System.out.println(e.getMessage());
        });
    }
}
