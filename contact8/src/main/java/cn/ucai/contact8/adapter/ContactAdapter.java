package cn.ucai.contact8.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.ucai.contact8.I;
import cn.ucai.contact8.I2;
import cn.ucai.contact8.R;
import cn.ucai.contact8.bean.UserBean;
import cn.ucai.contact8.utils.OkImageLoader;

/**
 * Created by Administrator on 2017/4/27.
 */

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<UserBean> mContactlist;

    public ContactAdapter(Context context, ArrayList<UserBean> mContactlist) {
        this.context = context;
        this.mContactlist = mContactlist;
    }

    boolean isMore = true;
    boolean isDragging;
    String footerText;

    public void setMore(boolean more) {
        isMore = more;
        notifyDataSetChanged();
    }

    public boolean isMore() {
        return isMore;
    }

    public void setDragging(boolean dragging) {
        isDragging = dragging;
        notifyDataSetChanged();
    }

    public void setFooterText(String footerText) {
        this.footerText = footerText;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case I2.CONTACT_ITEM:
                return new ContactHolder(View.inflate(context, R.layout.activity_contact, null));
            case I2.FOOTER_TEXT:
                return new FooterHolder(View.inflate(context, R.layout.activity_footer, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder parentholder, int position) {
        if(getItemViewType(position)==I2.FOOTER_TEXT){
            FooterHolder holder = (FooterHolder) parentholder;
            holder.mFooterText.setText(footerText);
            holder.mFooterText.setVisibility(View.VISIBLE);
            return;
        }
        UserBean user=mContactlist.get(position);
        ContactHolder holder = (ContactHolder) parentholder;
        holder.mName.setText(user.getUserName());
        holder.mNick.setText(user.getNick());
        OkImageLoader.build(I.REQUEST_DOWNLOAD_AVATAR_URL+user.getUserName())
                .defaultPicture(R.drawable.default_face)
                .setDragging(isDragging)
                .imageView(holder.mAvandar)
                .showImage(context);
    }

    @Override
    public int getItemCount() {
        return mContactlist.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return I2.FOOTER_TEXT;
        }
        return I2.CONTACT_ITEM;
    }

    public void initContact(ArrayList<UserBean> partlist) {
        mContactlist.clear();
        mContactlist.addAll(partlist);
        notifyDataSetChanged();
    }

    public void addContact(ArrayList<UserBean> partlist) {
        mContactlist.addAll(partlist);
        notifyDataSetChanged();
    }
}
