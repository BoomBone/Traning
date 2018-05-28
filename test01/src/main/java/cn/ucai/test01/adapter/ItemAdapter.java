package cn.ucai.test01.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.ucai.test01.R;
import cn.ucai.test01.bean.Item;

/**
 * Created by Administrator on 2017/8/3.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    ArrayList<Item> mItemList;
    Context context;
    View.OnClickListener listener;

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public ItemAdapter(ArrayList<Item> mItemList, Context context) {
        this.mItemList = mItemList;
        this.context = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_holder, null);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        bind(holder, position);
    }

    private void bind(final ItemViewHolder holder, final int position) {
        Item item = mItemList.get(position);
        if (item != null) {
            holder.mNum.setText(mItemList.get(position).getNum());
            if(mItemList.get(position).isSelect()){
                    holder.mNum.setTextColor(Color.RED);
                }else{
                    holder.mNum.setTextColor(Color.BLACK);
                }
        }
        holder.itemView.setOnClickListener(listener);
        holder.itemView.setTag(position);
    }


    @Override
    public int getItemCount() {
        return mItemList.size();
    }


}
