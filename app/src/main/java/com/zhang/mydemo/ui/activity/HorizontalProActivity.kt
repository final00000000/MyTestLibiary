package com.zhang.mydemo.ui.activity

import com.zhang.mydemo.base.BaseActivity
import com.zhang.mydemo.databinding.ActivityHorizontalProBinding
import com.zhang.mydemo.ui.adapter.HorizontalProgressListAdapter
import com.zhang.mydemo.ui.widget.Node
import kotlinx.android.synthetic.main.activity_horizontal_pro.*
import kotlinx.android.synthetic.main.layout_title.*
import java.util.*

class HorizontalProActivity : BaseActivity<ActivityHorizontalProBinding>() {

    override fun initView() {
        tvPageTitle.text = "横向物流轴"
        recyclerView.adapter =
            HorizontalProgressListAdapter(
                progressList
            )
    }

    /**
     * 模拟节点数据
     * node1.nodeStatus: 0 已完成状态  1正在处理状态  -1待处理状态
     *
     * @return
     */
    private val progressList: List<Node>
        private get() {
            val list: MutableList<Node> = ArrayList()
            val node1 = Node()
            val node2 = Node()
            val node3 = Node()
            val node4 = Node()
            val node5 = Node()
            node1.nodeName = "提交申请"
            node1.nodeStatus = 0
            node2.nodeName = "等待回寄"
            node2.nodeStatus = 0
            node3.nodeName = "已回寄"
            node3.nodeStatus = 0
            node4.nodeName = "等待退款"
            node4.nodeStatus = 1
            node5.nodeName = "完成"
            node5.nodeStatus = -1
            list.add(node1)
            list.add(node2)
            list.add(node3)
            list.add(node4)
            list.add(node5)
            return list
        }

    override fun initData() {}
    override fun setListener() {}
}