package com.example.baselibiary.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.baselibiary.R
import com.gyf.immersionbar.ImmersionBar
import com.tencent.mmkv.MMKV
import com.zhang.utilslibiary.utils.AppActivityManager
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.layout_title.*
import java.lang.reflect.ParameterizedType


/**
 * @Author : zhang
 * @Create Time : 2021-11-15 13:11:13
 * @Class Describe : 描述
 * @Project Name : KotlinDemo
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), IsBase {

    /**
     * 是否需要带toolbar的布局
     * 如果不需要自带的toolbar 就在对应的activity重写该方法
     * @return Boolean
     */
    override fun isLayoutToolbar(): Boolean = true

    var defaultMMKV: MMKV = MMKV.defaultMMKV()

    lateinit var viewBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppActivityManager.addActivity(this)

        viewBinding = getViewBindingForActivity(layoutInflater)
        if (isLayoutToolbar()) {
            setContentView(R.layout.activity_base)
//            baseContent.addView(layoutInflater.inflate(getLayoutId(), null,false))
            baseContent.addView(viewBinding.root)
            ivPageBack.singleClick { killMyself() }

        } else {
//            setContentView(getLayoutId())
            setContentView(viewBinding.root)
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

    private fun getViewBindingForActivity(layoutInflater: LayoutInflater): VB {
        val type = javaClass.genericSuperclass as ParameterizedType
        val aClass = type.actualTypeArguments[0] as Class<*>
        val method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        return method.invoke(null, layoutInflater) as VB
    }

//    protected abstract fun getLayoutId(): Int

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