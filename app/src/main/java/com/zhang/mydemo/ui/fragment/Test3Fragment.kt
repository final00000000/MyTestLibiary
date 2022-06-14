package com.zhang.mydemo.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhang.mydemo.R
import com.zhang.mydemo.base.fragment.BaseFragment
import com.zhang.mydemo.databinding.FragmentTest3Binding

class Test3Fragment : BaseFragment<FragmentTest3Binding>() {

    companion object {
        @JvmStatic
        fun newInstance() =
            Test3Fragment().apply {
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