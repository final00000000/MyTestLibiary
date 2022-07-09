package com.zhang.mydemo.ui.fragment

import android.R.attr.path
import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Bundle
import androidx.core.app.ShareCompat
import com.zhang.mydemo.R
import com.zhang.mydemo.base.fragment.BaseFragment
import com.zhang.mydemo.databinding.FragmentHomeBinding
import com.zhang.mydemo.ui.activity.RecyclerViewDraggableActivity
import com.zhang.mydemo.ui.activity.TabLayoutViewPagerDeleteActivity
import com.zhang.utilslibiary.utils.IpUtils
import com.zhang.utilslibiary.utils.singleClick
import com.zhang.utilslibiary.utils.toast.Toasty
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
        Timber.e("HomeFragment_36行_2022/7/9_11:36：${IpUtils.ipAddress}")
        Timber.e("HomeFragment_37行_2022/7/9_11:36：${IpUtils.outNetIP}")
//        Timber.e("HomeFragment_38行_2022/7/9_11:36：${IpUtils.GetNetIp()}")

    }

    override fun initData() {
    }

    override fun setListener() {
        mViewBinding.apply {
            tv01.singleClick {
                Toasty.success(R.string.Describe)
                share()
                for (i in 0..10 step 3) {
                    Timber.e("数字==>${i}")
                    Timber.e("setListener: $i")
                }

            }
            tv02.singleClick {
                startActivity<RecyclerViewDraggableActivity>()
            }
            tv03.singleClick {
                startActivity<TabLayoutViewPagerDeleteActivity>()
            }
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