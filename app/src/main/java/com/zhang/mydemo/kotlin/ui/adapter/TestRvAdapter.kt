package com.zhang.mydemo.kotlin.ui.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zhang.mydemo.R
import com.zhang.mydemo.kotlin.model.bean.TestRVData
import com.zhang.utilslibiary.utils.GlideUtils

/**
 * @Author : zhang
 * @Create Time : 2021/11/25
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
class TestRvAdapter(data: MutableList<TestRVData>) :
    BaseQuickAdapter<TestRVData, BaseViewHolder>(R.layout.item_test_rv, data) {

    override fun convert(holder: BaseViewHolder, item: TestRVData) {
        GlideUtils.loadImageCorners(context,item.image,15,holder.getView(R.id.iv_bg))

        holder.getView<TextView>(R.id.tv_name).text = item.name
    }
}