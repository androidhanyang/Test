package com.hy.myfuck;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hy.myfuck.bean.WanLe;
import com.hy.myfuck.bean.WanLeShang;
import com.hy.myfuck.json.NetJson;
import com.hy.myfuck.netutils.NetPagerUtils;
import com.hy.myfuck.view.WanLeActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/7/25.
 */
public class FragmentD extends BaseFragment {

    ListView lv;
    List<WanLe> list = new ArrayList<>();
    MyWanLeAdapter adapter;
    private RecyclerView rl;
    List<WanLeShang> mDatas;
    MyWanHengAdapter mAdapter;

    @Override
    public View init() {
        View view = View.inflate(getActivity(), R.layout.frag_d, null);
        lv = (ListView) view.findViewById(R.id.frag_d_lv);
        View view1 = View.inflate(getActivity(), R.layout.wanle_heng, null);
        rl = (RecyclerView) view1.findViewById(R.id.wanle_heng_rv);
        mDatas = new ArrayList<>();
        rl.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

//        rl.setLayoutManager(new StaggeredGridLayoutManager
//                (1, StaggeredGridLayoutManager.HORIZONTAL));

        adapter = new MyWanLeAdapter(list, getActivity());
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), WanLeActivity.class);
                String url = list.get(i - 1).weburl;
                intent.putExtra("keykey", url);
                startActivity(intent);
            }
        });

        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rl.setLayoutParams(lp);
        mAdapter = new MyWanHengAdapter(mDatas, getActivity());
        mAdapter.setOnItemClickListener(new MyWanHengAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), WanLeHengActivity.class);
                    String url = mDatas.get(position).url;
                    intent.putExtra("keykeykey", url);
                    Log.i("TGA", "onItemClick 44444: " + url);
                    startActivity(intent);
            }
        });
        rl.setAdapter(mAdapter);
        lv.addHeaderView(rl);
        lv.setAdapter(adapter);
        return view;
    }

    @Override
    public void setData() {
        onWanLeShang();
        onListWanLe();
        onWanLeHeng();
    }

    private void onWanLeHeng() {
        RequestParams params = new RequestParams(NetPagerUtils.NET_WANLE_XIA);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                mDatas = NetJson.wanLeShangNews1(result);
                Log.i("TAG", "onSuccess ((((((((((((((: "+mDatas);
                mAdapter.setNotifyList(mDatas);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void onWanLeShang() {
        RequestParams params = new RequestParams(NetPagerUtils.NET_WANLE_XIA);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                mDatas = NetJson.wanLeShangNews(result);
                Log.i("TAG", "onSuccess:mDatas " + mDatas);
                Log.i("TAG", "onSuccess: result" + result);
                mAdapter.setNotifyList(mDatas);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(), "44444", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }

    private void onListWanLe() {


        RequestParams params = new RequestParams(NetPagerUtils.NET_WANLE_XIA);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                list = NetJson.wanLeNews(result);
                Log.i("TAG", "onSuccess:list " + list);
                Log.i("TAG", "onSuccess: result" + result);
                adapter.setNotifyList(list);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                ex.printStackTrace();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
            }
        });
    }
}
