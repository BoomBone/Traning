package cn.ucai.contact9.model.net;

import cn.ucai.contact9.model.utils.OkUtils;

/**
 * Created by Administrator on 2017/5/1.
 */

public class ModleBase implements IModleBase {
    @Override
    public void release() {
        OkUtils.release();
    }
}
