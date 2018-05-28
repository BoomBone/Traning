package cn.ucai.contact9.controller.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.ucai.contact9.R;

/**
 * Created by Administrator on 2017/4/28.
 */

public class ContactHolder extends RecyclerView.ViewHolder {
    ImageView mAvadar;
    TextView mName,mNick;
    public ContactHolder(View itemView) {
        super(itemView);
        mAvadar = (ImageView) itemView.findViewById(R.id.imAvadar);
        mName = (TextView) itemView.findViewById(R.id.tvName);
        mNick = (TextView) itemView.findViewById(R.id.tvNick);
    }
}
