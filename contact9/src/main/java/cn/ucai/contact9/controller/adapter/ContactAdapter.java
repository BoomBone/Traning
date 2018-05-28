package cn.ucai.contact9.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.ucai.contact9.inter.I;
import cn.ucai.contact9.inter.I2;
import cn.ucai.contact9.R;
import cn.ucai.contact9.bean.UserBean;
import cn.ucai.contact9.model.net.IModleContact;
import cn.ucai.contact9.model.net.ModleContact;

/**
 * Created by Administrator on 2017/4/28.
 */

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<UserBean> mContactList;
    IModleContact mModle;

    public ContactAdapter(Context context, ArrayList<UserBean> mContactList) {
        this.context = context;
        this.mContactList = mContactList;
        mModle = new ModleContact();
    }
    String mFooterText;
    boolean isMore=true;
    boolean isDragging;

    public void setmFooterText(String mFooterText) {
        this.mFooterText = mFooterText;
        notifyDataSetChanged();
    }

    public void setMore(boolean more) {
        isMore = more;
        notifyDataSetChanged();
    }

    public void setDragging(boolean dragging) {
        isDragging = dragging;
        notifyDataSetChanged();
    }

    public boolean isMore() {
        return isMore;
    }

    public boolean isDragging() {
        return isDragging;

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
            holder.mFooterText.setText(mFooterText);
            holder.mFooterText.setVisibility(View.VISIBLE);
            return;
        }
        UserBean user = mContactList.get(position);
        ContactHolder holder = (ContactHolder) parentholder;
        holder.mName.setText(user.getUserName());
        holder.mNick.setText(user.getNick());
        mModle.showImage(context,I.REQUEST_DOWNLOAD_AVATAR_URL+user.getUserName(),holder.mAvadar,R.drawable.default_face,isDragging);
      /*  OkImageLoader.build(I.REQUEST_DOWNLOAD_AVATAR_URL+user.getUserName())
                .defaultPicture(R.drawable.default_face)
                .imageView(holder.mAvadar)
                .setDragging(isDragging)
                .showImage(context);*/
    }

    @Override
    public int getItemCount() {
        return mContactList.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==getItemCount()-1){
            return I2.FOOTER_TEXT;
        }
        return I2.CONTACT_ITEM;
    }

    public void initContact(ArrayList<UserBean> partlist) {
        mContactList.clear();
        mContactList.addAll(partlist);
        notifyDataSetChanged();
    }

    public void addContact(ArrayList<UserBean> partlist) {
        mContactList.addAll(partlist);
        notifyDataSetChanged();
    }
}
