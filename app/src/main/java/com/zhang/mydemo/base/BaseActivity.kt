package com.zhang.kotlindemo.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar
import com.zhang.mydemo.R
import org.greenrobot.eventbus.EventBus


/**
 * @Author : zhang
 * @Create Time : 2021-11-15 13:11:13
 * @Class Describe : 描述
 * @Project Name : KotlinDemo
 */
open class BaseActivity : AppCompatActivity() {

    /**
     * 是否需要使用带有TitleBar的父容器
     */
    fun isUseParentLayout() = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImmersionBar.with(this)
            .autoDarkModeEnable(true)
            .statusBarDarkFont(true, 1.0f)
            .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
            .init()


        if (isUseParentLayout()) {
            setContentView(R.layout.activity_base)
//            baseContent.addView(LayoutInflater.from(this).inflate(initView(savedInstanceState), null))
        } else {
//            setContentView(initView(savedInstanceState))
//            addContentView(initView(savedInstanceState), FrameLayout.LayoutParams(matchParent, matchParent))
        }
    }

}