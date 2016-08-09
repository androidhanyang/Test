package com.hy.myfuck;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.hy.myfuck.bean.BeiJing;
import com.hy.myfuck.bean.News;
import com.hy.myfuck.json.NetJson;
import com.hy.myfuck.netutils.NetPagerUtils;
import com.hy.myfuck.view.FragmentCPager;
import com.hy.myfuck.view.PullToRefreshView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/7/25.
 */
public class FragmentC extends BaseFragment {
    // 直接请求常量
    public static final int REQUEST = 0;
    // 下拉刷新请求常量
    public static final int REFRESH = 1;
    // 上拉加载请求常量
    public static final int LOAD = 2;
    int page = 0;
    ListView lv;
    List<BeiJing> mList = new ArrayList<>();
    MyListAdapter listAdapter;
    PullToRefreshView pull;

    ConvenientBanner convenientBanner;//轮播图
    List<News.DataNews> qlist;//获取网络的图片和文字的集合

    List<Integer> listLocation = new ArrayList<>();//没有网络时加载本地的图片
    private ImageView iv;

    @Override
    public View init() {

        View view1 = View.inflate(getActivity(), R.layout.list_view_frag_c, null);
        View view = View.inflate(getActivity(), R.layout.view_pager_frag_c, null);
        pull = (PullToRefreshView) view1.findViewById(R.id.fragc_pull);
        iv = (ImageView) view1.findViewById(R.id.list_view_frag_c_iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BeiJingSearch.class);
                startActivity(intent);
            }
        });
        convenientBanner = (ConvenientBanner) view.findViewById(R.id.fragc_conb);
        lv = (ListView) view1.findViewById(R.id.list_view_frag_c_lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Intent intent = new Intent(getActivity(), ContentActivity.class);
                String url = mList.get(i - 1).weburl;
                intent.putExtra("key", url);
                startActivity(intent);

            }
        });
        listAdapter = new MyListAdapter(mList, getActivity());

//        引入轮播图的布局
//        convenientBanner = (ConvenientBanner) LayoutInflater.from(getActivity())
//                .inflate(R.layout.view_pager_frag_c, null);
        convenientBanner.setLayoutParams
                (new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 400));
//        boolean is = lv == null;
//        Log.i("TAG", "init: "+ is);
        lv.addHeaderView(convenientBanner);
        lv.setAdapter(listAdapter);
        return view1;
    }

    @Override
    public void setData() {
        setPullListener();
        onListNet(0);
        onPagerNet();


    }


    private void setPullListener() {
        pull.setOnHeaderRefreshListener(new PullToRefreshView.OnHeaderRefreshListener() {
            @Override
            public void onHeaderRefresh(PullToRefreshView view) {
                onListNet(REFRESH);
            }
        });
        pull.setOnFooterLoadListener(new PullToRefreshView.OnFooterLoadListener() {
            @Override
            public void onFooterLoad(PullToRefreshView view) {
                onListNet(LOAD);
            }
        });
    }


    private void onListNet(final int type) {
        switch (type) {
            case REQUEST:
                page = 1;
                break;
            case REFRESH:
                page = 1;
                break;
            case LOAD:
                page++;
                break;

            default:
                break;
        }
        RequestParams params = new RequestParams(NetPagerUtils.NET_BEIJING);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                mList = NetJson.beiJingNews(result);

                if (type == LOAD) {
                    mList.addAll(mList);
                    pull.onFooterLoadFinish();
                } else {
                    mList = NetJson.beiJingNews(result);
                    pull.onHeaderRefreshFinish();
                }
                listAdapter.setNotifyList(mList);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(x.app(), "请检查您的网络", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    //网络请求解析
    private void onPagerNet() {
        RequestParams params = new RequestParams(NetPagerUtils.NET_BEIJING);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                News news = gson.fromJson(result, News.class);
                qlist = news.data.gallery;//解析出来的list集合赋值给当前的list集合

                Log.i("TAG", "onSuccess: " + result);
                Log.i("TAG", "onSuccess: " + qlist);
                //把解析的数据加载到vager的布局中
                convenientBanner.setPages(new CBViewHolderCreator<NetPager>() {
                    @Override
                    public NetPager createHolder() {
                        return new NetPager();
                    }
                }, qlist).setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(getActivity(), FragmentCPager.class);
                        String url = qlist.get(position).article.weburl;
                        intent.putExtra("keykeykeykey", url);
                        startActivity(intent);
                    }
                })
                        //设置小圆点
                        .setPageIndicator(new int[]{R.drawable.ic_page_bac, R.drawable.ic_page_focused})
                        //设置小圆点居右
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(x.app(), "请检查网络", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    //开始自动翻页
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
