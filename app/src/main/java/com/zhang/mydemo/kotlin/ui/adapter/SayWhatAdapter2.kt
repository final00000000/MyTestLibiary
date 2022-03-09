package com.zhang.mydemo.kotlin.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zhang.mydemo.R

/**
 * @Author : zhang
 * @Create Time : 2022/3/1
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
class SayWhatAdapter2(data: MutableList<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_say_what, data) {


    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tv_plate, item)
    }

    override fun convert(holder: BaseViewHolder, item: String, payloads: List<Any>) {
        if (payloads.isEmpty()) {
            convert(holder, item)
        } else {
            val r = payloads[0] as Boolean
            if (r) {
                holder.setVisible(R.id.v_indicate, true)
            } else {
                holder.setVisible(R.id.v_indicate, false)
            }
        }
    }
}
