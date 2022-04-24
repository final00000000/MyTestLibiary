package com.zhang.mydemo.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @Author : zhang
 * @Create Time : 2022/4/14
 * @Class Describe : 描述
 * @Project Name : supero
 */
//viewLifecycleOwner.lifecycle
class ViewPager2Adapter(fragmentActivity: FragmentActivity, var list: MutableList<Fragment>) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment = list[position]
}