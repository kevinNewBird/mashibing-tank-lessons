package test.container;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/2/4 10:40
 * @version: 1.0
 ***********************/
public class IntStreamTest {

    final static String NUMBER_REGEX = "[1-9][0-9]*";


    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(NUMBER_REGEX);

        Map<String, String> map0 = new HashMap<>();
        map0.put("1", "1");
        map0.put("2", "b");
        map0.put("3", "c");
        List<String> collect = IntStream.range(0, 5).collect(ArrayList::new, (result, t) -> result.add(map0.get(String.valueOf(++t))), ArrayList::addAll).stream().distinct()
                .map(String::valueOf).filter(value -> pattern.matcher(value).find()).collect(Collectors.toList());

        System.out.println(collect);

        //---------------------------------------------------------
        int somearray[] = new int[] { 1, 5, 6, 7 };

        Map<String, Integer> map1 = IntStream.range(0, 4).collect(
                ()->{
                    Map<String, Integer> innerMap = new HashMap<>();
                    innerMap.put("inner",1);
                    return innerMap;
                },
                (result, t) -> result.put("test" + t, somearray[t]),
                (left, right) -> left.putAll(right));
        System.out.println(map1);
    }
}
