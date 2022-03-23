package com.zhang.mydemo.base

/**
 * @Author : zhang
 * @Create Time : 2022/3/23
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
data class HttpResult<T>(
    var code: Int? = 200,
    var message: String? = "",
    var data: T,
    var isSuccess: Boolean = code == 200 || code == 1000
)
