package com.zhang.mydemo.base

import java.lang.annotation.ElementType
import java.lang.annotation.ElementType.*
import java.lang.annotation.RetentionPolicy

/**
 * @Author : zhang
 * @Create Time : 2021/11/24
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
interface IsBase {
    /**
     * 是否需要使用带有TitleBar的父容器
     */
    fun isLayoutToolbar(): Boolean

    /**
     * 是否需要自动注册Eventbus
     * 存在BUG 先注释掉了
     */
//    fun isEventbus(): Boolean

}