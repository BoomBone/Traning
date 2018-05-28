package cn.ucai.contact9.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/1.
 */

public class MessageBean implements Serializable {
    private boolean success;
    private String msg;

    public MessageBean() {
    }

    public MessageBean(boolean success, String msg) {
        super();
        this.success = success;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                '}';
    }
}
