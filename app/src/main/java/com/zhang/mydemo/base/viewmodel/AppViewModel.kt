package com.zhang.mydemo.base.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhang.mydemo.base.request
import com.zhang.mydemo.base.viewmodel.BaseViewModel
import com.zhang.mydemo.model.bean.UserInfo

/**
 * @Author : zhang
 * @Create Time : 2022/3/23
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
class AppViewModel : BaseViewModel() {
    var mLive = MutableLiveData<UserInfo>()


    override fun getData() {

    }
}