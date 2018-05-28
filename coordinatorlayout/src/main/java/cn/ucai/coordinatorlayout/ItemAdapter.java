package cn.ucai.coordinatorlayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/7.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<Item> list;

    public ItemAdapter(Context context,List<Item> list) {
        mLayoutInflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewHolder holder=new ItemViewHolder(mLayoutInflater.inflate(R.layout.item_view_holder, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.mTv.setText(String.valueOf(list.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
