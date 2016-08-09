package com.hy.myfuck;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hy.myfuck.bean.SheQuNeiBu;

import java.util.List;

/**
 * Created by user on 2016/8/3.
 */
public class MySheQuNeiBuAdapter extends BaseAdapter {
    private static final String ONE = "1";
    private static final String TWO = "2";
    private static final String THREE = "3";
    List<SheQuNeiBu> list;
    Context context;

    public MySheQuNeiBuAdapter(List<SheQuNeiBu> list, Context context) {
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

        if(list.get(position).thumbnail_medias != null){
            convertView = View.inflate(context, R.layout.shequneibu_item1,null);
            ImageView ivIcon = (ImageView) convertView.findViewById(R.id.comm_icon_iv);
            ImageView ivImage = (ImageView) convertView.findViewById(R.id.comm_image_iv);
            TextView tvName = (TextView) convertView.findViewById(R.id.comm_name_tv);
            TextView tvTitle = (TextView) convertView.findViewById(R.id.comm_count_tv);
            TextView tvNum = (TextView) convertView.findViewById(R.id.comm_num_tv);
            if(list.get(position).thumbnail_medias.size() == 0){
                ivImage.setVisibility(View.GONE);
            }else{
                Glide.with(context).load(list.get(position).thumbnail_medias.get(0).url).into(ivImage);
            }
            Glide.with(context).load(list.get(position).icon).into(ivIcon);
            ivImage.setScaleType(ImageView.ScaleType.FIT_XY);
            ivIcon.setScaleType(ImageView.ScaleType.FIT_XY);
            tvName.setText(list.get(position).name);
            tvTitle.setText(list.get(position).content);
            tvNum.setText(list.get(position).hot_num+"");
        }else{
            convertView = View.inflate(context, R.layout.shequneibu_item2,null);
            ImageView ivIcon = (ImageView) convertView.findViewById(R.id.comm_icon_iv);
            TextView tvName = (TextView) convertView.findViewById(R.id.comm_name_tv);
            TextView tvTitle = (TextView) convertView.findViewById(R.id.comm_count_tv);
            TextView tvNum = (TextView) convertView.findViewById(R.id.comm_num_tv);
            Glide.with(context).load(list.get(position).icon).into(ivIcon);
            tvName.setText(list.get(position).name);
            tvTitle.setText(list.get(position).content);
            tvNum.setText(list.get(position).hot_num+"");
        }


            return convertView;
        }


    public void setList(List<SheQuNeiBu> list) {
        this.list = list;
        notifyDataSetChanged();
    }


}
