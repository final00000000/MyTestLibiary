package com.zhang.utilslibiary.utils

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

/**
 * @Author : zhang
 * @Create Time : 2022/2/9
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
object TabViewPagerInit {
    fun TPinit(tl_tab: TabLayout, vp: ViewPager2,position : Int) {
        //tab 联动 vp
        tl_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                vp.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        //vp滑动监听
        vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tl_tab.getTabAt(position)!!.select()
            }
        })
        vp.currentItem = position
    }
}
