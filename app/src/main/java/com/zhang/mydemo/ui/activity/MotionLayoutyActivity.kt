package com.zhang.mydemo.ui.activity

import com.zhang.mydemo.base.BaseActivity
import com.zhang.mydemo.databinding.ActivityMotionLayoutyBinding
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.layout_title.*

class MotionLayoutyActivity : BaseActivity<ActivityMotionLayoutyBinding>() {

    override fun initView() {
    }

    override fun initData() {
    }

    override fun setListener() {
        ivPageBack.singleClick { killMyself() }
        tvPageTitle.text = "动画"
    }
}