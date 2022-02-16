package com.zhang.mydemo.base

import android.app.Application
import android.content.Context
import com.elvishew.xlog.LogConfiguration
import com.elvishew.xlog.XLog
import com.tencent.mmkv.MMKV


/**
 * @Author : zhang
 * @Create Time : 2021-11-15 13:10:36
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
class MyDemoAppLication : Application() {


    fun getApp() : MyDemoAppLication{
        return this
    }


    override fun onCreate() {
        super.onCreate()
        // Xlog打印日志初始化 更多花样详情百度.
        val config = LogConfiguration.Builder()
            .enableThreadInfo() // 允许打印线程信息
//            .enableBorder()     // 边框
            .build()
        XLog.init(config)
        MMKV.initialize(this)
    }
}