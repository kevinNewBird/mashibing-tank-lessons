package com.mashibing.tank.frame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/***********************
 * Description: 资源管理类 <BR>
 *     <p>
 *         类似图片这种,一般都是先将需要用的资源加载进来(loading)
 *     </p>
 * author: zhao.song
 * date: 2020/12/22 0:33
 * version: 1.0
 ***********************/
public class ResourceMgr {
    private static Logger logger = LoggerFactory.getLogger(ResourceMgr.class);
    public static BufferedImage goodTankL, goodTankU, goodTankR, goodTankD;
    // 好坦克的灯光效果
    public static BufferedImage goodTankL_light, goodTankU_light, goodTankR_light, goodTankD_light;
    public static BufferedImage badTankL, badTankU, badTankR, badTankD;
    // 坏坦克的灯光效果
    public static BufferedImage badTankL_light, badTankU_light, badTankR_light, badTankD_light;
    public static BufferedImage bulletL, bulletU, bulletR, bulletD;
    //爆炸效果
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
//            int i = 1 / 0;
            goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankL = ImageUtil.rotateImage(goodTankU, -90);
            goodTankR = ImageUtil.rotateImage(goodTankU, 90);
            goodTankD = ImageUtil.rotateImage(goodTankU, 180);
            goodTankD_light = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank2.png"));
            goodTankD_light = ImageUtil.rotateImage(goodTankD_light, -90);
            goodTankD_light = ImageUtil.rotateImage(goodTankD_light, 90);
            goodTankD_light = ImageUtil.rotateImage(goodTankD_light, 180);
            badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankL = ImageUtil.rotateImage(badTankU, -90);
            badTankR = ImageUtil.rotateImage(badTankU, 90);
            badTankD = ImageUtil.rotateImage(badTankU, 180);
            badTankD_light = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank2.png"));
            badTankD_light = ImageUtil.rotateImage(badTankD_light, -90);
            badTankD_light = ImageUtil.rotateImage(badTankD_light, 90);
            badTankD_light = ImageUtil.rotateImage(badTankD_light, 180);
            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            bulletR = ImageUtil.rotateImage(bulletU, 90);
            bulletD = ImageUtil.rotateImage(bulletU, 180);
            for (int i = 0; i < explodes.length; i++) {
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
            }
        } catch (Exception e) {
            logger.error("初始化资源加载失败!", e);
        }

    }

    public static void main(String[] args) {

    }
}
