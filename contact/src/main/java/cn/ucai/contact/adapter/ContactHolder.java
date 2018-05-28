package cn.ucai.contact.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.ucai.contact.R;

/**
 * Created by Administrator on 2017/4/12.
 */

public class ContactHolder extends RecyclerView.ViewHolder {
    ImageView mim;
    TextView mtvName,mtvNick;
    public ContactHolder(View itemView) {
        super(itemView);
        mim = (ImageView) itemView.findViewById(R.id.imPhoto);
        mtvName = (TextView) itemView.findViewById(R.id.tvName);
        mtvNick = (TextView) itemView.findViewById(R.id.tvNick);
    }
}
