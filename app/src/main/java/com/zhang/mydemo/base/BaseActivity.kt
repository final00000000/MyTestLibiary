package com.zhang.kotlindemo.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar


/**
 * @Author : zhang
 * @Create Time : 2021-11-15 13:11:13
 * @Class Describe : 描述
 * @Project Name : KotlinDemo
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImmersionBar.with(this)
            .autoDarkModeEnable(true)
            .statusBarDarkFont(true, 1.0f)
            .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
            .init()
    }

}