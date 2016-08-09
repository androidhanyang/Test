package com.hy.myfuck;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class SettingMine extends Activity {

    private ScrollView scrollView;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_mine);
        init();
    }

    private void init() {
        scrollView = (ScrollView) findViewById(R.id.mine_sv);
        ll = (LinearLayout) findViewById(R.id.mine_ll);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingMine.this,DengluActivity.class);
                startActivity(intent);
            }
        });
    }


}
