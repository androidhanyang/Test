package com.hy.myfuck;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hy.myfuck.bean.HotBean;
import com.hy.myfuck.bean.SheQu;

import java.util.List;

/**
 * Created by user on 2016/8/3.
 */
public class MySheQuAdapter extends BaseAdapter {
    List<SheQu> list;
    Context context;

    public MySheQuAdapter(List<SheQu> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.fragment_e_item, null);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.fragment_e_item_tv1);
            holder.tvContent = (TextView) convertView.findViewById(R.id.fragment_e_item_tv2);
            holder.iv = (ImageView) convertView.findViewById(R.id.fragment_e_item_iv);


            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvTitle.setText(list.get(position).title);
        holder.tvContent.setText(list.get(position).stitle);
        Glide.with(context).load(list.get(position).pic).into(holder.iv);
        return convertView;
    }

    public class ViewHolder {
        TextView tvTitle;
        TextView tvContent;
        ImageView iv;

    }


    public void setList(List<SheQu> list) {
        this.list = list;
        notifyDataSetChanged();
    }


}
