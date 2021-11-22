package com.zhang.mydemo.kotlin

import android.os.Bundle
import com.zhang.kotlindemo.base.BaseActivity
import com.zhang.mydemo.R
import com.zhang.mydemo.kotlin.RecyclerView.RecyclerViewActivity
import com.zhang.mydemo.kotlin.keyboard.KeyBoard
import com.zhang.mydemo.kotlin.pickerview.PickerDateActivity
import com.zhang.mydemo.kotlin.richtext.RichTextActivity
import com.zhang.mydemo.kotlin.utils.singleClick
import com.zhang.mydemo.kotlin.webview.WebViewActivity
import kotlinx.android.synthetic.main.activity_kotlin.*
import org.jetbrains.anko.startActivity

class KotlinActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        initView()
    }

    fun initView() {
        tv_01.singleClick {
            startActivity<WebViewActivity>()
        }
        tv_02.singleClick {
            startActivity<KeyBoard>()
        }
        tv_03.singleClick {
            startActivity<RichTextActivity>()
        }
        tv_04.singleClick {
            startActivity<RecyclerViewActivity>()
        }
        tv_05.singleClick {
            startActivity<PickerDateActivity>()
        }
    }
}