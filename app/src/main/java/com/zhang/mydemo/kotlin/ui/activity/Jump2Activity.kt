package com.zhang.mydemo.kotlin.ui.activity

import android.content.Intent
import com.example.baselibiary.base.BaseActivity
import com.zhang.mydemo.databinding.ActivityJump2Binding
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_jump2.*
import kotlinx.android.synthetic.main.layout_title.*
import kotlinx.android.synthetic.main.layout_title.ivPageBack as ivPageBack1

class Jump2Activity : BaseActivity<ActivityJump2Binding>() {

    override fun initView() {
        tv_show.text = intent.getStringExtra("data")
    }

    override fun initData() {
    }

    override fun setListener() {
        ivPageBack1.singleClick { killMyself() }
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