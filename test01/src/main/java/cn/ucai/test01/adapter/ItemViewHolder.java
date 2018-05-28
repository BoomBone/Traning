package cn.ucai.test01.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cn.ucai.test01.R;

/**
 * Created by Administrator on 2017/8/3.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {
    TextView mNum;
    public ItemViewHolder(View itemView) {
        super(itemView);
        mNum = (TextView) itemView.findViewById(R.id.tv_num_holder);
    }
}
