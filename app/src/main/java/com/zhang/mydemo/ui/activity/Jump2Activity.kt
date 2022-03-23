package com.zhang.mydemo.ui.activity

import android.content.Intent
import com.zhang.mydemo.base.activity.BaseActivity
import com.zhang.mydemo.databinding.ActivityJump2Binding
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_jump2.*
import kotlinx.android.synthetic.main.layout_title.*

class Jump2Activity : BaseActivity<ActivityJump2Binding>() {

    override fun initView() {
        tv_show.text = intent.getStringExtra("data")
    }

    override fun initData() {
    }

    override fun setListener() {
        tvPageTitle.text = "学习跳转"
        finish.singleClick {
            val itn = Intent()
            itn.putExtra("data", "这是回传值")
            setResult(999, itn)
            if (!tv_show.text.isNullOrEmpty()) tv_show.text = ""
            killMyself()
        }
    }
}