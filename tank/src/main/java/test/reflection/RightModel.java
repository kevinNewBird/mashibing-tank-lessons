package test.reflection;

import io.vavr.control.Try;
import net.sf.json.JSONObject;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/***********************
 * @Description: 权限处理门面 <BR>
 * @author: zhao.song
 * @since: 2021/4/21 15:30
 * @version: 1.0
 ***********************/
public class RightModel {

    private AbstractZMYRightHandler handler;

    public RightModel(AbstractZMYRightHandler handler) {
        this.handler = handler;
    }

    /**
     * Description: TODO <BR>
     *
     * @param oViewData:
     * @param nMediaType:
     * @return {@link JSONObject}
     * @author: zhao.song
     * @date: 2021/4/25 16:09
     */
    public JSONObject handleRights(MetaViewData oViewData, int nMediaType) throws Exception {
        Objects.requireNonNull(handler, "权限处理类对象为空!");
        JSONObject result = new JSONObject();
        Map<String, Method> superMethodMap = Arrays.asList(AbstractZMYRightHandler.class.getDeclaredMethods()).stream().distinct()
                .filter(method -> method.isAnnotationPresent(Operate.class)).collect(Collectors.toMap(method -> method.getName(), method -> method));
        Map<String, Method> subMethodMap = Arrays.asList(handler.getClass().getDeclaredMethods()).stream().distinct()
                .filter(method -> method.isAnnotationPresent(Operate.class)).collect(Collectors.toMap(method -> method.getName(), method -> method));
        System.out.println(subMethodMap);
        System.out.println(superMethodMap);
        for (String key : superMethodMap.keySet()) {
            if (subMethodMap.containsKey(key)) {
                superMethodMap.put(key, subMethodMap.get(key));
            }
        }
        System.out.println(superMethodMap);
        Collection<Method> methodList = superMethodMap.values();
        for (Method method : methodList) {
            method.setAccessible(true);
            if (method.isAnnotationPresent(Operate.class)) {
                String key = method.getAnnotation(Operate.class).rightName();
                result.put(key, method.invoke(handler, (Object) null));
            }
        }
        return result;
    }

    /**
     * Description: TODO <BR>
     *
     * @param args:
     * @return
     * @author: zhao.song
     * @date:2021/4/25 16:08
     */
    public static void main(String[] args) throws Exception {
        StringJoiner idJoiner = new StringJoiner(";", String.format("[%s][ID=", "oprPrefix"), "]");
        idJoiner.add("a");
        idJoiner.add("b");
        idJoiner.add("c");
        System.out.println(idJoiner.toString());

        Try.run(() -> {
            int i = 1 / 0;
        }).onFailure(ex -> System.out.println("1111"));
        String queryString = "s=1&id=2134&title=奥里给&tom";
        int id = 0;
        Map<String, String> newMap = Arrays.stream(queryString.split("&")).distinct()
                .collect(Collectors.toMap(kvPair -> Try.of(() -> kvPair.split("=")[0]).getOrElse("default")
                        , kvPair -> Try.of(() -> kvPair.split("=")[1]).getOrElse("")));
        newMap.put("ssss", String.valueOf(id = 12));
        System.out.println(newMap);
        RightModel model = new RightModel(new DeptRightHandler());
        JSONObject jsonObject = model.handleRights(new MetaViewData(), -1);
        System.out.println(jsonObject);
    }
}
