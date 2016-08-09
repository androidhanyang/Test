package com.hy.myfuck;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.hy.myfuck.bean.SheQu;
import com.hy.myfuck.json.NetJson;
import com.hy.myfuck.netutils.NetPagerUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by user on 2016/7/25.
 */
public class FragmentE extends BaseFragment {


    private ListView lv;
    List<SheQu> list = new ArrayList<>();
    MySheQuAdapter adapter;
SpringView sv;
    @Override
    public View init() {
        View view = View.inflate(getActivity(), R.layout.fragment_e, null);
        lv = (ListView) view.findViewById(R.id.fragment_e_lv);
        sv = (SpringView) view.findViewById(R.id.fragment_e_sv);
        sv.setType(SpringView.Type.FOLLOW);
        sv.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                onSheQuNet();
            }

            @Override
            public void onLoadmore() {
                onSheQuMoreNet();
            }
        });
        sv.setHeader(new DefaultHeader(getActivity()));
        sv.setFooter(new DefaultFooter(getActivity()));
        adapter = new MySheQuAdapter(list, getActivity());
        lv.setAdapter(adapter);

        return view;
    }

    @Override
    public void setData() {
        onSheQuNet();
        onSheQuMoreNet();
    }

    private void onSheQuNet() {
        OkHttpUtils.get().url(NetPagerUtils.NET_SHEQU).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {

            }

            @Override
            public void onResponse(String s, int i) {
                list = NetJson.sheQuNews(s);
                adapter.setList(list);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(getActivity(),SheQuXinWen.class);
                        String url = list.get(position).api_url;
                        intent.putExtra("key123", url);
                        startActivity(intent);
                    }


                });
                sv.onFinishFreshAndLoad();
            }
        });
    }

    private void onSheQuMoreNet() {
        OkHttpUtils.get().url(NetPagerUtils.NET_SHEQU_MORE).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {

            }

            @Override
            public void onResponse(String s, int i) {
                List<SheQu> listMore = NetJson.sheQuNews(s);

                list.addAll(listMore);
                adapter.setList(list);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(getActivity(),SheQuXinWen.class);
                        String url = list.get(position).api_url;
                        intent.putExtra("key123", url);
                        startActivity(intent);
                    }


                });
                sv.onFinishFreshAndLoad();
            }
        });

    }


}
