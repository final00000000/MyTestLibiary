package com.zhang.mydemo.ui.activity

import com.zhang.mydemo.base.BaseActivity
import com.zhang.mydemo.databinding.ActivityJumpTextBinding
import kotlinx.android.synthetic.main.layout_title.*

class JumpTextActivity : BaseActivity<ActivityJumpTextBinding>() {

    override fun initView() {
        tvPageTitle.text = "文字绘制轨迹"
    }

    override fun initData() {}
    override fun setListener() {}
}