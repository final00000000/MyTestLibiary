package com.zhang.mydemo.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.DraggableModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zhang.mydemo.R

/**
 * @Author : zhang
 * @Create Time : 2022/6/6
 * @Class Describe :  项目描述
 * @Project Name : MyDemo
 */
class RecyclerViewDraggableAdapter(data: MutableList<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_recyclerview_draggable, data),DraggableModule{

    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tv,item)
    }
}