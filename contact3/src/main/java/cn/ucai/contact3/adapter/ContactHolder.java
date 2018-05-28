package cn.ucai.contact3.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.ucai.contact3.R;

/**
 * Created by Administrator on 2017/4/16.
 */

public class ContactHolder extends RecyclerView.ViewHolder {
    ImageView mimContactPhoto;
    TextView mtvName,mtvNick;
    public ContactHolder(View itemView) {
        super(itemView);
        mimContactPhoto = (ImageView) itemView.findViewById(R.id.imContact);
        mtvName = (TextView) itemView.findViewById(R.id.tvName);
        mtvNick = (TextView) itemView.findViewById(R.id.tvNick);
    }
}
