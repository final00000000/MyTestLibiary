package com.zhang.mydemo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.core.app.ShareCompat
import com.zhang.mydemo.base.activity.BaseNetWorkActivity
import com.zhang.mydemo.base.manager.NetWorkState
import com.zhang.mydemo.databinding.ActivityMainBinding
import com.zhang.mydemo.ui.adapter.NavigationAdapter
import com.zhang.mydemo.viewmodel.MainViewModel
import com.zhang.utilslibiary.utils.AppActivityManager
import com.zhang.utilslibiary.utils.singleClick
import com.zhang.utilslibiary.utils.toast.Toasty
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import timber.log.Timber

class MainActivity : BaseNetWorkActivity<ActivityMainBinding, MainViewModel>() {

    lateinit var navigationAdapter: NavigationAdapter

    var mList = mutableListOf<Pair<String, Int>>()
    override fun initView(savedInstanceState: Bundle?) {
        baseTitle.visibility = View.GONE
        addData()
        navigationAdapter = NavigationAdapter()
        navigationRV.adapter = navigationAdapter
        navigationAdapter.setOnItemClickListener { adapter, view, position ->

            navigationAdapter.mPosition = position
            navigationAdapter.notifyItemChanged(position)
        }
        navigationAdapter.setNewInstance(mutableListOf("1","2","3","4"))
    }

    fun addData() {
//        mList.addAll(Pair < "首页", R.drawable.ic_home_normal >)
    }

    override fun setListener() {
        tv_01.singleClick {
            Toasty.success(R.string.Describe)
            share()
        }
        kotlin.singleClick {
            startActivity<KotlinActivity>()
        }
    }

    @SuppressLint("StringFormatInvalid")
    private fun share() {
        val intent = ShareCompat.IntentBuilder(this)
            .setType("text/plain")
            .setChooserTitle(getString(R.string.DescribeWebView))
//            .setText(getString(R.string.DescribeRichText))
            .intent

        startActivity(Intent.createChooser(intent, ""))
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit()
            true
        } else {
            super.onKeyDown(keyCode, event)
        }
    }

    private var mExitTime: Long = 0

    private fun exit() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            toast("再按一次退出应用")
            mExitTime = System.currentTimeMillis()
        } else {
            AppActivityManager.removeAllActivity()
        }
    }

    override fun onNetworkStateChanged(netState: NetWorkState) {
        super.onNetworkStateChanged(netState)
        if (netState.isSuccess) {
            Toasty.success("我特么来网了")
        } else {
            Toasty.error("我特么网丢了")
        }
    }

    override fun createObserver() {
        mViewModel.mList.observe(this) {
            Timber.e("MainActivity_65行_2022/3/24_16:32：${it}")
        }
    }


}