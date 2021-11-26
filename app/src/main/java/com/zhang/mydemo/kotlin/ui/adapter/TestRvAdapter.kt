package com.zhang.mydemo.kotlin.ui.adapter

import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zhang.mydemo.R
import com.zhang.mydemo.kotlin.model.bean.TestRVData
import com.zhang.utilslibiary.utils.GlideUtils
import com.bumptech.glide.load.resource.bitmap.CenterCrop
/**
 * @Author : zhang
 * @Create Time : 2021/11/25
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
class TestRvAdapter(data: MutableList<TestRVData>) :
    BaseQuickAdapter<TestRVData, BaseViewHolder>(R.layout.item_test_rv, data) {

    override fun convert(holder: BaseViewHolder, item: TestRVData) {

        Glide.with(context)
            .load(item.image)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(15)))
            .into(holder.getView(R.id.iv_bg))

        holder.getView<TextView>(R.id.tv_name).text = item.name
    }
}