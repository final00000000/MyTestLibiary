package com.zhang.utilslibiary.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import java.lang.reflect.ParameterizedType

/**
 * @Author : zhang
 * @Create Time : 2022/3/23
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */

/**
 * 获取当前类绑定的泛型ViewModel-clazz
 */
fun <VM> getVmClass(obj: AppCompatActivity): VM {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as VM
}

inline fun <reified VM : ViewModel> AppCompatActivity.viewModelOf(
    factory: ViewModelProvider.Factory
) = ViewModelProvider(this, factory)[VM::class.java]


/**
 * 获取ViewModel
 */
/*
fun createViewModel(appCompatActivity: AppCompatActivity) {
    //这里获得到的是类的泛型的类型
    val type = appCompatActivity.javaClass.genericSuperclass
    if (type != null && type is ParameterizedType) {
        val actualTypeArguments = type.actualTypeArguments
        val tClass = actualTypeArguments[1]
        mViewModel = ViewModelProvider(
            appCompatActivity,
            ViewModelProvider.AndroidViewModelFactory.getInstance(appCompatActivity.application)
        )[tClass as Class<VM>]
    }
}*/
