package cn.ucai.contact7.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cn.ucai.contact7.R;

/**
 * Created by Administrator on 2017/4/26.
 */

public class FooterHolder extends RecyclerView.ViewHolder {
    TextView mtvFooterText;
    public FooterHolder(View itemView) {
        super(itemView);
        mtvFooterText = (TextView) itemView.findViewById(R.id.tvFooterText);
    }
}
