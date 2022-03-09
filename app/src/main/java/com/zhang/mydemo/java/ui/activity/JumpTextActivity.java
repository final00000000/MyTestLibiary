package com.zhang.mydemo.java.ui.activity;

import android.widget.TextView;

import com.zhang.mydemo.R;
import com.example.baselibiary.base.BaseActivity;
import com.zhang.mydemo.databinding.ActivityJumpTextBinding;

public class JumpTextActivity extends BaseActivity<ActivityJumpTextBinding> {

    @Override
    protected void initView() {
        findViewById(R.id.ivPageBack).setOnClickListener(v -> {
            killMyself();
        });
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