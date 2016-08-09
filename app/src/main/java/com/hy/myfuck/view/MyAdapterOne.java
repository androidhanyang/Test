package com.hy.myfuck.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hy.myfuck.R;
import com.hy.myfuck.dao.SQLiteTable;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/8/1.
 */
public class MyAdapterOne extends RecyclerView.Adapter<MyAdapterOne.MyViewHolder> {
    private ImageView iv1;
    private LayoutInflater mInflater;
    private List<SQLiteTable> mDatas;
    private Context mContext;
    DbManager dbManager;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_ITEM = 1;
    private View mHeaderView;

    public MyAdapterOne(List<SQLiteTable> mDatas, Context context, DbManager dbManager) {
        this.mDatas = mDatas;
        this.mContext = context;
        this.dbManager = dbManager;
        mInflater = LayoutInflater.from(mContext);
    }

    //定义一个接口，实现方法回调
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener onItemClickLitener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickLitener = onItemClickListener;
    }

    //实例化头布局
    public class HeaderHolder extends MyViewHolder {
        public HeaderHolder(View itemView) {
            super(itemView);
        }
    }

    //头布局的位置判断
    private int getHeaderViewSize() {
        return mHeaderView == null ? 0 : 1;
    }
    //头布局和item布局的判断

    @Override
    public int getItemViewType(int position) {
        if (position < getHeaderViewSize()) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    //添加0位头布局
    public void addHeader(View header) {
        mHeaderView = header;
        notifyItemInserted(0);

    }

    //删除mDatas数据
    public void deleteData(int position) {
        mDatas.remove(position + 1);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        Log.i("TAG", "onBindViewHolder: " + mDatas.size());
        return mDatas.size();
    }

    //创建新的View布局
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = mInflater.inflate(R.layout.fragment_a_item, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        } else if (viewType == TYPE_HEADER) {
            View view = mHeaderView;
            return new HeaderHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (position == 0) return;
        holder.tv.setText(mDatas.get(position).getTitleNews());
        Glide.with(mContext).load(mDatas.get(position).getPic()).into(holder.iv);
        int mColor = Color.parseColor(mDatas.get(position).getBlock_color());
        holder.iv1.setBackgroundColor(mColor);
        Log.i("TAG", "onBindViewHolder: " + mDatas.get(position).getTitleNews() + ":" + mDatas.get(position).getTitle());

        if (onItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 从布局中获取条目位置
                    int pos = holder.getLayoutPosition();
                    onItemClickLitener.onItemClick(holder.itemView, pos - 1);
                    Log.i("TAG", "onLongClick: onClick");
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    onItemClickLitener.onItemLongClick(holder.itemView, pos - 1);
                    Log.i("TAG", "onLongClick: long");
                    return false;
                }
            });
        }
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView iv, iv1;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.fragment_a_item_tv);
            iv = (ImageView) itemView.findViewById(R.id.fragment_a_item_iv);
            iv1 = (ImageView) itemView.findViewById(R.id.iviviv);
        }
    }

    public void setData(List<SQLiteTable> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public void insert(int i) {

        try {
            SQLiteTable table = new SQLiteTable();
            table.setTitle(mDatas.get(i).getTitle());
            table.setTitleNews(mDatas.get(i).getTitleNews());
            table.setPic(mDatas.get(i).getPic());
            table.setApi_url(mDatas.get(i).getApi_url());
            table.setBlock_color(mDatas.get(i).getBlock_color());
            dbManager.save(table);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}


