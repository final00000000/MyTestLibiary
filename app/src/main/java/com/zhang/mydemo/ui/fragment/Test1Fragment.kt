package com.zhang.mydemo.ui.fragment

import android.os.Bundle
import com.zhang.mydemo.base.fragment.BaseFragment
import com.zhang.mydemo.databinding.FragmentTest1Binding

class Test1Fragment : BaseFragment<FragmentTest1Binding>() {

    companion object {
        @JvmStatic
        fun newInstance() =
            Test1Fragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun setListener() {

    }

}
