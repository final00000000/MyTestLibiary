package com.zhang.mydemo.kotlin.utils

import android.view.View

/**
 * @Author : zhang
 * @Create Time : 2021/10/20 13:23
 * @Class Describe : 描述
 * @Project Name : KotlinDemo
 */
fun View.singleClick(isCheckLogin: Boolean = false, listener: (View?) -> Unit) {
    setOnClickListener(SingleClickListener(isCheckLogin, listener))
}

fun View.singleClick(isCheckLogin: Boolean = false, t: Int, listener: (View?) -> Unit) {
    setOnClickListener(SingleClickListener(isCheckLogin, t, listener))
}