package com.zhang.mydemo.base.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.gyf.immersionbar.ImmersionBar
import com.tencent.mmkv.MMKV
import com.zhang.mydemo.R
import com.zhang.mydemo.base.IsBase
import com.zhang.mydemo.base.manager.NetWorkState
import com.zhang.mydemo.base.manager.NetworkManager
import com.zhang.utilslibiary.utils.AppActivityManager
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.layout_title.*

/**
 * @Author : zhang
 * @Create Time : 2022/3/23
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
abstract class BaseNetWorkActivity<VB : ViewBinding, VM : ViewModel> : BaseVBVMActivity<VB, VM>() {


    /**
     * 是否需要带toolbar的布局
     * 如果不需要自带的toolbar 就在对应的activity重写该方法
     * @return Boolean
     */
    override fun isLayoutToolbar(): Boolean = true

    var mDefaultMMKV: MMKV = MMKV.defaultMMKV()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isLayoutToolbar()) {
            setContentView(R.layout.activity_base)
            baseContent.addView(initViewBinding())
            /**
             * toolbar返回键
             */
            ivPageBack.singleClick { killMyself() }
        } else {
            setContentView(initViewBinding())
        }
        AppActivityManager.addActivity(this)
        init(savedInstanceState)
    }


    protected abstract fun initView(savedInstanceState: Bundle?)

    protected abstract fun setListener()

    protected abstract fun createObserver()

    private fun init(savedInstanceState: Bundle?) {

        //初始化设置沉浸式状态栏
        initImmerBar()
        //初始化控件
        initView(savedInstanceState)
        // 设置监听
        setListener()
        // 数据观察
        createObserver()

        NetworkManager.instance.mNetworkStateCallback.observeSticky(this) {
            onNetworkStateChanged(it)
        }
    }


    /**
     * 网络变化监听 子类重写
     */
    open fun onNetworkStateChanged(netState: NetWorkState) {}


    /**
     * 初始化设置沉浸式状态栏
     */
    fun initImmerBar() {
        ImmersionBar.with(this)
            .statusBarDarkFont(true, 0.2f)
            .init()
    }

    protected fun killMyself() {
        AppActivityManager.removeActivity(this)
        finish()
    }
}

