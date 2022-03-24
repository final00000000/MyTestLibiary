package com.zhang.mydemo.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.zhang.mydemo.base.IsBase
import com.zhang.mydemo.base.manager.NetWorkState
import com.zhang.mydemo.base.manager.NetworkManager
import com.zhang.utilslibiary.utils.getViewBindingForActivity
import java.lang.reflect.ParameterizedType


/**
 * @Author : zhang
 * @Create Time : 2022/3/9
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
abstract class BaseVBVMFragment<VB : ViewBinding, VM : ViewModel> : Fragment(), IsBase {

    /**
     * 是否需要带toolbar的布局
     * 如果不需要自带的toolbar 就在对应的activity重写该方法
     * @return Boolean
     */
    override fun isLayoutToolbar(): Boolean = true

    lateinit var mViewModel: VM

    lateinit var viewBinding: VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initViewBinding()
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        createViewModel()

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


    protected abstract fun initView(savedInstanceState: Bundle?)

    protected abstract fun setListener()

    protected abstract fun createObserver()


    /**
     * 网络变化监听 子类重写
     */
    open fun onNetworkStateChanged(netState: NetWorkState) {}


    fun initViewBinding(): View {
        viewBinding = getViewBindingForActivity(layoutInflater)
        return viewBinding.root
    }


    /**
     * 获取ViewModel
     */
    protected open fun createViewModel(): VM? {
        //这里获得到的是类的泛型的类型
        val type = javaClass.genericSuperclass
        if (type != null && type is ParameterizedType) {
            val actualTypeArguments = type.actualTypeArguments
            val tClass = actualTypeArguments[1]
            return ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application)
            )
                .get(tClass as Class<VM>)
        }
        return null
    }

}