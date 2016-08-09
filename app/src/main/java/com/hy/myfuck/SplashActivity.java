package com.hy.myfuck;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.hy.myfuck.bean.NewsTab;
import com.hy.myfuck.dao.SQLiteTable;
import com.hy.myfuck.dao.XUtil;
import com.hy.myfuck.json.NetJson;
import com.hy.myfuck.netutils.NetPagerUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.List;

import okhttp3.Call;

public class SplashActivity extends AppCompatActivity {
    List<NewsTab> list;
    DbManager dbManager;
    SharedPreferences sharedPreferences;
    private ImageView splash_iv;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPreferences = getPreferences(MODE_PRIVATE);
        DbManager.DaoConfig daoConfig = XUtil.getDaoConfig();
        dbManager = x.getDb(daoConfig);
        //判断是否是第一次加载
        if (sharedPreferences.getBoolean("isFirst",true)){
            onSplashNet();
        }else{
            mHandler.sendEmptyMessageDelayed(0,2000);
        }
        init();
        run();

    }

    private void init() {
        splash_iv = (ImageView) findViewById(R.id.splash_iv);
    }

    public void run() {
        mHandler.sendEmptyMessageDelayed(0, 1500);
    }

    private void onSplashNet() {
        OkHttpUtils.get().url(NetPagerUtils.NET_CHANNEL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {

            }

            @Override
            public void onResponse(String s, int i) {
                list = NetJson.newsTabs(s);
                Log.i("TAG", "onResponse: 1231231231231141414141414"+list);
                Log.i("TAG","asdasdasdasdad"+s);
                insert();
                mHandler.sendEmptyMessageDelayed(0, 1500);
            }
        });
    }

    public void insert() {
        try {
            SQLiteTable sqLiteTable = new SQLiteTable();
            for (int i = 0; i < list.size() - (list.size() - 7); i++) {
                sqLiteTable.setTitleNews(list.get(i).titleNews);
                sqLiteTable.setTitle(list.get(i).title);
                sqLiteTable.setApi_url(list.get(i).api_url);
                sqLiteTable.setBlock_color(list.get(i).block_color);
                sqLiteTable.setPic(list.get(i).pic);
                dbManager.save(sqLiteTable);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }

    }
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isFirst", false);
        editor.commit();
    }
}
