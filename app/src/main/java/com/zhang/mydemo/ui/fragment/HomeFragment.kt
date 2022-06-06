package com.zhang.mydemo.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.app.ShareCompat
import com.zhang.mydemo.R
import com.zhang.mydemo.base.fragment.BaseFragment
import com.zhang.mydemo.databinding.FragmentHomeBinding
import com.zhang.mydemo.ui.activity.RecyclerViewDraggableActivity
import com.zhang.utilslibiary.utils.singleClick
import com.zhang.utilslibiary.utils.toast.Toasty
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.support.v4.startActivity
import timber.log.Timber

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }

    override fun initView() {

    }

    override fun initData() {
    }

    override fun setListener() {
        tv_01.singleClick {
            Toasty.success(R.string.Describe)
            share()
            for (i in 0..10 step 3) {
                Timber.e("数字==>${i}")
                Timber.e("setListener: $i")
            }
        }
        tv_02.singleClick {
            startActivity<RecyclerViewDraggableActivity>()
        }
    }

    @SuppressLint("StringFormatInvalid")
    private fun share() {
        val intent = ShareCompat.IntentBuilder(requireActivity())
            .setType("text/plain")
            .setChooserTitle(getString(R.string.DescribeWebView))
//            .setText(getString(R.string.DescribeRichText))
            .intent

        startActivity(Intent.createChooser(intent, ""))
    }

}