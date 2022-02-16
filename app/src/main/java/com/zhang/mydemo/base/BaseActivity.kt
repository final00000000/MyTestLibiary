package com.zhang.mydemo.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar
import com.zhang.mydemo.R
import com.zhang.utilslibiary.utils.AppActivityManager
import kotlinx.android.synthetic.main.activity_base.*
import android.content.ComponentName

import android.app.ActivityManager.RunningTaskInfo

import android.app.ActivityManager
import android.content.Context
import com.tencent.mmkv.MMKV
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.layout_title.*


/**
 * @Author : zhang
 * @Create Time : 2021-11-15 13:11:13
 * @Class Describe : 描述
 * @Project Name : KotlinDemo
 */
abstract class BaseActivity : AppCompatActivity(), IsBase {

    /**
     * 是否需要带toolbar的布局
     * 如果不需要自带的toolbar 就在对应的activity重写该方法
     * @return Boolean
     */
    override fun isLayoutToolbar(): Boolean = true
    var defaultMMKV: MMKV = MMKV.defaultMMKV()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppActivityManager.addActivity(this)

        if (isLayoutToolbar()) {
            setContentView(R.layout.activity_base)
            baseContent.addView(LayoutInflater.from(this).inflate(getLayoutId(), null))
            ivPageBack.singleClick { killMyself() }
        } else {
            setContentView(getLayoutId())
        }

        //初始化设置沉浸式状态栏
        initImmerBar()
        //初始化控件
        initView()
        //数据的操作
        initData()
        // 设置监听
        setListener()

    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initView()

    protected abstract fun initData()

    protected abstract fun setListener()

    protected fun killMyself() {
        AppActivityManager.removeActivity(this)
        finish()
    }

    /**
     * 初始化设置沉浸式状态栏
     */
    fun initImmerBar() {
        ImmersionBar.with(this)
            .statusBarDarkFont(true, 0.2f)
            .init()
    }


}