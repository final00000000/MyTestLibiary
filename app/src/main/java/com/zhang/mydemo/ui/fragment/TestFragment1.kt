package com.zhang.mydemo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zhang.mydemo.R
import com.zhang.utilslibiary.utils.getColorRes

class TestFragment1 : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getInt("param1").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_test1, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) = TestFragment1().apply {
            arguments = Bundle().apply {
                putInt("param1", param1)
            }
        }
    }
}