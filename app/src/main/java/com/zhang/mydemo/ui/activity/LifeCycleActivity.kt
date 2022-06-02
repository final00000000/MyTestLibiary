package com.zhang.mydemo.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.zhang.mydemo.base.activity.BaseActivity
import com.zhang.mydemo.databinding.ActivityLifeCycleBinding
import kotlinx.android.synthetic.main.activity_life_cycle.*

class LifeCycleActivity : BaseActivity<ActivityLifeCycleBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        lifecycle.addObserver(Chorno)
    }

    override fun initData() {
        MediaStore.getMediaScannerUri()

    }

    override fun setListener() {
    }
}