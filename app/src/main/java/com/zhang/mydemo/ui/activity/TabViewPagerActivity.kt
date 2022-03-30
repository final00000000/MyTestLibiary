package com.zhang.mydemo.ui.activity

import androidx.fragment.app.Fragment
import com.zhang.mydemo.base.activity.BaseActivity
import com.zhang.mydemo.databinding.ActivityTabViewPagerBinding
import com.zhang.mydemo.ui.adapter.TabPagerAdapter
import com.zhang.mydemo.ui.fragment.TestFragment1
import com.zhang.mydemo.ui.fragment.TestFragment2
import com.zhang.mydemo.ui.fragment.TestFragment3
import com.zhang.mydemo.ui.fragment.TestFragment4
import com.zhang.utilslibiary.utils.TabViewPagerInit
import kotlinx.android.synthetic.main.activity_tab_view_pager.*
import kotlinx.android.synthetic.main.activity_tab_view_pager.tl_tab
import kotlinx.android.synthetic.main.activity_tab_view_pager.vp
import kotlinx.android.synthetic.main.layout_title.*


class TabViewPagerActivity : BaseActivity<ActivityTabViewPagerBinding>() {

    lateinit var tabPageAdapter: TabPagerAdapter

    // Fragment
    var fmList: MutableList<Fragment> = mutableListOf(
        TestFragment1.newInstance(1),
        TestFragment2.newInstance(2),
        TestFragment3.newInstance(3),
        TestFragment4.newInstance(4)
    )

    override fun initView() {
        //添加Android自带的分割线
//        rv_tp.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

    }

    override fun initData() {
    }

    override fun setListener() {
        tvPageTitle.text = "Tab+ViewPager"
        tabPageAdapter = TabPagerAdapter(this@TabViewPagerActivity, fmList)
        vp.adapter = tabPageAdapter
        TabViewPagerInit.TPinit(tl_tab, vp, 0)
    }
}