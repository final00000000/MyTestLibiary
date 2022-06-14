package com.zhang.mydemo.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * @Author : zhang
 * @Create Time : 2022/4/14
 * @Class Describe : 描述
 * @Project Name : supero
 */
class ViewPager1Adapter(fragmentManager: FragmentManager, var list: MutableList<Fragment>) :
    FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int = list.size


    override fun getItem(position: Int): Fragment = list[position]
}