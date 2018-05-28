package cn.ucai.contact7.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.ucai.contact7.R;

/**
 * Created by Administrator on 2017/4/26.
 */

public class ContactHolder extends RecyclerView.ViewHolder {
    ImageView mivAvandar;
    TextView mName,mNick;
    public ContactHolder(View itemView) {
        super(itemView);
        mivAvandar = (ImageView) itemView.findViewById(R.id.imAvadar);
        mName = (TextView) itemView.findViewById(R.id.tvName);
        mNick = (TextView) itemView.findViewById(R.id.tvNick);
    }
}
