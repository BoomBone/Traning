package cn.ucai.contact7.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.ucai.contact7.I;
import cn.ucai.contact7.I2;
import cn.ucai.contact7.R;
import cn.ucai.contact7.bean.UserBean;
import cn.ucai.contact7.utils.OkImageLoader;

/**
 * Created by Administrator on 2017/4/26.
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
                return new ContactHolder(View.inflate(context, R.layout.activity_contact, null));
            case I2.FOOTER_TEXT:
                return new FooterHolder(View.inflate(context, R.layout.activity_footer, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder parentholder, int position) {
        if(getItemViewType(position)==I2.FOOTER_TEXT){
            FooterHolder holder= (FooterHolder) parentholder;
            holder.mtvFooterText.setText(FooterText);
            return;
        }
        UserBean user = contactlist.get(position);
        ContactHolder holder = (ContactHolder) parentholder;
        holder.mName.setText(user.getUserName());
        holder.mNick.setText(user.getNick());
        OkImageLoader.build(I.REQUEST_DOWNLOAD_AVATAR_URL+user.getUserName())
                .defaultPicture(R.drawable.default_face)
                .setDragging(Dragging)
                .imageView(holder.mivAvandar)
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
