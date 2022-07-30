package com.zhang.mydemo.viewmodel

import androidx.lifecycle.MutableLiveData
import com.kunminx.architecture.domain.message.MutableResult
import com.kunminx.architecture.ui.callback.UnPeekLiveData
import com.zhang.mydemo.base.viewmodel.BaseViewModel

/**
 * @Author : zhang
 * @Create Time : 2022/7/27
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
class HomeViewModel : BaseViewModel() {
    var mLiveData = UnPeekLiveData<String>()
    override fun getData() {
        mLiveData.postValue("1")
    }
}