package cn.ucai.contact9.model.net;

import android.content.Context;

import cn.ucai.contact9.bean.UserBean;
import cn.ucai.contact9.inter.I;
import cn.ucai.contact9.model.utils.OkUtils;

/**
 * Created by Administrator on 2017/5/1.
 */

public class ModleContact extends ModleBaseImage implements IModleContact {
    @Override
    public void downloadContactList(Context context, String userName, int pageId,int action, OkUtils.OnCompleteListener<UserBean[]> listener) {
        OkUtils<UserBean[]> utils = new OkUtils<>(context);
        utils.url(I.SERVER_URL)
                .addParam(I.KEY_REQUEST,I.REQUEST_DOWNLOAD_CONTACT_LIST)
                .addParam(I.User.USER_NAME,userName)
                .addParam(I.PAGE_ID,pageId+"")
                .addParam(I.PAGE_SIZE,"10")
                .targetClass(UserBean[].class)
                .execute(listener);
    }
}
