package com.zhang.mydemo.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * @Author : zhang
 * @Create Time : 2022/3/23
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
abstract class BaseViewModel : ViewModel() {
    abstract fun getData()
}