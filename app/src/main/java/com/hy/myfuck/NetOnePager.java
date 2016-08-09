package com.hy.myfuck;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.hy.myfuck.bean.News;
import com.hy.myfuck.bean.One;

/**
 * Created by user on 2016/8/1.
 */
public class NetOnePager implements Holder<One.DataOne> {
    private ImageView netIv;
    private TextView netTv;


    //图片加载到的pager布局
    @Override
    public View createView(Context context) {
        View view = View.inflate(context, R.layout.netpager, null);
        netIv = (ImageView) view.findViewById(R.id.header_item_iv);
        netTv = (TextView) view.findViewById(R.id.header_item_tv);
        netIv.setScaleType(ImageView.ScaleType.FIT_XY);
        return view;
    }




    @Override
    public void UpdateUI(Context context, int position, One.DataOne data) {
        netIv.setImageResource(R.drawable.ic_default_adimage0);
        netTv.setText(data.title);
        Glide.with(context).load(data.promotion_img).into(netIv);
    }
}

