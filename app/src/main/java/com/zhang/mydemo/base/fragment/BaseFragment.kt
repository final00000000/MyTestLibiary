package com.zhang.mydemo.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.tencent.mmkv.MMKV
import com.zhang.utilslibiary.utils.getViewBindingForFragment

/**
 * @Author : zhang
 * @Create Time : 2022/3/9
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    var defaultMMKV: MMKV = MMKV.defaultMMKV()

    lateinit var mViewBinding: VB

    private var isLazyLoad = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewBinding = getViewBindingForFragment(layoutInflater, container)
        return mViewBinding.root
    }

    protected abstract fun initView()

    protected abstract fun initData()

    protected abstract fun setListener()


    override fun onResume() {
        super.onResume()
        if (isLazyLoad) {
            //初始化控件
            initView()
            //数据的操作
            initData()
            // 设置监听
            setListener()
            isLazyLoad = false
        }
    }
}