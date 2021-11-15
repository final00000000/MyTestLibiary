package com.zhang.mydemo

import android.os.Bundle
import com.zhang.kotlindemo.base.BaseActivity
import com.zhang.mydemo.java.JavaActivity
import com.zhang.mydemo.kotlin.KotlinActivity
import com.zhang.mydemo.kotlin.utils.singleClick
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    fun initView() {
        tv_01.singleClick {
            toast("演示")
        }
        kotlin.singleClick {
            startActivity<KotlinActivity>()
        }
        java.singleClick {
            startActivity<JavaActivity>()
        }
    }
}