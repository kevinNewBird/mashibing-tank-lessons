package test.vavr;

import io.vavr.control.Try;

/***********************
 * Description: TODO <BR>
 * author: zhao.song
 * date: 2021/1/21 15:25
 * version: 1.0
 ***********************/
public class TryTest {

    public static void main(String[] args) throws Exception {
        Try.run(() -> {int i=1/0;}).getOrElseThrow(e -> new Exception(
                "报题正在被用户[" + 1111 + "]编辑，不能审批报题！"));
    }
}
