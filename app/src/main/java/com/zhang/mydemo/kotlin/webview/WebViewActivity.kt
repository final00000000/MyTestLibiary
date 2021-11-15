package com.zhang.mydemo.kotlin.webview

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.zhang.kotlindemo.base.BaseActivity
import com.zhang.mydemo.R
import kotlinx.android.synthetic.main.activity_web_view.*


class WebViewActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        initData()
    }

    fun initData() {

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
        web.loadUrl("https://www.baidu.com/")

        web.settings.javaScriptEnabled = true // 开启Android和js交互
        //需要添加上面这个 因为会报这个错-> You need to enable javaScript to run this app.

    }
}