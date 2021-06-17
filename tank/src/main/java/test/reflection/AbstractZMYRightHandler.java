package test.reflection;

import io.vavr.control.Either;
import net.sf.json.JSONObject;

/***********************
 * @Description: <BR>
 * @author: zhao.song
 * @since: 2021/4/21 13:30
 * @version: 1.0
 ***********************/
public abstract class AbstractZMYRightHandler implements IZMYRightHandler {

    @Override
    public JSONObject doCheckAllRight(MetaViewData oViewData, int nMediaType) throws Exception {

        // 使用门面处理通用的权限逻辑
        RightModel model = new RightModel(this);
        return model.handleRights(oViewData, nMediaType);
    }


    /**
     * Description: 编辑权限判断 <BR>
     *
     * @param loginUser:
     * @return {@link net.sf.json.JSONObject}
     * @author zhao.song    2021/4/21 14:36
     */
    @Operate(rightName = ZMYRightConstants.CAN_EDIT)
    protected boolean doCanEdit(Object loginUser) {
        return false;
    }


    /**
     * Description: 提交权限判断 <BR>
     *
     * @param loginUser:
     * @return {@link net.sf.json.JSONObject}
     * @author zhao.song    2021/4/21 14:41
     */
    @Operate(rightName = ZMYRightConstants.CAN_SUBMIT)
    protected boolean doCanSubmit(Object loginUser) {
        return false;
    }

    /**
     * Description: 部门提交权限 <BR>
     *
     * @param loginUser:
     * @return {@link net.sf.json.JSONObject}
     * @author zhao.song    2021/4/21 14:41
     */
    @Operate(rightName = ZMYRightConstants.CAN_DEPT_SUBMIT)
    protected boolean doCanDeptSubmit(Object loginUser) {
        return false;
    }

    /**
     * Description: 传稿权限 <BR>
     *
     * @param loginUser:
     * @author zhao.song    2021/4/21 14:42
     */
    @Operate(rightName = ZMYRightConstants.CAN_TRANSFER_DOC)
    protected boolean doCanTransferDoc(Object loginUser) {
        return false;
    }


    /**
     * Description: 共享权限 <BR>
     *
     * @param loginUser:
     * @author zhao.song    2021/4/21 14:42
     */
    @Operate(rightName = ZMYRightConstants.CAN_SHARE)
    protected boolean doCanShare(Object loginUser) {
        return false;
    }

    /**
     * Description: 废稿权限 <BR>
     *
     * @param loginUser:
     * @author zhao.song    2021/4/21 14:43
     */
    @Operate(rightName = ZMYRightConstants.CAN_DISCARD_DOC)
    protected boolean doCanDiscardDoc(Object loginUser) {
        return false;
    }

    /**
     * Description: 查看痕迹权限 <BR>
     *
     * @param loginUser:
     * @author zhao.song    2021/4/21 14:43
     */
    @Operate(rightName = ZMYRightConstants.CAN_LOOK_TRACE)
    protected boolean doCanLookTrace(Object loginUser) {
        return false;
    }

    /**
     * Description: 发稿单 <BR>
     *
     * @param loginUser:
     * @author zhao.song    2021/4/21 14:44
     */
    @Operate(rightName = ZMYRightConstants.CAN_DOC_SHEET)
    protected boolean doCanDocSheet(Object loginUser) {
        return false;
    }

    /**
     * Description: 退稿权限 <BR>
     *
     * @param loginUser:
     * @author zhao.song    2021/4/21 14:45
     */
    @Operate(rightName = ZMYRightConstants.CAN_REJECT_DOC)
    protected boolean doCanRejectDoc(Object loginUser) {
        return false;
    }

    /**
     * Description: 管理员撤稿 <BR>
     *
     * @param loginUser:
     * @author zhao.song    2021/4/21 14:45
     */
    @Operate(rightName = ZMYRightConstants.CAN_CANCEL_DOC)
    protected boolean doCanCancelDoc(Object loginUser) {
        return false;
    }

    /**
     * Description: 收藏权限 <BR>
     *
     * @param loginUser:
     * @author zhao.song    2021/4/21 14:45
     */
    @Operate(rightName = ZMYRightConstants.CAN_COLLECT_DOC)
    protected boolean doCanCollectDoc(Object loginUser) {
        return false;
    }


    /**
     * Description: 复制建新稿权限 <BR>
     *
     * @param loginUser:
     * @author zhao.song    2021/4/21 14:46
     */
    @Operate(rightName = ZMYRightConstants.CAN_COPY_DOC)
    protected boolean doCanCopyDoc(Object loginUser) {
        return false;
    }


}
