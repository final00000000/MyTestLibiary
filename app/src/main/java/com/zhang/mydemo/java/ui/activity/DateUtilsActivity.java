package com.zhang.mydemo.java.ui.activity;

import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

import com.zhang.mydemo.R;
import com.zhang.mydemo.base.BaseActivity;
import com.zhang.utilslibiary.utils.DateUtil;

public class DateUtilsActivity extends BaseActivity {

    private AppCompatTextView mJavaTv1;
    private AppCompatTextView mJavaTv2;
    private AppCompatTextView mJavaTv3;
    private AppCompatTextView mJavaTv4;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_date_utils;
    }

    @Override
    protected void initView() {
        mJavaTv1 = findViewById(R.id.java_tv_1);
        mJavaTv2 = findViewById(R.id.java_tv_2);
        mJavaTv3 = findViewById(R.id.java_tv_3);
        mJavaTv4 = findViewById(R.id.java_tv_4);
    }

    @Override
    protected void initData() {
        findViewById(R.id.ivPageBack).setOnClickListener(v->{killMyself();});
        TextView tvPageTitle = findViewById(R.id.tvPageTitle);
        tvPageTitle.setText("时间工具类");
    }

    @Override
    protected void setListener() {
        mJavaTv1.setOnClickListener(v -> mJavaTv1.setText("当前时间戳：" + DateUtil.INSTANCE.getCurrentMillis()));
        mJavaTv2.setOnClickListener(v -> mJavaTv2.setText("当前时间格式为(yy-MM-dd hh-mm-ss)：" + DateUtil.INSTANCE.getCurrentTimeYMDHMS()));
        mJavaTv3.setOnClickListener(v -> mJavaTv3.setText("当前时间格式为(yy-MM-dd)：" + DateUtil.INSTANCE.getCurrentTimeYMD()));
        mJavaTv4.setOnClickListener(v -> mJavaTv4.setText("获取现在时间：" + DateUtil.INSTANCE.getStringDate()));
    }

}