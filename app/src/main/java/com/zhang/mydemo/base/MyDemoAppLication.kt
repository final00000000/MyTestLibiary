package com.zhang.mydemo.base

import android.app.Application
import android.content.Context
import com.tencent.mmkv.MMKV
import com.zhang.mydemo.BuildConfig
import com.zhang.utilslibiary.utils.toast.Toasty
import timber.log.Timber


/**
 * @Author : zhang
 * @Create Time : 2021-11-15 13:10:36
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
class MyDemoAppLication : Application() {

    fun getApp(): MyDemoAppLication {
        return this
    }

    fun getContext(): Context {
        return getApp().applicationContext
    }


    override fun onCreate() {
        super.onCreate()
        Toasty.setContext(getContext())
        MMKV.initialize(this)
        if(BuildConfig.DEBUG){
        Timber.plant(Timber.DebugTree())
        }
    }

}