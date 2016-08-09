package com.hy.myfuck;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hy.myfuck.bean.HotBean;
import com.hy.myfuck.json.NetJson;
import com.hy.myfuck.netutils.NetPagerUtils;
import com.hy.myfuck.view.PullToRefreshView;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by user on 2016/7/25.
 */
public class FragmentB extends BaseFragment {
    //    // 直接请求常量
//    public static final int REQUEST = 0;
//    // 下拉刷新请求常量
//    public static final int REFRESH = 1;
//    // 上拉加载请求常量
//    public static final int LOAD = 2;
//    int page = 0;
//    PullToRefreshView pull;
    ListView mListView;
    List<HotBean> mlist;
    MyAdapter myAdapter;
    private ImageView man;
SpringView springView;
    //定义一个变量，来表示是否退出
    private static boolean isExit = false;
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    public View init() {
        View view = View.inflate(getActivity(), R.layout.fragment_b, null);
        springView = (SpringView) view.findViewById(R.id.hot_sv);
        man = (ImageView) view.findViewById(R.id.fragment_b_iv);
        man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SettingMine.class);
                startActivity(intent);
            }
        });
//        pull = (PullToRefreshView) view.findViewById(R.id.fragment_b_pull);
        mListView = (ListView) view.findViewById(R.id.fragment_b_lv);
        mlist = new ArrayList<>();

//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//        });
        return view;
    }


    public void setData() {
//        setPullListener();
//        getNetData(0);
        onNetAdd();

        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                onNetAdd();
            }

            @Override
            public void onLoadmore() {
                onHotNet();
            }
        });
        springView.setHeader(new DefaultHeader(getActivity()));
        springView.setFooter(new DefaultFooter(getActivity()));
    }



    private void onNetAdd() {



        OkHttpUtils.get().url(NetPagerUtils.NET_HOT).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {

            }

            @Override
            public void onResponse(String s, int i) {
                mlist = NetJson.hotBeen(s);
                myAdapter = new MyAdapter(mlist, getActivity());
                mListView.setAdapter(myAdapter);
                Log.i("TGA", "onResponse: "+mlist);
                myAdapter.setList(mlist);
            }
        });
        springView.onFinishFreshAndLoad();
    }
    private void onHotNet() {

        OkHttpUtils.get().url(NetPagerUtils.NET_HOT).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {

            }

            @Override
            public void onResponse(String s, int i) {
                List<HotBean> listMore = NetJson.hotBeen(s);
                mlist.addAll(listMore);
                myAdapter.setList(mlist);
            }

        });
        springView.onFinishFreshAndLoad();
    }
//    private void setPullListener() {
//        pull.setOnHeaderRefreshListener(new PullToRefreshView.OnHeaderRefreshListener() {
//            @Override
//            public void onHeaderRefresh(PullToRefreshView view) {
//                getNetData(REFRESH);
//            }
//        });
//        pull.setOnFooterLoadListener(new PullToRefreshView.OnFooterLoadListener() {
//            @Override
//            public void onFooterLoad(PullToRefreshView view) {
//                getNetData(LOAD);
//            }
//        });
//    }

//    private void getNetData(final int type) {
//        switch (type) {
//            case REQUEST:
//                page = 1;
//                break;
//            case REFRESH:
//                page = 1;
//                break;
//            case LOAD:
//                page++;
//                break;
//
//            default:
//                break;
//        }
//        if (type == LOAD) {
//            mlist.addAll(mlist);
//            pull.onFooterLoadFinish();
//        } else {
//            mlist = mlist;
//            pull.onHeaderRefreshFinish();
//        }
//        myAdapter.setList(mlist);
//
//    }
}