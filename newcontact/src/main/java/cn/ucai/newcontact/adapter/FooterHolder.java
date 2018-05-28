package cn.ucai.newcontact.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cn.ucai.newcontact.R;

/**
 * Created by Administrator on 2017/4/21.
 */

public class FooterHolder extends RecyclerView.ViewHolder {
    TextView mtvFooter;
    public FooterHolder(View itemView) {
        super(itemView);
        mtvFooter = (TextView) itemView.findViewById(R.id.tvFooter);
    }
}
