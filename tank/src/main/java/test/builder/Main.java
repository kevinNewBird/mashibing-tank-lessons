package test.builder;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * description  Main <BR>
 * <p>
 * author: zhao.song
 * date: created in 17:14  2021/6/22
 * company: TRS信息技术有限公司
 * version 1.0
 */
public class Main {

    public static Pattern pattern = Pattern.compile("(\\d:\\S+)");

    private static String handlePubMedia(String attrValue) {
        if (StringUtils.isBlank(attrValue)) {
            return attrValue;
        }

        Map<String, String> productInfo = Arrays.stream(attrValue.toString().split(","))
                .filter(cell -> pattern.matcher(cell).find())
                .collect(Collectors.groupingBy(value -> value.split(":")[0]
                        , Collectors.mapping(value -> value.split(":")[1], Collectors.joining(","))));
        if (!Objects.isNull(productInfo) && !productInfo.isEmpty()) {
            final StringBuilder newAttrValue = new StringBuilder();
            productInfo.keySet().stream().map(Integer::parseInt).sorted().forEach(mediaType -> {
                // -20为自定义类型,用于处理大数据同步过来的数据没有媒体类型的情况
                newAttrValue.append(mediaType == -20 ? "" : "默认媒体".concat(":"))
                        .append("【").append(productInfo.get(String.valueOf(mediaType))).append("】,");
            });

            attrValue = newAttrValue.toString().substring(0, newAttrValue.length() == 0
                    ? 0 : newAttrValue.length() - 1);
        }
        return attrValue;
    }

    public static void main(String[] args) {
        System.out.println(handlePubMedia("-20:我是谁"));
//        ExcelExportTest<String> e0 = new ExcelExportTest.ExcelExportTestBuilder<String>()
//                .buildFileName("1").buildCellData(new ArrayList<>()).build();
//        ExcelExportTest<String> e1 = new ExcelExportTest.ExcelExportTestBuilder<String>()
//                .buildFileName("2").buildCellData(new ArrayList<>()).build();
//
//        System.out.println(e0.getData().equals(e1.getData()));
//        System.out.println(e0.getData().getCellData() == e1.getData().getCellData());

    }
}
