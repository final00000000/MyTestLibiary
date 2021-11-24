package com.zhang.mydemo.java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhang.mydemo.R;
import com.zhang.mydemo.base.BaseActivity;
import com.zhang.mydemo.java.date.DateUtilsActivity;
import com.zhang.mydemo.java.jump.JumpTextActivity;
import com.zhang.mydemo.java.starbar.StarRatingActivity;

public class JavaActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_java;
    }

    @Override
    protected void initData() {
        findViewById(R.id.ivPageBack).setOnClickListener(v->{killMyself();});
        TextView tvPageTitle = findViewById(R.id.tvPageTitle);
        tvPageTitle.setText("Java");

    }

    @Override
    protected void setListener() {
        findViewById(R.id.date_utils).setOnClickListener(v -> {
            startActivity(new Intent(this, DateUtilsActivity.class));
        });
        findViewById(R.id.java_1).setOnClickListener(v -> {
            startActivity(new Intent(this, StarRatingActivity.class));
        });
        findViewById(R.id.java_2).setOnClickListener(v -> {
            startActivity(new Intent(this, JumpTextActivity.class));
        });
    }

    @Override
    protected void initView() {

    }
}