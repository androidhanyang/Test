package com.hy.myfuck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ShequItemActivity extends AppCompatActivity {

    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shequ_item);
        init();
    }

    private void init() {
        web = (WebView) findViewById(R.id.shequ_item_wv);
        Intent intent = getIntent();
        String url = intent.getStringExtra("123");
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
