package com.zhang.mydemo.java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.zhang.kotlindemo.base.BaseActivity;
import com.zhang.mydemo.R;

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
    }
}