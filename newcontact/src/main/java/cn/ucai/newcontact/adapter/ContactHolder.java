package cn.ucai.newcontact.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.ucai.newcontact.R;

/**
 * Created by Administrator on 2017/4/21.
 */

public class ContactHolder extends RecyclerView.ViewHolder {
    ImageView mimAvadar;
    TextView mName, mNick;

    public ContactHolder(View itemView) {
        super(itemView);
        mimAvadar = (ImageView) itemView.findViewById(R.id.imAvadar);
        mName = (TextView) itemView.findViewById(R.id.tvName);
        mNick = (TextView) itemView.findViewById(R.id.tvNick);
    }
}
