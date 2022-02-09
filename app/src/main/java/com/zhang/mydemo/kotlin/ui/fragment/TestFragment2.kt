package com.zhang.mydemo.kotlin.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elvishew.xlog.XLog
import com.zhang.mydemo.R
import kotlinx.android.synthetic.main.fragment_test1.*
import kotlinx.android.synthetic.main.fragment_test1.tv_show
import kotlinx.android.synthetic.main.fragment_test2.*

class TestFragment2 : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_test2, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) = TestFragment2().apply {
            arguments = Bundle().apply {
                putInt("param1", param1)
            }
        }
    }
}