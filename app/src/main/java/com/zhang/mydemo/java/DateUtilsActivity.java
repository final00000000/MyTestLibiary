package com.zhang.mydemo.java;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.zhang.kotlindemo.base.BaseActivity;
import com.zhang.mydemo.R;
import com.zhang.mydemo.java.utils.DateUtils;

public class DateUtilsActivity extends BaseActivity {

    private AppCompatTextView mJavaTv1;
    private AppCompatTextView mJavaTv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_utils);
        initView();
    }

    private void initView() {
        mJavaTv1 = (AppCompatTextView) findViewById(R.id.java_tv_1);
        mJavaTv2 = (AppCompatTextView) findViewById(R.id.java_tv_2);
        mJavaTv1.setOnClickListener(v -> mJavaTv1.setText("当前时间戳：" + DateUtils.getCurrentMillis()));
        mJavaTv2.setOnClickListener(v -> mJavaTv2.setText("格式为(yy-MM-dd hh-mm-ss)当前时间为：" + DateUtils.getCurrentTimeYMDHMS()));
    }
}