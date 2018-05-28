package cn.ucai.contact2.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.ucai.contact2.I;
import cn.ucai.contact2.I2;
import cn.ucai.contact2.R;
import cn.ucai.contact2.bean.UserBean;
import cn.ucai.contact2.utils.OkImageLoader;

/**
 * Created by Administrator on 2017/4/15.
 */

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<UserBean> contactList;

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
        notifyDataSetChanged();
    }

    String footer;

    boolean isMore=true;
    //是否拖拽
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



    public ContactAdapter(Context context, ArrayList<UserBean> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch(viewType){
            case I2.ACTION_ITEM:
                return new ContactHolder(View.inflate(context, R.layout.activity_contact, null));
            case I2.ACTION_FOOTER:
                return new FooterHolder(View.inflate(context, R.layout.activity_footer, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder parentholder, int position) {
        if(I2.ACTION_FOOTER==getItemViewType(position)){
            FooterHolder holder = (FooterHolder) parentholder;
            holder.mtvFooter.setText(footer);
            holder.mtvFooter.setVisibility(View.VISIBLE);
            return;
        }
        //取出联系人
        UserBean user = contactList.get(position);
        ContactHolder holder= (ContactHolder) parentholder;
        holder.mtvName.setText(user.getUserName());
        holder.mtvNick.setText(user.getNick());
        OkImageLoader.build(I.REQUEST_DOWNLOAD_AVATAR_URL+user.getUserName())
                .defaultPicture(R.drawable.default_face)
                .imageView(holder.mivAvadar)
                .setDragging(Dragging)
                .showImage(context);
    }

    @Override
    public int getItemCount() {
        return contactList.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==getItemCount()-1){
            return  I2.ACTION_FOOTER;
        }
        return I2.ACTION_ITEM;
    }

    public void initContact(ArrayList<UserBean> contactlist) {
        contactList.clear();
        contactList.addAll(contactlist);
        notifyDataSetChanged();
    }

    public void addContact(ArrayList<UserBean> contactlist) {
        contactList.addAll(contactlist);
        notifyDataSetChanged();
    }
}
