package com.zhang.mydemo.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * @Author : zhang
 * @Create Time : 2022/1/25
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
class MyView : View {
    constructor(context: Context?) : super(context, null) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs, 0) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }
}