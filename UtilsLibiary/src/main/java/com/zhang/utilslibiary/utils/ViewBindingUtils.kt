package com.zhang.utilslibiary.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * @Author : zhang
 * @Create Time : 2022/3/23
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */

fun <VB : ViewBinding, any : Any> any.getViewBindingForActivity(layoutInflater: LayoutInflater): VB {
    val type = javaClass.genericSuperclass as ParameterizedType
    val aClass = type.actualTypeArguments[0] as Class<*>
    val method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
    return method.invoke(null, layoutInflater) as VB
}


@Suppress("UNCHECKED_CAST")
fun <VB : ViewBinding, any : Any> any.getViewBindingForFragment(
    layoutInflater: LayoutInflater,
    container: ViewGroup?
): VB {
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