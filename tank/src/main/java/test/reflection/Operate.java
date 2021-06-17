package test.reflection;

import java.lang.annotation.*;

/***********************
 * @Description: 操作权限注解 <BR>
 * @author: zhao.song
 * @since: 2021/4/21 16:01
 * @version: 1.0
 ***********************/
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Operate {

    String rightName() default "";
}
