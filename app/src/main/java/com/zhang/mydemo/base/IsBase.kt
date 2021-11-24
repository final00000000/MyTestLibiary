package com.zhang.mydemo.base

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
     */
    fun isEventbus(): Boolean
}