package com.zhang.mydemo.base.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.gyf.immersionbar.ImmersionBar
import com.tencent.mmkv.MMKV
import com.zhang.mydemo.R
import com.zhang.mydemo.base.IsBase
import com.zhang.mydemo.base.manager.NetWorkState
import com.zhang.mydemo.base.manager.NetworkManager
import com.zhang.utilslibiary.utils.AppActivityManager
import com.zhang.utilslibiary.utils.getViewBindingForActivity
import com.zhang.utilslibiary.utils.getVmClass
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.layout_title.*
import java.lang.reflect.ParameterizedType

/**
 * @Author : zhang
 * @Create Time : 2022/3/9
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
abstract class BaseVBVMActivity<VB : ViewBinding, VM : ViewModel> : AppCompatActivity(), IsBase {

    /**
     * 是否需要带toolbar的布局
     * 如果不需要自带的toolbar 就在对应的activity重写该方法
     * @return Boolean
     */
    override fun isLayoutToolbar(): Boolean = true

//    lateinit var mViewModel: VM

    lateinit var viewBinding: VB

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

    private fun init(savedInstanceState: Bundle?) {
//        mViewModel = createViewModel()

        //初始化设置沉浸式状态栏
        initImmerBar()
        //初始化控件
        initView(savedInstanceState)
        // 设置监听
        setListener()
        // 数据观察
        createObserver()

        NetworkManager.instance.mNetworkStateCallback.observeSticky(this, {
            onNetworkStateChanged(it)
        })
    }

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
            return ViewModelProvider(this).get(getVmClass(this))
    }

    protected abstract fun initView(savedInstanceState: Bundle?)

    protected abstract fun setListener()

    protected abstract fun createObserver()

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


    /**
     * 网络变化监听 子类重写
     */
    open fun onNetworkStateChanged(netState: NetWorkState) {}


    fun initViewBinding(): View {
        viewBinding = getViewBindingForActivity(layoutInflater)
        return viewBinding.root
    }


    private fun getViewBindingForActivity(layoutInflater: LayoutInflater): VB {
        val type = javaClass.genericSuperclass as ParameterizedType
        val aClass = type.actualTypeArguments[0] as Class<*>
        val method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        return method.invoke(null, layoutInflater) as VB
    }
}