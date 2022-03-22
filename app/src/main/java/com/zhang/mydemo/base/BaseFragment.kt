package com.zhang.mydemo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.tencent.mmkv.MMKV
import java.lang.reflect.ParameterizedType

/**
 * @Author : zhang
 * @Create Time : 2022/3/9
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    var defaultMMKV: MMKV = MMKV.defaultMMKV()

    lateinit var viewBinding: VB

    private var isLazyLoad = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = getViewBindingForFragment(layoutInflater, container)
        return viewBinding.root
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

    @Suppress("UNCHECKED_CAST")
    fun getViewBindingForFragment(layoutInflater: LayoutInflater, container: ViewGroup?): VB {
        val type = javaClass.genericSuperclass as ParameterizedType
        val aClass = type.actualTypeArguments[0] as Class<*>
        val method = aClass.getDeclaredMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        return method.invoke(null, layoutInflater, container, false) as VB
    }
}