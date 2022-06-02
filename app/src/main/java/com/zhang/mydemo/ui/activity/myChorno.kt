package com.zhang.mydemo.ui.activity

import android.content.Context
import android.util.AttributeSet
import android.widget.Chronometer
import androidx.lifecycle.*
import timber.log.Timber

/**
 * @Author : zhang
 * @Create Time : 2022/5/17
 * @Class Describe :  项目描述
 * @Project Name : MyDemo
 */
class myChorno : Chronometer, DefaultLifecycleObserver {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun startMeter() {
        Timber.e("myChorno==>开始")
        start()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun stopMeter() {
        Timber.e("myChorno==>暂停")
        stop()
    }
}