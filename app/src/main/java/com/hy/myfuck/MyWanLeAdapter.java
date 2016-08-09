package com.hy.myfuck;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hy.myfuck.bean.WanLe;

import java.util.List;

/**
 * Created by user on 2016/7/29.
 */
public class MyWanLeAdapter extends BaseAdapter {
    List<WanLe> list;
    Context context;
    private TextView tv1;
    private TextView tv2;
    private ImageView iv;

    public MyWanLeAdapter(List<WanLe> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = View.inflate(context, R.layout.wanle_item, null);
        tv1 = (TextView) view.findViewById(R.id.wanle_item_tv1);
        tv2 = (TextView)view.findViewById(R.id.wanle_item_tv2);
        iv = (ImageView)view.findViewById(R.id.wanle_item_iv);
        tv1.setText(list.get(i).title);
        tv2.setText(list.get(i).share_content);
        Glide.with(context).load(list.get(i).url).into(iv);
        Log.i("TAG", "getView: "+list.get(i).url);
        return view;
    }
    public void setNotifyList(List<WanLe> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
