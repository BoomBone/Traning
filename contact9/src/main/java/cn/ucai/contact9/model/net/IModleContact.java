package cn.ucai.contact9.model.net;

import android.content.Context;

import cn.ucai.contact9.bean.UserBean;
import cn.ucai.contact9.model.utils.OkUtils;

/**
 * Created by Administrator on 2017/5/1.
 */

public interface IModleContact extends IModleBaseImage {
    void downloadContactList(Context context, String userName, int pageId, int action, OkUtils.OnCompleteListener<UserBean[]> listener);
}
