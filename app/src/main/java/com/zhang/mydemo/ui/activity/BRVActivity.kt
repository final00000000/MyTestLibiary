package com.zhang.mydemo.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zhang.mydemo.R
import com.zhang.mydemo.base.activity.BaseActivity
import com.zhang.mydemo.databinding.ActivityBrvactivityBinding
import kotlinx.android.synthetic.main.activity_brvactivity.*
import kotlinx.android.synthetic.main.layout_title.*

class BRVActivity : BaseActivity<ActivityBrvactivityBinding>() {

    override fun initData() {
    }

    override fun setListener() {
    }

    override fun initView(savedInstanceState: Bundle?) {
        tvPageTitle.text = "新适配器练习"

    }
}