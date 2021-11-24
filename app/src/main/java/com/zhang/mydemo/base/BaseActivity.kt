package com.zhang.mydemo.base

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.animation.ArgbEvaluatorCompat
import com.gyf.immersionbar.ImmersionBar
import com.zhang.mydemo.R
import com.zhang.utilslibiary.utils.AppManager
import kotlinx.android.synthetic.main.activity_base.*
import org.greenrobot.eventbus.EventBus


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

    /**
     * 是否需要自己注册EventBus
     * @return Boolean
     */
    override fun isEventbus(): Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.addActivity(this)

        if (isLayoutToolbar()) {
            setContentView(R.layout.activity_base)
            baseContent.addView(LayoutInflater.from(this).inflate(getLayoutId(), null))
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
        AppManager.removeActivity(this)
        finish()
    }

    /**
     * 初始化设置沉浸式状态栏
     */
    fun initImmerBar() {
        val barColor: Int =
            ArgbEvaluatorCompat.getInstance().evaluate(1.0f, Color.BLACK, Color.WHITE)
        val textColor: Int =
            ArgbEvaluatorCompat.getInstance().evaluate(1.0f, Color.WHITE, Color.BLACK)

        ImmersionBar.with(this)
            .statusBarDarkFont(true, 0.2f)
            .init()
    }

    override fun onStart() {
        super.onStart()
        if (isEventbus()) {
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this)
            }
        } else {
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
        AppManager.removeActivity(this)
    }


}