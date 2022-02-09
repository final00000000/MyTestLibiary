package com.zhang.mydemo.kotlin.ui.activity.tab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.zhang.mydemo.R
import com.zhang.mydemo.base.BaseActivity
import com.zhang.mydemo.kotlin.ui.adapter.TabPagerAdapter
import com.zhang.mydemo.kotlin.ui.fragment.TestFragment1
import com.zhang.mydemo.kotlin.ui.fragment.TestFragment2
import com.zhang.mydemo.kotlin.ui.fragment.TestFragment3
import com.zhang.mydemo.kotlin.ui.fragment.TestFragment4
import com.zhang.utilslibiary.utils.TabViewPagerInit
import kotlinx.android.synthetic.main.activity_tab.*

class TabActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_tab

    var fmList: MutableList<Fragment> = mutableListOf(
        TestFragment1.newInstance(1),
        TestFragment2.newInstance(2),
        TestFragment3.newInstance(3),
        TestFragment4.newInstance(4)
    )

    override fun initView() {
    }

    override fun initData() {
    }

    // Fragment
    lateinit var tabPageAdapter: TabPagerAdapter
    override fun setListener() {
        tabPageAdapter = TabPagerAdapter(this@TabActivity, fmList)
        vp.adapter = tabPageAdapter
        TabViewPagerInit.TPinit(tl_tab, vp, 0)
    }
}