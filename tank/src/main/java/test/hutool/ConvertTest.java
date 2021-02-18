package test.hutool;

import cn.hutool.core.convert.Convert;
import cn.hutool.http.HttpUtil;

import java.util.Optional;
import java.util.stream.Stream;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/2/18 14:04
 * @version: 1.0
 ***********************/
public class ConvertTest {

    public static void main(String[] args) {

        Integer[] intArray = Stream.of(1, 2, 3, 4, 5).toArray(Integer[]::new);
        String bStr = Convert.toStr(intArray);
        System.out.println(bStr);
        Optional.of(bStr).ifPresent(System.out::println);
    }
}
