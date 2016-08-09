package com.hy.myfuck;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.hy.myfuck.bean.HotBean;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 2016/7/23.
 */
public class MyAdapter extends BaseAdapter {

    private static final String THREE_B = "3_b";
    private static final String ONE = "1";
    private static final String ONE_B = "1_b";
    private static final String THREE = "3";


    List<HotBean> mlist;
    Context context;


    public MyAdapter(List<HotBean> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
    }


    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int i) {
        return mlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (THREE_B.equals(mlist.get(i).item_type)) {
            view = View.inflate(context, R.layout.activity_main_item, null);
            RelativeLayout rl = (RelativeLayout) view.findViewById(R.id.three_three);
            rl.setVisibility(View.VISIBLE);
            TextView tvTitle = (TextView) view.findViewById(R.id.three_three_tv);
            TextView tvNews = (TextView) view.findViewById(R.id.three_three_tv1);
            ImageView iv1 = (ImageView) view.findViewById(R.id.three_three_iv1);
            ImageView iv2 = (ImageView) view.findViewById(R.id.three_three_iv2);
            ImageView iv3 = (ImageView) view.findViewById(R.id.three_three_iv3);
            tvTitle.setText(mlist.get(i).title);
            tvNews.setText(mlist.get(i).auther_name);
            Glide.with(context).load(mlist.get(i).thumbnail_medias.get(0).url).into(iv1);
            Glide.with(context).load(mlist.get(i).thumbnail_medias.get(1).url).into(iv2);
            Glide.with(context).load(mlist.get(i).thumbnail_medias.get(2).url).into(iv3);
        } else if(THREE.equals(mlist.get(i).item_type)){
            view = View.inflate(context, R.layout.activity_main_item, null);
            RelativeLayout rl = (RelativeLayout) view.findViewById(R.id.three_three);
            rl.setVisibility(View.VISIBLE);
            TextView tvTitle = (TextView) view.findViewById(R.id.three_three_tv);
            TextView tvNews = (TextView) view.findViewById(R.id.three_three_tv1);
            ImageView iv1 = (ImageView) view.findViewById(R.id.three_three_iv1);
            ImageView iv2 = (ImageView) view.findViewById(R.id.three_three_iv2);
            ImageView iv3 = (ImageView) view.findViewById(R.id.three_three_iv3);
            tvTitle.setText(mlist.get(i).title);
            tvNews.setText(mlist.get(i).auther_name);
            Glide.with(context).load(mlist.get(i).thumbnail_medias.get(0).url).into(iv1);
            Glide.with(context).load(mlist.get(i).thumbnail_medias.get(1).url).into(iv2);
            Glide.with(context).load(mlist.get(i).thumbnail_medias.get(2).url).into(iv3);



        }





        else if (ONE.equals(mlist.get(i).item_type)) {
            view = View.inflate(context, R.layout.activity_main_item, null);
            RelativeLayout rl2 = (RelativeLayout) view.findViewById(R.id.zuoyou);
            rl2.setVisibility(View.VISIBLE);
            TextView tvTitle2 = (TextView) view.findViewById(R.id.zuoyou_tv);
            ImageView iv4 = (ImageView) view.findViewById(R.id.zuoyou_iv);
            tvTitle2.setText(mlist.get(i).title);
            Glide.with(context).load(mlist.get(i).thumbnail_medias.get(0).url).into(iv4);
        } else if (ONE_B.equals(mlist.get(i).item_type)) {
            view = View.inflate(context, R.layout.activity_main_item, null);
            RelativeLayout rl3 = (RelativeLayout) view.findViewById(R.id.one_one);
            rl3.setVisibility(View.VISIBLE);
            TextView tvTitle3 = (TextView) view.findViewById(R.id.one_one_tv);
            ImageView iv5 = (ImageView) view.findViewById(R.id.one_one_iv);

            tvTitle3.setText(mlist.get(i).title);
            Glide.with(context).load(mlist.get(i).thumbnail_medias.get(0).url).into(iv5);
        } else {
            view = View.inflate(context, R.layout.activity_main_item, null);
            RelativeLayout rl4 = (RelativeLayout) view.findViewById(R.id.wuwu);
            rl4.setVisibility(View.VISIBLE);
            TextView tvTitle4 = (TextView) view.findViewById(R.id.wuwu_tv1);
            TextView tvTitle5 = (TextView) view.findViewById(R.id.wuwu_tv2);
            tvTitle4.setText(mlist.get(i).title);
            tvTitle5.setText(mlist.get(i).auther_name);
        }
        return view;
    }

    public void setList(List<HotBean> mlist) {
        this.mlist = mlist;
        notifyDataSetChanged();
    }


}
