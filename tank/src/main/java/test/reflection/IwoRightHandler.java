package test.reflection;

import io.vavr.control.Either;
import net.sf.json.JSONObject;

/***********************
 * @Description: iwo权限处理器 <BR>
 * @author: zhao.song
 * @since: 2021/4/21 10:41
 * @version: 1.0
 ***********************/
public class IwoRightHandler extends AbstractZMYRightHandler {
    @Override
    public String key() {
        return String.valueOf(-1);
    }

    @Override
    public String desc() {
        return "";
    }


}
