package com.zhang.mydemo.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zhang.mydemo.ExpandableTextView
import com.zhang.mydemo.R
import timber.log.Timber


/**
 * @Author : zhang
 * @Create Time : 2022/4/2
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
class ExpandAdapter(data: MutableList<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_shrink, data) {

    override fun convert(holder: BaseViewHolder, item: String) {
//        holder.setText(R.id.expand_text_view, item)
        val view = holder.getView<ExpandableTextView>(R.id.expand_text_view)
        view.text = item

        view.setOnExpandStateChangeListener { textView, isExpanded ->
            Timber.e("ExpandAdapter_36行_2022/4/2_11:49：${textView.text} $isExpanded")
        }
    }
}