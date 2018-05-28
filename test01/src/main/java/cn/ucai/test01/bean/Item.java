package cn.ucai.test01.bean;

/**
 * Created by Administrator on 2017/8/3.
 */

public class Item {
    public Item(String num) {
        this.num = num;
    }

    String num;
    boolean isSelect = false;

    public boolean isSelect() {

        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
