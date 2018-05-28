package cn.ucai.newcontact.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.ucai.newcontact.I;
import cn.ucai.newcontact.I2;
import cn.ucai.newcontact.R;
import cn.ucai.newcontact.bean.UserBean;
import cn.ucai.newcontact.utils.OkImageLoader;

/**
 * Created by Administrator on 2017/4/21.
 */

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<UserBean> contactlist;

    String FooterText;

    boolean isMore=true;

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }

    public ContactAdapter(Context context, ArrayList<UserBean> contactlist) {
        this.context = context;
        this.contactlist = contactlist;
    }

    public String getFooterText() {
        return FooterText;
    }

    public void setFooterText(String footerText) {
        FooterText = footerText;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case I2.CONTACT_ITEM:
                return new ContactHolder(View.inflate(context, R.layout.contact_activity, null));
            case I2.FOOTER_TEXT:
                return new FooterHolder(View.inflate(context, R.layout.footer_activity, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder parentholder, int position) {
        if (getItemViewType(position) == I2.FOOTER_TEXT) {
            FooterHolder holder= (FooterHolder) parentholder;
            holder.mtvFooter.setText(FooterText);
            holder.mtvFooter.setVisibility(View.VISIBLE);
            return;
        }
        UserBean user = contactlist.get(position);
        ContactHolder holder = (ContactHolder) parentholder;
        holder.mName.setText(user.getUserName());
        holder.mNick.setText(user.getNick());
        OkImageLoader.build(I.REQUEST_DOWNLOAD_AVATAR_URL+user.getUserName())
                .defaultPicture(R.drawable.default_face)
                .imageView(holder.mimAvadar)
                .showImage(context);
    }

    @Override
    public int getItemCount() {
        return contactlist.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return I2.FOOTER_TEXT;
        }
        return I2.CONTACT_ITEM;
    }

    public void initContact(ArrayList<UserBean> partlist) {
        contactlist.clear();
        contactlist.addAll(partlist);
        notifyDataSetChanged();
    }

    public void addContact(ArrayList<UserBean> partlist) {

        contactlist.addAll(partlist);
        notifyDataSetChanged();
    }
}
