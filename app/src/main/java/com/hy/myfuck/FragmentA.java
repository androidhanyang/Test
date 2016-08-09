package com.hy.myfuck;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.hy.myfuck.bean.One;
import com.hy.myfuck.dao.SQLiteTable;
import com.hy.myfuck.dao.XUtil;
import com.hy.myfuck.netutils.NetPagerUtils;
import com.hy.myfuck.view.MyAdapterOne;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by user on 2016/7/25.
 */
public class FragmentA extends BaseFragment {


    private RecyclerView rl;
    List<SQLiteTable> mDatas = new ArrayList<>();
    MyAdapterOne adapter;
    DbManager dbManager;
    private ConvenientBanner convenientBanner;
    List<One.DataOne> pList;

    @Override
    public View init() {
        View view = View.inflate(getActivity(), R.layout.frag_a, null);
        View view1 = View.inflate(getActivity(), R.layout.fragmenta_re, null);
        DbManager.DaoConfig daoConfig = XUtil.getDaoConfig();
        dbManager = x.getDb(daoConfig);
        query();
        Log.i("TAG", "query(): "+mDatas.size());
        rl = (RecyclerView) view1.findViewById(R.id.id_recyclerView);
        adapter = new MyAdapterOne(mDatas, getActivity(),dbManager);
        adapter.setOnItemClickListener(new MyAdapterOne.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "1231231", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ReadActivity.class);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                showDialog(position);
            }
        });

        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);

        convenientBanner = (ConvenientBanner) view.findViewById(R.id.fraga_conb);
//                convenientBanner = (ConvenientBanner) LayoutInflater.from(getActivity())
//                .inflate(R.layout.frag_a, null);
        convenientBanner.setLayoutParams
                (new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 400));
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (adapter.getItemViewType(position) == MyAdapterOne.TYPE_HEADER) {
                    return layoutManager.getSpanCount();
                }
                return 1;
            }
        });
        layoutManager.setSpanCount(layoutManager.getSpanCount());
        rl.setLayoutManager(layoutManager);
        adapter.addHeader(view);
        rl.setAdapter(adapter);
        return view1;
    }

    @Override
    public void setData() {

        onHeaderNet();
        onPagerNet();

    }

    private void onHeaderNet() {
        OkHttpUtils.get().url(NetPagerUtils.NET_PAGER).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {

            }

            @Override
            public void onResponse(String s, int i) {
                Gson gson = new Gson();
                One one = gson.fromJson(s, One.class);
                pList = one.data.list;
                onPagerNet();
            }
        });


    }

    //查找数据库  得到全部数据
    public void query() {
        try {
            mDatas = dbManager.findAll(SQLiteTable.class);


        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    private void onPagerNet() {

                convenientBanner.setPages(new CBViewHolderCreator<NetOnePager>() {
                    @Override
                    public NetOnePager createHolder() {
                        return new NetOnePager();
                    }
                }, pList).setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                    }
                }).setPageIndicator(new int[]{R.drawable.ic_page_bac, R.drawable.ic_page_focused})
                        //设置小圆点居右
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
                adapter.setData(mDatas);
            }





    //展示一个对话框，显示删除
    private void showDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("温馨提示");
        builder.setIcon(R.drawable.ding);
        builder.setMessage("您要删除" + mDatas.get(position) + "吗");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (position < 5) {
                    Toast.makeText(getActivity(), "nonono", Toast.LENGTH_SHORT).show();
                } else {
//                    adapter.deleteData(mDatas.get(position), position);
                    Toast.makeText(getActivity(), "就这么删了", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "不删了", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        convenientBanner.startTurning(5000);
    }

    //停止翻页
    @Override
    public void onStop() {
        super.onStop();
        convenientBanner.stopTurning();
    }
}
