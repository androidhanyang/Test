package com.hy.myfuck;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hy.myfuck.bean.NewsTab;
import com.hy.myfuck.dao.XUtil;
import com.hy.myfuck.json.NetJson;
import com.hy.myfuck.netutils.NetPagerUtils;
import com.hy.myfuck.view.PullToRefreshView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.xutils.DbManager;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private FragmentManager fragmentManager;
    List<NewsTab>list;
    BaseFragment fragmentA, fragmentB, fragmentC, fragmentD, fragmentE;

    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private RadioButton radioButton5;
    private FrameLayout frameLayout;


    private static boolean isExit = false;
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
    private DbManager dbManager;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setData();
    }

    private void initView() {
        frameLayout = (FrameLayout) findViewById(R.id.fl);
        radioButton1 = (RadioButton) findViewById(R.id.main_rg_rb1);
        radioButton2 = (RadioButton) findViewById(R.id.main_rg_rb2);
        radioButton3 = (RadioButton) findViewById(R.id.main_rg_rb3);
        radioButton4 = (RadioButton) findViewById(R.id.main_rg_rb4);
        radioButton5 = (RadioButton) findViewById(R.id.main_rg_rb5);
        radioButton1.setOnClickListener(this);
        radioButton2.setOnClickListener(this);
        radioButton3.setOnClickListener(this);
        radioButton4.setOnClickListener(this);
        radioButton5.setOnClickListener(this);
        DbManager.DaoConfig daoConfig = XUtil.getDaoConfig();
        dbManager = x.getDb(daoConfig);
        initFragment();
        fragmentManager = getSupportFragmentManager();
    }
    private void setData(){
//        if (sharedPreferences.getBoolean("isFirst", true)) {
//            onChannelNet();
//        }
    }
public void onChannelNet(){
    OkHttpUtils.get().url(NetPagerUtils.NET_CHANNEL).build().execute(new StringCallback() {
        @Override
        public void onError(Call call, Exception e, int i) {

        }

        @Override
        public void onResponse(String s, int i) {
list= NetJson.newsTabs(s);

        }
    });
}
    private void initFragment() {
        fragmentA = new FragmentA();
        fragmentB = new FragmentB();
        fragmentC = new FragmentC();
        fragmentD = new FragmentD();
        fragmentE = new FragmentE();
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.main_rg_rb1:
                transaction.replace(R.id.fl, fragmentA);
                break;
            case R.id.main_rg_rb2:
                transaction.replace(R.id.fl, fragmentB);
                break;
            case R.id.main_rg_rb3:
                transaction.replace(R.id.fl, fragmentC);
                break;
            case R.id.main_rg_rb4:
                transaction.replace(R.id.fl, fragmentD);
                break;
            case R.id.main_rg_rb5:
                transaction.replace(R.id.fl, fragmentE);
                break;
            default:
                break;
        }
        transaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(this, "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();

        }
    }
}

