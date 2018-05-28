package cn.ucai.contact3.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cn.ucai.contact3.R;

/**
 * Created by Administrator on 2017/4/16.
 */

public class FooterHolder extends RecyclerView.ViewHolder {
    TextView mtvFooter;
    public FooterHolder(View itemView) {
        super(itemView);
        mtvFooter = (TextView) itemView.findViewById(R.id.tvFooter);
    }
}
