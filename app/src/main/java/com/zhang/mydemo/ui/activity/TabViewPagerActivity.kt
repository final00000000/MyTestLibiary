package com.zhang.mydemo.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.zhang.mydemo.base.activity.BaseActivity
import com.zhang.mydemo.databinding.ActivityTabViewPagerBinding
import com.zhang.mydemo.ui.adapter.TabPagerAdapter
import com.zhang.mydemo.ui.fragment.DiscoveryFragment
import com.zhang.mydemo.ui.fragment.HomeFragment
import com.zhang.mydemo.ui.fragment.MessageFragment
import com.zhang.mydemo.ui.fragment.MineFragment
import com.zhang.utilslibiary.utils.TabViewPagerInit
import kotlinx.android.synthetic.main.activity_tab_view_pager.tl_tab
import kotlinx.android.synthetic.main.activity_tab_view_pager.vp
import kotlinx.android.synthetic.main.layout_title.*


class TabViewPagerActivity : BaseActivity<ActivityTabViewPagerBinding>() {

    lateinit var tabPageAdapter: TabPagerAdapter

    // Fragment
    var fmList: MutableList<Fragment> = mutableListOf(
        HomeFragment.newInstance(),
        DiscoveryFragment.newInstance(),
        MessageFragment.newInstance(),
        MineFragment.newInstance()
    )

    override fun initData() {
    }

    override fun setListener() {
        tvPageTitle.text = "Tab+ViewPager"
        tabPageAdapter = TabPagerAdapter(this@TabViewPagerActivity, fmList)
        vp.adapter = tabPageAdapter
        TabViewPagerInit.TPinit(tl_tab, vp, 0)
    }

    override fun initView(savedInstanceState: Bundle?) {
        //添加Android自带的分割线
//        rv_tp.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

    }
}