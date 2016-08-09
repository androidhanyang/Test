package com.hy.myfuck;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hy.myfuck.bean.BeiJing;

import java.util.List;

/**
 * Created by user on 2016/7/26.
 */
public class MyListAdapter extends BaseAdapter {

    List<BeiJing> mList;
    Context context;
    private ImageView netIv;
    private TextView netTv;
    private TextView netTv2;

    public MyListAdapter(List<BeiJing> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = View.inflate(context, R.layout.list_item, null);
        netIv = (ImageView) view.findViewById(R.id.list_item_iv);
        netTv = (TextView) view.findViewById(R.id.list_item_tv);
        netTv2 = (TextView) view.findViewById(R.id.list_item_tv2);
        netTv.setText(mList.get(i).title);
        netTv2.setText(mList.get(i).auther_name);
        Glide.with(context).load(mList.get(i).thumbnail_mpic).into(netIv);
        if (mList.get(i).thumbnail_mpic == null){
            netIv.setVisibility(View.GONE);
        }
        return view;
    }


    public void setNotifyList(List<BeiJing> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }
}
