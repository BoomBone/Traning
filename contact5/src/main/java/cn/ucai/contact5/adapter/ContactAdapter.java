package cn.ucai.contact5.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.ucai.contact5.I;
import cn.ucai.contact5.R;
import cn.ucai.contact5.bean.UserBean;
import cn.ucai.contact5.I2;
import cn.ucai.contact5.utils.OkImageLoader;

/**
 * Created by Administrator on 2017/4/22.
 */

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Context context;
    ArrayList<UserBean> contactlist;

    String FooterText;
    boolean isMore = true;

    boolean Dragging;

    public void setDragging(boolean dragging) {
        Dragging = dragging;
        notifyDataSetChanged();
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }

    public String getFooterText() {
        return FooterText;
    }

    public void setFooterText(String footerText) {
        FooterText = footerText;
        notifyDataSetChanged();
    }

    public ContactAdapter(Context context, ArrayList<UserBean> contactlist) {
        this.context = context;
        this.contactlist = contactlist;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch(viewType){
            case I2.CONTACT_ITEM:
                return new ContactHolder(View.inflate(context, R.layout.contact_list, null));
            case I2.FOOTER_TEXT:
                return new FooterHolder(View.inflate(context, R.layout.footer_activity, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder parentholder, int position) {
        if(getItemViewType(position)==I2.FOOTER_TEXT){
            FooterHolder holder= (FooterHolder) parentholder;
            holder.mFooterText.setText(FooterText);
            holder.mFooterText.setVisibility(View.VISIBLE);
            return;
        }
        UserBean user = contactlist.get(position);
        ContactHolder holder = (ContactHolder) parentholder;
        holder.mName.setText(user.getUserName());
        holder.mNick.setText(user.getNick());
        OkImageLoader.build(I.REQUEST_DOWNLOAD_AVATAR_URL+user.getUserName())
                .defaultPicture(R.drawable.default_face)
                .imageView(holder.mAvadar)
                .setDragging(Dragging)
                .showImage(context);
    }

    @Override
    public int getItemCount() {
        return contactlist.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==getItemCount()-1){
            return I2.FOOTER_TEXT;
        }
        return I2.CONTACT_ITEM;
    }

    public void initContact(ArrayList<UserBean> list) {
        contactlist.clear();
        contactlist.addAll(list);
        notifyDataSetChanged();
    }

    public void addContact(ArrayList<UserBean> list) {
        contactlist.addAll(list);
        notifyDataSetChanged();
    }
}
