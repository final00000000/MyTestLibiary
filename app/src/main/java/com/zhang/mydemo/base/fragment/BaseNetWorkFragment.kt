package com.zhang.mydemo.base.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.gyf.immersionbar.ImmersionBar
import com.tencent.mmkv.MMKV
import com.zhang.mydemo.R
import com.zhang.mydemo.base.IsBase
import com.zhang.utilslibiary.utils.AppActivityManager
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.layout_title.*

/**
 * @Author : zhang
 * @Create Time : 2022/3/23
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
abstract class BaseNetWorkFragment<VB : ViewBinding, VM : ViewModel> : BaseVBVMFragment<VB, VM>() {


}

