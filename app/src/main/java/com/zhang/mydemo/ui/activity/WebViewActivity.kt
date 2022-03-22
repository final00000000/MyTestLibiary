package com.zhang.mydemo.ui.activity

import android.webkit.WebView
import android.webkit.WebViewClient
import com.zhang.mydemo.base.BaseActivity
import com.zhang.mydemo.databinding.ActivityWebViewBinding
import kotlinx.android.synthetic.main.activity_web_view.*


class WebViewActivity : BaseActivity<ActivityWebViewBinding>() {

    override fun isLayoutToolbar(): Boolean {
        return false
    }

    override fun initView() {

    }

    override fun initData() {
        // 防止链接跳到自带浏览器
        web.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return super.shouldOverrideUrlLoading(view, url)
            }
        }
        /**
         * 9.0版本以上需要加  android:usesCleartextTraffic="true" 不加就是白屏.
         */
        web.loadUrl(intent.getStringExtra("url")!!)

        web.settings.javaScriptEnabled = true // 开启Android和js交互
        //需要添加上面这个 因为会报这个错-> You need to enable javaScript to run this app.
    }

    override fun setListener() {

    }
}