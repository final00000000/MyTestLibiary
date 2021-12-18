package com.zhang.mydemo.kotlin.ui.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zhang.mydemo.R
import com.zhang.mydemo.kotlin.model.bean.User
import com.zhang.mydemo.kotlin.ui.widgetkt.HighLightTextView
import com.zhang.utilslibiary.utils.GlideUtils

/**
 * @Author : zhang
 * @Create Time : 2021/12/18
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
class SearchAdapter(data: MutableList<User>) :
    BaseQuickAdapter<User, BaseViewHolder>(R.layout.item_rv_search, data) {

    override fun convert(holder: BaseViewHolder, item: User) {

        holder.getView<HighLightTextView>(R.id.tv_name).highLightText = item.name
        holder.setText(R.id.tv_name, item.name)

        GlideUtils.loadImage(context, item.icon, holder.getView(R.id.iv_icon))
    }
}