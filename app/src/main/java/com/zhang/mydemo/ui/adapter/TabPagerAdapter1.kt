package com.zhang.mydemo.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @Author : zhang
 * @Create Time : 2022/2/9
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
class TabPagerAdapter1(fragmentManager: FragmentManager, var list: MutableList<Fragment>) :
    FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Fragment = list[position]

}