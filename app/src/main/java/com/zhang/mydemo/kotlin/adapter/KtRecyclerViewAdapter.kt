package com.zhang.mydemo.kotlin.adapter

import android.widget.CheckBox
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zhang.mydemo.R

/**
 * @Author : zhang
 * @Create Time : 2021/11/15 18:06
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
class KtRecyclerViewAdapter(data: MutableList<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.recycler_view_item, data) {

    override fun convert(holder: BaseViewHolder, item: String) {
        holder.getView<TextView>(R.id.tv).text = item
    }
}