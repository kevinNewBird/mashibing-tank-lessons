package test.reflection;

import io.vavr.control.Either;
import net.sf.json.JSONObject;

/***********************
 * @Description: 部门稿库权限处理器 <BR>
 * @author: zhao.song
 * @since: 2021/4/21 10:41
 * @version: 1.0
 ***********************/
public class DeptRightHandler extends AbstractZMYRightHandler {

    @Override
    public String key() {
        return -2 + "";
    }

    @Override
    public String desc() {
        return "";
    }

    @Override
    @Operate
    protected boolean doCanCancelDoc(Object loginUser) {
        return true;
    }

    protected Object obtainRightHost(MetaViewData oViewData, int nMediaType) throws Exception {
        return null;
    }
}
