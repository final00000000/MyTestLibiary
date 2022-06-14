package com.zhang.mydemo.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.zhang.mydemo.base.activity.BaseActivity
import com.zhang.mydemo.databinding.ActivityTabViewPagerDeleteBinding
import com.zhang.mydemo.ui.adapter.TabPagerAdapter
import com.zhang.mydemo.ui.adapter.ViewPager1Adapter
import com.zhang.mydemo.ui.fragment.*
import com.zhang.utilslibiary.utils.TabViewPagerInit
import kotlinx.android.synthetic.main.activity_tab_view_pager_delete.*
import kotlinx.android.synthetic.main.layout_title.*

class TabLayoutViewPagerDeleteActivity : BaseActivity<ActivityTabViewPagerDeleteBinding>() {

    lateinit var tabPageAdapter: TabPagerAdapter

    var fmList: MutableList<Fragment> = mutableListOf(
        Test1Fragment.newInstance(),
        Test2Fragment.newInstance(),
        Test3Fragment.newInstance()
    )

    override fun initView(savedInstanceState: Bundle?) {
        tvPageTitle.text = "Tab+ViewPager"

        vp.adapter = ViewPager1Adapter(supportFragmentManager, fmList)

        vp.offscreenPageLimit = fmList.size

//        tabPageAdapter = TabPagerAdapter(this@TabLayoutViewPagerDeleteActivity, fmList)
//        vp.adapter = tabPageAdapter
//        TabViewPagerInit.TPinit(tl_tab, vp, 0)
        tl_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                vp.currentItem = tab!!.position

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                tl_tab.getTabAt(position)!!.select()
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
    }

    override fun initData() {

    }

    override fun setListener() {

    }

}