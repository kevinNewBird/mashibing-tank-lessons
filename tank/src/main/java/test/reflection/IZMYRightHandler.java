package test.reflection;

import net.sf.json.JSONObject;

/***********************
 * @Description: 采编中心访问者接口 <BR>
 * @author: zhao.song
 * @since: 2021/4/21 10:17
 * @version: 1.0
 ***********************/
public interface IZMYRightHandler {


    public String key();

    public String desc();

    /**
     * Description: 权限访问 <BR>
     *
     * @param oViewData:
     * @return {@link net.sf.json.JSONObject}
     * @author zhao.song    2021/4/21 10:19
     */
    JSONObject doCheckAllRight(MetaViewData oViewData, int nMediaType) throws Exception;
}
