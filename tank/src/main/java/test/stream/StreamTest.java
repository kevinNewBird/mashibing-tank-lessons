package test.stream;

import java.util.Arrays;

/**
 * description  StreamTest <BR>
 * <p>
 * author: zhao.song
 * date: created in 17:53  2021/6/28
 * company: TRS信息技术有限公司
 * version 1.0
 */
public class StreamTest {

    public static int[] MODAL_STATUS_YIQIANFA = new int[]{10, 12, 13, 14};

    public static void main(String[] args) {
        System.out.println(Arrays.stream(MODAL_STATUS_YIQIANFA).distinct().boxed().anyMatch(status -> status == 15));
    }
}
