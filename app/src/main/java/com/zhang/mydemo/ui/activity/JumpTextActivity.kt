package com.zhang.mydemo.ui.activity

import android.os.Bundle
import com.zhang.mydemo.base.activity.BaseActivity
import com.zhang.mydemo.databinding.ActivityJumpTextBinding
import kotlinx.android.synthetic.main.layout_title.*

class JumpTextActivity : BaseActivity<ActivityJumpTextBinding>() {


    override fun initData() {}

    override fun setListener() {}

    override fun initView(savedInstanceState: Bundle?) {
        tvPageTitle.text = "文字绘制轨迹"

    }
}