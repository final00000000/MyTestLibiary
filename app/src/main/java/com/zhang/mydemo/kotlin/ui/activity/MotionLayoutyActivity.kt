package com.zhang.mydemo.kotlin.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zhang.mydemo.R
import com.zhang.mydemo.base.BaseActivity
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.layout_title.*

class MotionLayoutyActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_motion_layouty

    override fun initView() {
    }

    override fun initData() {
    }

    override fun setListener() {
        ivPageBack.singleClick { killMyself() }
        tvPageTitle.text = "动画"
    }
}