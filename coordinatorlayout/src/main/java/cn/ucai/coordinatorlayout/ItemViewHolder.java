package cn.ucai.coordinatorlayout;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/8/7.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {
    TextView mTv;
    public ItemViewHolder(View itemView) {
        super(itemView);
        mTv = (TextView) itemView.findViewById(R.id.tv_text);
    }
}
