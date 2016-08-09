package com.hy.myfuck.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hy.myfuck.R;

public class WanLeActivity extends AppCompatActivity {

    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wan_le);

        init();
    }

    private void init() {
        web = (WebView) findViewById(R.id.wanle_webView);
        Intent intent = getIntent();
        String url = intent.getStringExtra("keykey");
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setBlockNetworkImage(false);
        web.loadUrl(url);
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
