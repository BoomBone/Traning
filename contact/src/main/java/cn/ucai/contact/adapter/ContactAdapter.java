package cn.ucai.contact.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.ucai.contact.I;
import cn.ucai.contact.R;
import cn.ucai.contact.bean.UserBean;
import cn.ucai.contact.utils.OkImageLoader;

/**
 * Created by Administrator on 2017/4/12.
 */

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //必有的
    Context context;
    ArrayList<UserBean> list;
    //列表和页脚
    final int TYPE_ITEM=0;
    final int TYPE_FOOTER=1;
    //页脚文字
    String FootText;

    public boolean isMore = true;

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }



    public String getFootText() {
        return FootText;
    }

    public void setFootText(String footText) {
        FootText = footText;
        notifyDataSetChanged();
    }

    public ContactAdapter(Context context, ArrayList<UserBean> list) {
        this.context = context;
        this.list = list;
    }
    //初始化第一页，一上来就有数据
    public void initContact(ArrayList<UserBean> contactList) {
        this.list.clear();
        this.list.addAll(contactList);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建缓存，inflate解析,返回缓存,缓存context，对应布局
        switch(viewType){
            case TYPE_ITEM:
            return new ContactHolder(View.inflate(context,R.layout.activity_contact,null));
            case TYPE_FOOTER:
                return new FooterHolder(View.inflate(context, R.layout.activityfooter, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder parentholder, int position) {
        if(getItemViewType(position)==TYPE_FOOTER){
            FooterHolder holder= (FooterHolder) parentholder;
            holder.mtv.setText(FootText);
            holder.mtv.setVisibility(View.VISIBLE);
            return;
        }
        //取出当前的联系人
        UserBean user = list.get(position);
        ContactHolder holder = (ContactHolder) parentholder;
        holder.mtvName.setText(user.getUserName());
        holder.mtvName.setText(user.getNick());
        OkImageLoader.build(I.REQUEST_DOWNLOAD_AVATAR_URL + user.getUserName())
                .defaultPicture(R.drawable.default_face)
                .imageView(holder.mim)
                .showImage(context);
    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        //到了最下面就返回TYPE_FOOTER值
        if(position==getItemCount()-1){
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    public void addContactList(ArrayList<UserBean> contactList) {
        list.addAll(contactList);
        notifyDataSetChanged();
    }
}
