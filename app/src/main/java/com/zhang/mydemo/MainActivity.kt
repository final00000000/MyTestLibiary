package com.zhang.mydemo

import android.os.Bundle
import android.view.KeyEvent
import com.zhang.mydemo.adapter.NavigationAdapter
import com.zhang.mydemo.adapter.ViewPager2Adapter
import com.zhang.mydemo.base.activity.BaseNetWorkActivity
import com.zhang.mydemo.base.manager.NetWorkState
import com.zhang.mydemo.databinding.ActivityMainBinding
import com.zhang.mydemo.ui.fragment.DiscoveryFragment
import com.zhang.mydemo.ui.fragment.HomeFragment
import com.zhang.mydemo.ui.fragment.MessageFragment
import com.zhang.mydemo.ui.fragment.MineFragment
import com.zhang.mydemo.viewmodel.MainViewModel
import com.zhang.utilslibiary.utils.AppActivityManager
import com.zhang.utilslibiary.utils.getDrawableRes
import com.zhang.utilslibiary.utils.toast.Toasty
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import timber.log.Timber
import java.util.*

class MainActivity : BaseNetWorkActivity<ActivityMainBinding, MainViewModel>(),
    NavigationAdapter.OnNavigationListener {

    override fun isLayoutToolbar(): Boolean = false

    var fragmentList = mutableListOf(
        HomeFragment.newInstance(),
        DiscoveryFragment.newInstance(),
        MessageFragment.newInstance(),
        MineFragment.newInstance()
    )

    lateinit var adapter: NavigationAdapter

    override fun initView(savedInstanceState: Bundle?) {
        initIndicator()
    }

    private fun initIndicator() {

        adapter = NavigationAdapter(this).apply {
            addItem(NavigationAdapter.MenuItem("首页", getDrawableRes(R.drawable.main_home_selector)))
            addItem(
                NavigationAdapter.MenuItem(
                    "发现",
                    getDrawableRes(R.drawable.main_discovery_selector)
                )
            )

            addItem(
                NavigationAdapter.MenuItem(
                    "消息",
                    getDrawableRes(R.drawable.main_message_selector)
                )
            )
            addItem(NavigationAdapter.MenuItem("我的", getDrawableRes(R.drawable.main_mine_selector)))
            setOnNavigationListener(this@MainActivity)
            mainNavigationRv.adapter = this
        }

        val viewPager2Adapter = ViewPager2Adapter(this, fragmentList)
        mainViewPager.isUserInputEnabled = false
        mainViewPager.adapter = viewPager2Adapter
    }

    override fun setListener() {
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

    override fun onNavigationItemSelected(position: Int): Boolean {
        return when (position) {
            0, 1, 2, 3 -> {
                mainViewPager.currentItem = position
                true
            }
            else -> false
        }
    }


}