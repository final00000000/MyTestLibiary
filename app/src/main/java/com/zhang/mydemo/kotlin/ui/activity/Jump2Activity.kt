package com.zhang.mydemo.kotlin.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zhang.mydemo.R
import com.zhang.mydemo.base.BaseActivity
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_jump2.*
import kotlinx.android.synthetic.main.layout_title.*

class Jump2Activity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_jump2

    override fun initView() {
        tv_show.text = intent.getStringExtra("data")
    }

    override fun initData() {
    }

    override fun setListener() {
        ivPageBack.singleClick { killMyself() }
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