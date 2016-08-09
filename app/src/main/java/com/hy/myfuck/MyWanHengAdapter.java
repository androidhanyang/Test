package com.hy.myfuck;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hy.myfuck.bean.WanLe;
import com.hy.myfuck.bean.WanLeShang;

import java.util.List;

/**
 * Created by user on 2016/7/29.
 */
public class MyWanHengAdapter extends RecyclerView.Adapter<MyWanHengAdapter.MyViewHolder> {

    private LayoutInflater mInflater;
    private List<WanLeShang> mDatas;
    private Context mContext;


    public MyWanHengAdapter(List<WanLeShang> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }


    /**
     * 定义一个接口，实现方法的回掉
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
//        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener onItemClickLitener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickLitener = onItemClickListener;
    }

    //给MyViewHolder传递布局对象
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_textview, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    //把数据赋值给布局对象
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Glide.with(mContext).load(mDatas.get(position).m_url).into(holder.iv);

        if (onItemClickLitener != null) {
            holder.iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickLitener.onItemClick(view,position);
                }
            });
        }
    }

    //计算有多少条数据
    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    //MyViewHolder用来初始化布局中的控件对象
    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.id_iv1);
        }
    }

    public void setNotifyList(List<WanLeShang> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }
}
