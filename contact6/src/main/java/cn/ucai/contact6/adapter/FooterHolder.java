package cn.ucai.contact6.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cn.ucai.contact6.R;

/**
 * Created by Administrator on 2017/4/23.
 */

public class FooterHolder extends RecyclerView.ViewHolder {
    TextView mFooterText;
    public FooterHolder(View itemView) {
        super(itemView);
        mFooterText = (TextView) itemView.findViewById(R.id.tvFooterText);
    }
}
