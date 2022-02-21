package com.zhang.mydemo.kotlin.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zhang.mydemo.R

/**
 * @Author : zhang
 * @Create Time : 2022/2/21
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
class VerificationAdapter(data: MutableList<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_verification, data) {

    override fun convert(holder: BaseViewHolder, item: String) {

    }
}