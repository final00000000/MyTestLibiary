package com.zhang.mydemo.ui.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zhang.mydemo.MainActivity
import com.zhang.mydemo.R
import com.zhang.utilslibiary.utils.getColorRes
import com.zhang.utilslibiary.utils.strToInt
import org.jetbrains.anko.textColor

/**
 * @Author : zhang
 * @Create Time : 2022/4/7
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
class NavigationAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_navigation, mutableListOf()) {

    var mPosition = 0
    override fun convert(holder: BaseViewHolder, item: String) {
        val tvTitle = holder.getView<TextView>(R.id.tvTitle)
        tvTitle.text = item
        mPosition = holder.layoutPosition
        if (mPosition == holder.layoutPosition) {
            tvTitle.textColor = (context as MainActivity).getColorRes(R.color.color3D3D3D)
//            tvTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(0, item.strToInt(), 0, 0)
        } else {
            tvTitle.textColor = (context as MainActivity).getColorRes(R.color.colorBBBBBB)
//            tvTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(0, item.strToInt(), 0, 0)
        }
    }
}