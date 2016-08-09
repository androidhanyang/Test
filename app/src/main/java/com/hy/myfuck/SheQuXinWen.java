package com.hy.myfuck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hy.myfuck.bean.SheQuNeiBu;
import com.hy.myfuck.json.NetJson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class SheQuXinWen extends AppCompatActivity {
    private ListView lv;
    List<SheQuNeiBu> list = new ArrayList<>();
    MySheQuNeiBuAdapter adapter;
    String url;
    SpringView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_she_qu_xin_wen);

        init();
    }


    private void init() {
        Intent intent = getIntent();
        url = intent.getStringExtra("key123");
        onSheQuNBNet();
        sv = (SpringView) findViewById(R.id.shequxinwen_sv);
        sv.setType(SpringView.Type.FOLLOW);
        sv.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                onSheQuNBNet();
            }

            @Override
            public void onLoadmore() {
                onSheQuNBMoreNet();
            }
        });
        sv.setHeader(new DefaultHeader(this));
        sv.setFooter(new DefaultFooter(this));

        lv = (ListView) findViewById(R.id.shequxinwen_lv);
        adapter = new MySheQuNeiBuAdapter(list, this);
        lv.setAdapter(adapter);
    }


    private void onSheQuNBNet() {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {

            }

            @Override
            public void onResponse(String s, int i) {
                list = NetJson.sheQuNeiBus(s);
                Log.i("TGA", "onResponse:12312312312312312312313123 " + url);
                Log.i("TGA", "onResponse:1111111111111111111111111111111122 " + list);
                adapter.setList(list);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent  = new Intent(SheQuXinWen.this,ShequItemActivity.class);
                        String urll = list.get(position).weburl;
                        intent.putExtra("123",urll);
                        startActivity(intent);
                    }
                });
                sv.onFinishFreshAndLoad();
            }
        });
    }

    private void onSheQuNBMoreNet() {
        OkHttpUtils.get().url(list.get(list.size() - 1).next_url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {

            }

            @Override
            public void onResponse(String s, int i) {

                List<SheQuNeiBu> listp = NetJson.sheQuNeiBus(s);
                list.addAll(listp);
                adapter.setList(list);
                sv.onFinishFreshAndLoad();
            }
        });

    }
}
