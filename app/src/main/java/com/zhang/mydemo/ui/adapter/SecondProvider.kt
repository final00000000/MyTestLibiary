package com.zhang.mydemo.ui.adapter

import android.view.View
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zhang.mydemo.R
import com.zhang.mydemo.model.bean.SecondNode

class SecondProvider : BaseNodeProvider() {
    override val itemViewType: Int
        get() = 2
    override val layoutId: Int
        get() = R.layout.item_node_second

    override fun convert(helper: BaseViewHolder, data: BaseNode) {
        val entity: SecondNode = data as SecondNode
        helper.setText(R.id.title, entity.getTitle())
//        if (entity.isExpanded) {
//            helper.setImageResource(R.id.iv, R.drawable.arrow_b)
//        } else {
//            helper.setImageResource(R.id.iv, R.drawable.arrow_r)
//        }
    }

    override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
        val entity: SecondNode = data as SecondNode
//        if (entity.isExpanded) {
//            getAdapter()!!.collapse(position)
//        } else {
//            getAdapter()!!.expandAndCollapseOther(position)
//        }
    }
}