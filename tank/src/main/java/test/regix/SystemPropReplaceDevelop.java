package test.regix;

import org.jsoup.helper.StringUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***********************
 * Description: TODO <BR>
 * author: zhao.song
 * date: 2020/12/16 18:28
 * version: 1.0
 ***********************/
public class SystemPropReplaceDevelop {

    static Map<String, Object> props = new HashMap<>();

    static {
        try {
            Properties props0 = new Properties();
            props0.load(new FileInputStream("E:\\classloader\\test0.properties"));
            Enumeration<String> keys = (Enumeration<String>) props0.propertyNames();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                SystemPropReplaceDevelop.props.put(key, props0.getProperty(key));
            }
        } catch (IOException e) {

        }
/*        ResourceBundle bundle = ResourceBundle.getBundle("test0");
        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            SystemPropReplaceDevelop.props.put(key, bundle.getString(key));
        }*/
    }

    public static final String PLACEHOLDER_REGEX = "[$][{]\\w+[_.-]*\\w+[}][$]";

    public static void main(String[] args) {
        String test0 = "${test_url}$:8806/${findById}$";
        final Pattern pattern = Pattern.compile(PLACEHOLDER_REGEX);
        final Matcher matcher = pattern.matcher(test0);
        while (matcher.find()) {
            String placeholder = matcher.group();
            System.out.println(placeholder);
            String realStr = showEmpty(props.get(matcher.group()), "");
            if (StringUtil.isBlank(realStr)) {
                continue;
            }
            test0 = test0.replace(placeholder, realStr);
        }

        System.out.println(test0);
    }

    private static String showEmpty(Object source, String target) {
        return source == null ? target : source.toString();
    }


}
