package cn.ucai.contact2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.ucai.contact2.R;

/**
 * Created by Administrator on 2017/4/15.
 */

public class ContactHolder extends RecyclerView.ViewHolder {
    ImageView mivAvadar;
    TextView mtvName,mtvNick;
    public ContactHolder(View itemView) {
        super(itemView);
        mivAvadar = (ImageView) itemView.findViewById(R.id.ivAvadar);
        mtvName = (TextView) itemView.findViewById(R.id.tvName);
        mtvNick = (TextView) itemView.findViewById(R.id.tvNick);
    }
}
