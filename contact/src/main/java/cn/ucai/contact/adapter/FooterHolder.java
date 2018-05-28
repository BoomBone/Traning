package cn.ucai.contact.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cn.ucai.contact.R;

/**
 * Created by Administrator on 2017/4/12.
 */

public class FooterHolder extends RecyclerView.ViewHolder {
    TextView mtv;
    public FooterHolder(View itemView) {
        super(itemView);
        mtv = (TextView) itemView.findViewById(R.id.tvFooter);
    }
}
