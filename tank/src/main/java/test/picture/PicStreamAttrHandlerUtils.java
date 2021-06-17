package test.picture;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.apache.commons.imaging.formats.jpeg.exif.ExifRewriter;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

/***********************
 * @Description: 图片流属性处理工具类 <BR>
 * @author: zhao.song
 * @since: 2021/5/8 10:19
 * @version: 1.0
 ***********************/
public final class PicStreamAttrHandlerUtils {

    public static void main(String[] args) throws IOException {
        File oImgFile = new File("E:\\wallpapers\\W020210506626563994689.jpeg");
        File dstFile = new File("E:\\wallpapers\\alita_new1.jpeg");
        removeExifTagV4(oImgFile, dstFile);
    }
    public static void removeExifTagV2(final File oImgFile, final File dstFile) {
        try (OutputStream dstOs = new BufferedOutputStream(new FileOutputStream(dstFile))) {
            new ExifRewriter().removeExifMetadata(oImgFile,dstOs);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void removeExifTagV3(final File oImgFile, final File dstFile)  {
        try (OutputStream dstOs = new BufferedOutputStream(new FileOutputStream(dstFile))){
            ImageMetadata metadata = Imaging.getMetadata(new FileInputStream(oImgFile), oImgFile.getName());
//            ImageMetadata metadata = Imaging.getMetadata(oImgFile);
            if (metadata instanceof JpegImageMetadata){
                new ExifRewriter().removeExifMetadata(oImgFile,dstOs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean removeExifTagV4(final File oImgFile, final File dstFile) throws IOException {
//        FileImageInputStream is = new FileImageInputStream(new File("E:\\deskFile\\trs_work\\工作任务\\新疆融媒体云--新疆日报\\素材\\贵州省政务公开标准规范（试行）-三棵树镇人民政府.png"));
        BufferedImage image = ImageIO
                .read(new File("E:\\deskFile\\trs_work\\工作任务\\新疆融媒体云--新疆日报\\素材\\贵州省政务公开标准规范（试行）-三棵树镇人民政府.png"));
        System.in.read();
        try {
            InputStream sourceImgInStream = new FileInputStream(oImgFile);
            OutputStream dstImgOutStream = new BufferedOutputStream(new FileOutputStream(dstFile));
            String sFileName = dstFile.getName();
            // 查看源码原则上只会有jpeg图片存在exif信息
            ImageMetadata metadata = Imaging.getMetadata(sourceImgInStream, null);
//
//            if (metadata instanceof JpegImageMetadata) {
                new ExifRewriter().removeExifMetadata(sourceImgInStream, dstImgOutStream);
                return true;
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static void removeExifTag(final File oImgFile, final File dstFile) {

        try (InputStream dstIs = new FileInputStream(dstFile)) {
            Metadata metadata = ImageMetadataReader.readMetadata(oImgFile);
            Iterable<Directory> directories = metadata.getDirectories();
//            directories.forEach(System.out::println);
            Collection<ExifSubIFDDirectory> exifDirectory = metadata.getDirectoriesOfType(ExifSubIFDDirectory.class);
            Iterator<Directory> iterator = directories.iterator();
            while (iterator.hasNext()) {
                Directory currDirectory = iterator.next();
                System.out.println(currDirectory.getName());
                if (currDirectory.getName().equals("Exif SubIFD")) {
                    iterator.remove();
                }
            }
            System.out.println("---------------------------------------");
            metadata.getDirectories().forEach(System.out::println);


//            System.out.println("--------------------------------------------------");
//            exifDirectory.stream().forEach(dir -> {
//                dir.getTags().forEach(tag -> System.out.println(tag.getDirectoryName() + ":" + tag.getTagName() + ":" +dir.getString(tag.getTagType()) ));
//            });
//            Iterable<Directory> directories = metadata.getDirectories();
//            Iterator<Directory> iterator = directories.iterator();
//            while (iterator.hasNext()) {
//                Collection<Tag> tags = iterator.next().getTags();
//                System.out.println(tags);
//                tags.stream().forEach();
//                tags.removeIf(tag -> tag.getTagName())
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
