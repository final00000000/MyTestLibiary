package com.zhang.mydemo.kotlin.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @Author : zhang
 * @Create Time : 2022/2/9
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
class TabPagerAdapter(fragmentActivity: FragmentActivity, var list: MutableList<Fragment>) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment = list[position]
}