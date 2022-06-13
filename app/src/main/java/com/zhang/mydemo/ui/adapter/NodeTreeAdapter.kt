package com.zhang.mydemo.ui.adapter

import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.zhang.mydemo.model.bean.FirstNode
import com.zhang.mydemo.model.bean.SecondNode

class NodeTreeAdapter : BaseNodeAdapter() {

    override fun getItemType(data: List<BaseNode>, position: Int): Int {
        val node = data[position]
        if (node is FirstNode) {
            return 1
        } else if (node is SecondNode) {
            return 2
        } /* else if (node instanceof ThirdNode) {
            return 3;
        }*/
        return -1
    }

    companion object {
        const val EXPAND_COLLAPSE_PAYLOAD = 110
    }

    init {
        addNodeProvider(FirstProvider())
        addNodeProvider(SecondProvider())
        //addNodeProvider(new ThirdProvider());
    }
}