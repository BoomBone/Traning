package cn.ucai.test.bean;

/**
 * Created by hutcwp on 2017/7/31.
 * Mail : hutcwp@foxmail.com
 * Blog : hutcwp.club
 * GitHub : github.com/hutcwp
 */

/**
 * Item 实体类 用做Adapter的填充数据
 */
public class Item {

    /**
     * selectStatus 用于表示，是否被选中
     */
    String i;

    public Item(String i) {
        this.i = i;
    }

    public Item() {
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    private boolean selectStatus = false;

    public boolean isSelectStatus() {
        return selectStatus;
    }

    public void setSelectStatus(boolean selectStatus) {
        this.selectStatus = selectStatus;
    }

}
