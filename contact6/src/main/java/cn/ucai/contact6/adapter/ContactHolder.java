package cn.ucai.contact6.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.ucai.contact6.R;

/**
 * Created by Administrator on 2017/4/23.
 */

public class ContactHolder extends RecyclerView.ViewHolder {
    ImageView mAvandar;
    TextView mName,mNick;
    public ContactHolder(View itemView) {
        super(itemView);
        mAvandar = (ImageView) itemView.findViewById(R.id.imAvandar);
        mName = (TextView) itemView.findViewById(R.id.tvName);
        mNick = (TextView) itemView.findViewById(R.id.tvNick);
    }
}
