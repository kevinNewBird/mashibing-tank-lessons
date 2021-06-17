package test.picture;

import javafx.scene.control.Separator;
import org.apache.commons.lang.builder.ToStringBuilder;
import sun.misc.Cleaner;
import sun.nio.ch.DirectBuffer;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * description  mmap 工具 <BR>
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/2 15:06
 **/
public class MmapStorageUtil {

    static String mmapPath = "E:\\deskFile\\trs_work\\工作任务\\新疆融媒体云--新疆日报\\3.0\\oom";

    public static void main(String[] args) throws IOException, InterruptedException {
        File file = new File("E:\\deskFile\\trs_work\\test\\贵州省政务公开标准规范（试行）-三棵树镇人民政府.png");
        TimeUnit.SECONDS.sleep(40);
//        FileInputStream fis = new FileInputStream(file);
//        System.out.println("-----");
//        fis.close();

//        testFileImageIo(new FileInputStream(file));
//        mmapFile(file);
//        testFileImageIo(file);
        testFileImageReader(file);

    }
    public static void testFileImageIo(InputStream is) throws IOException {
        BufferedImage bi = ImageIO.read(is);
        System.out.println("WIDTH[II]"+bi.getWidth());
        System.out.println("HEIGHT[II]"+bi.getHeight());
        bi = null;
    }

    public static void testFileImageIo(File file) throws IOException {
        BufferedImage bi = ImageIO.read(file);
        System.out.println("WIDTH[II]"+bi.getWidth());
        System.out.println("HEIGHT[II]"+bi.getHeight());
        bi = null;
    }

    public static void testFileImageReader(File file) throws IOException {
        ImageInputStream iis = ImageIO.createImageInputStream(file);
        Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);

        if (!iter.hasNext()) {
            throw new RuntimeException("No readers found!");
        }
        ImageReader ir = iter.next();
        ir.setInput(iis);
        ImageReadParam param = ir.getDefaultReadParam();
        System.out.println(ToStringBuilder.reflectionToString(param));
        BufferedImage bi = ir.read(0, param);
        System.out.println(bi.getWidth());
        System.out.println("WIDTH[II]"+ir.getWidth(0));
        System.out.println("HEIGHT[II]" + ir.getHeight(0));
        ir.dispose();
        iis.close();
    }

    public static void mmapFile(File file) throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(30);
        if (Objects.isNull(file)) {
            return;
        }
        long len = 0;
        if (file.isFile()) {
            // kb
            len = file.length();
            System.out.println(len);
        }
        File mmapFile = new File(mmapPath.concat(File.separator).concat("mmap.png"));
//        if (!mmapFile.exists()) {
//            mmapFile.mkdir();
//        }
        RandomAccessFile raf = new RandomAccessFile(mmapFile, "rw");
        MappedByteBuffer mmap = raf.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, len);
        InputStream is = new FileInputStream(file);
        int originLen = 0;
        byte[] cache = new byte[2024];
        while ((originLen = is.read(cache)) != -1) {
            mmap.put(cache, 0, originLen);
        }
        Cleaner cleaner = ((DirectBuffer) mmap).cleaner();
        if (cleaner != null) {
            cleaner.clean();
        }
        raf.close();

    }
}
