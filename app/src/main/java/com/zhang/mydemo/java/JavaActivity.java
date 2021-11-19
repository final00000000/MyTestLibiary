package com.zhang.mydemo.java;

import android.content.Intent;
import android.os.Bundle;

import com.zhang.kotlindemo.base.BaseActivity;
import com.zhang.mydemo.R;
import com.zhang.mydemo.java.date.DateUtilsActivity;
import com.zhang.mydemo.java.scrollview.NestedScrollViewActivity;
import com.zhang.mydemo.java.starbar.StarRatingActivity;

public class JavaActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
        initView();
    }

    private void initView() {
        findViewById(R.id.date_utils).setOnClickListener(v -> {
            startActivity(new Intent(this, DateUtilsActivity.class));
        });
        findViewById(R.id.scroll).setOnClickListener(v -> {
            startActivity(new Intent(this, NestedScrollViewActivity.class));
        });
        findViewById(R.id.java_1).setOnClickListener(v -> {
            startActivity(new Intent(this, StarRatingActivity.class));
        });
    }
}