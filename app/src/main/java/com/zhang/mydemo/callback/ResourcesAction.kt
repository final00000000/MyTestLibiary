package com.zhang.mydemo.callback

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

/**
 * Context 意图处理（扩展非 Context 类的方法，请不要用 Context 子类实现此接口）
 */
interface ResourcesAction {

    fun getContext(): Context

    fun getResources(): Resources {
        return getContext().resources
    }

    fun getString(@StringRes id: Int): String? {
        return getContext().getString(id)
    }

    fun getString(@StringRes id: Int, vararg formatArgs: Any?): String? {
        return getResources().getString(id, *formatArgs)
    }

    fun <S> getSystemService(serviceClass: Class<S>): S {
        return ContextCompat.getSystemService(getContext(), serviceClass)!!
    }
}