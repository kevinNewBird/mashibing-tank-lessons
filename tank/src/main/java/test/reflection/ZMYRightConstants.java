package test.reflection;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/***********************
 * @Description: 平行越权常量类 <BR>
 * @author: zhao.song
 * @since: 2021/4/21 9:54
 * @version: 1.0
 ***********************/
public class ZMYRightConstants {

    /**
     * 编辑权限key
     */
    public static final String CAN_EDIT = "CANEDIT";

    /**
     * 提交权限key
     */
    public static final String CAN_SUBMIT = "CANSUBMIT";

    /**
     * 部门提交权限key
     */
    public static final String CAN_DEPT_SUBMIT = "CANDEPTSUBMIT";

    /**
     * 传稿权限key
     */
    public static final String CAN_TRANSFER_DOC = "CANTRANSFERDOC";

    /**
     * 共享权限key
     */
    public static final String CAN_SHARE = "CANSHARE";


    /**
     * 废稿权限key
     */
    public static final String CAN_DISCARD_DOC = "CANDISCARDDOC";

    /**
     * 查看痕迹权限key
     */
    public static final String CAN_LOOK_TRACE = "CANLOOKTRACE";

    /**
     * 发稿单权限key
     */
    public static final String CAN_DOC_SHEET = "CANDOCSHEET";


    /**
     * 退稿权限key
     */
    public static final String CAN_REJECT_DOC = "CANREJECTDOC";

    /**
     * 管理员撤稿权限key
     */
    public static final String CAN_CANCEL_DOC = "CANCANCELDOC";

    /**
     * 收藏权限key
     */
    public static final String CAN_COLLECT_DOC = "CANCOLLECTDOC";

    /**
     * 复制建新稿权限key
     */
    public static final String CAN_COPY_DOC = "CANCOPYDOC";


    public static final List<String> rightKeyContainer = Collections.unmodifiableList(Arrays.asList(CAN_EDIT, CAN_SUBMIT
            , CAN_DEPT_SUBMIT, CAN_TRANSFER_DOC, CAN_SHARE, CAN_DISCARD_DOC, CAN_LOOK_TRACE, CAN_DOC_SHEET, CAN_REJECT_DOC
            , CAN_CANCEL_DOC, CAN_COLLECT_DOC, CAN_COPY_DOC));





}
