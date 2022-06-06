package com.zhang.mydemo.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhang.mydemo.base.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * @Author : zhang
 * @Create Time : 2022/6/6
 * @Class Describe :  项目描述
 * @Project Name : MyDemo
 */
class MessageViewModel : BaseViewModel() {

    var mLiveData = MutableLiveData<Int>()

    fun getCurrentLiveData(): MutableLiveData<Int> {
        if (mLiveData.value == null) {
            mLiveData.postValue(0)
        }
        return mLiveData
    }

    override fun getData() {

    }
}