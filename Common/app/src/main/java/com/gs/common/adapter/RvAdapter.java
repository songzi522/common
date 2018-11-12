package com.gs.common.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gs.common.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/1/24.
 */

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {

    private ArrayList<String> mData;

    /**
     * 事件回调监听
     */
    private RvAdapter.OnItemClickListener onItemClickListener;

    public RvAdapter(ArrayList<String> data) {
        this.mData = data;
    }

    public void updateData(ArrayList<String> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public void addNewItem() {
        if(mData == null) {
            mData = new ArrayList<>();
        }
        mData.add(0, "new Item");
        notifyItemInserted(0);
    }

    public void deleteItem() {
        if(mData == null || mData.isEmpty()) {
            return;
        }
        mData.remove(0);
        notifyItemRemoved(0);
    }

    /**
     * 设置回调监听
     *
     * @param listener
     */
    public void setOnItemClickListener(RvAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rv_item, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // 绑定数据
        holder.mTv.setText(mData.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, pos);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(holder.itemView, pos);
                }
                //表示此事件已经消费，不会触发单击事件
                return true;
            }
        });

    }


    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTv;

        public ViewHolder(View itemView) {
            super(itemView);
            mTv = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }


}
