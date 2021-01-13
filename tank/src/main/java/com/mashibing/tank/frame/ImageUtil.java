package com.mashibing.tank.frame;


import java.awt.*;
import java.awt.image.BufferedImage;

/***********************
 * Description: 图片操纵类,用于控制图片的旋转 <BR>
 * author: zhao.song
 * date: 2021/1/13 15:40
 * version: 1.0
 ***********************/
public final class ImageUtil {

    /**
     * Description: 图片旋转 <BR>
     *
     * @param srcBufferedImg:
     * @param degree:
     * @return {@link java.awt.image.BufferedImage}
     * @author zhao.song    2021/1/13 15:42
     */
    public static final BufferedImage rotateImage(final BufferedImage srcBufferedImg, final int degree) {
        int srcWidth = srcBufferedImg.getWidth();
        int srcHeight = srcBufferedImg.getHeight();
        // 原图透明度
        int srcTransparency = srcBufferedImg.getColorModel().getTransparency();

        BufferedImage newImg;
        Graphics2D g2 = (newImg = new BufferedImage(srcWidth, srcHeight, srcTransparency))
                .createGraphics();
        //设置多种着色微调
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        // toRadians角度转换为弧度,并旋转
        g2.rotate(Math.toRadians(degree), srcWidth / 2.0, srcHeight / 2.0);
        g2.drawImage(srcBufferedImg, 0, 0, null);
        g2.dispose();
        return newImg;
    }

}
