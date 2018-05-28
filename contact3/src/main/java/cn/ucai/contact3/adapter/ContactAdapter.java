package cn.ucai.contact3.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.ucai.contact3.I;
import cn.ucai.contact3.I2;
import cn.ucai.contact3.R;
import cn.ucai.contact3.bean.UserBean;
import cn.ucai.contact3.utils.OkImageLoader;

/**
 * Created by Administrator on 2017/4/16.
 */

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<UserBean> mContactList;

    String Footertext;

    public String getFootertext() {
        return Footertext;
    }

    public void setFootertext(String footertext) {
        Footertext = footertext;
        notifyDataSetChanged();
    }

    boolean isMore=true;

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }

    public ContactAdapter(Context context, ArrayList<UserBean> mContactList) {
        this.context = context;
        this.mContactList = mContactList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case I2.ACTIOC_ITEM:
                return new ContactHolder(View.inflate(context, R.layout.activity_contact,null));
            case I2.ACTION_FOOTER:
                return new FooterHolder(View.inflate(context,R.layout.activity_footer,null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder parentholder, int position) {
        if(I2.ACTION_FOOTER==getItemViewType(position)){
            FooterHolder footerHolder = (FooterHolder) parentholder;
            footerHolder.mtvFooter.setText(Footertext);
            footerHolder.mtvFooter.setVisibility(View.VISIBLE);
            return;
        }
        UserBean user = mContactList.get(position);
        //创建适配器
        ContactHolder holder = (ContactHolder) parentholder;
        holder.mtvName.setText(user.getUserName());
        holder.mtvNick.setText(user.getNick());
        OkImageLoader.build(I.REQUEST_DOWNLOAD_AVATAR_URL+user.getUserName())
                .defaultPicture(R.drawable.default_face)
                .imageView(holder.mimContactPhoto)
                .showImage(context);
    }

    @Override
    public int getItemCount() {
        return mContactList.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==getItemCount()-1){
            return I2.ACTION_FOOTER;
        }
        return I2.ACTIOC_ITEM;
    }

    public void initContact(ArrayList<UserBean> list) {
        mContactList.clear();
        mContactList.addAll(list);
        notifyDataSetChanged();
    }

    public void addContact(ArrayList<UserBean> contactlist) {
        mContactList.addAll(contactlist);
        notifyDataSetChanged();
    }
}
