package cn.ucai.contact5.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.ucai.contact5.R;

/**
 * Created by Administrator on 2017/4/22.
 */

public class ContactHolder extends RecyclerView.ViewHolder {
    ImageView mAvadar;
    TextView mName,mNick;
    public ContactHolder(View view) {
        super(view);
        mAvadar = (ImageView) view.findViewById(R.id.imAvadar);
        mName = (TextView) view.findViewById(R.id.tvName);
        mNick = (TextView) view.findViewById(R.id.tvNick);
    }
}
