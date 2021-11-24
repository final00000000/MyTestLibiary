package com.zhang.mydemo.kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import com.zhang.mydemo.R
import com.zhang.mydemo.base.BaseActivity
import com.zhang.mydemo.kotlin.glide.TestGlideActivity
import com.zhang.mydemo.kotlin.imageorvideo.PickViewActivity
import com.zhang.mydemo.kotlin.keyboard.KeyBoard
import com.zhang.mydemo.kotlin.pickerview.PickerDateActivity
import com.zhang.mydemo.kotlin.richtext.RichTextActivity
import com.zhang.mydemo.kotlin.webview.WebViewActivity
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_kotlin.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.startActivity

class KotlinActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_kotlin

    override fun initView() {
        ivPageBack.singleClick { killMyself() }
        tvPageTitle.text = "Kotlin"
    }

    override fun initData() {

    }

    override fun setListener() {
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
            startActivity<PickerDateActivity>()
        }
        tv_05.singleClick {
            startActivity<TestGlideActivity>()
        }
        tv_06.singleClick {
            startActivity<PickViewActivity>()
        }
    }
}