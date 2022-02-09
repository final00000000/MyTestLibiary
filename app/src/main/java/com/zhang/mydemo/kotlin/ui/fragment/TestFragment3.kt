package com.zhang.mydemo.kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zhang.mydemo.R
import kotlinx.android.synthetic.main.fragment_test3.*


class TestFragment3 : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_test3, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) = TestFragment3().apply {
            arguments = Bundle().apply {
                putInt("param1", param1)
            }
        }
    }
}