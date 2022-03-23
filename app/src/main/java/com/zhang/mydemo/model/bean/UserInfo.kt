package com.zhang.mydemo.model.bean

/**
 * @Author : zhang
 * @Create Time : 2022/3/23
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
data class UserInfo(
    var userId: Int? = 0,
    var userName: String? = "",
    var userAvatar: String? = "",
    var userSex: String? = "",
)
