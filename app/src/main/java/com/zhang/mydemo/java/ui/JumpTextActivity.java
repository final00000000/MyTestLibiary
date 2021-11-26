package com.zhang.mydemo.java.ui;

import android.widget.TextView;

import com.zhang.mydemo.R;
import com.zhang.mydemo.base.BaseActivity;

public class JumpTextActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_jump_text;
    }

    @Override
    protected void initView() {
        findViewById(R.id.ivPageBack).setOnClickListener(v->{killMyself();});
        TextView tvPageTitle = findViewById(R.id.tvPageTitle);
        tvPageTitle.setText("文字绘制轨迹");

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }
}