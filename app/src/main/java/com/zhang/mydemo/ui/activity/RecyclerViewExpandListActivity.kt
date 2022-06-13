package com.zhang.mydemo.ui.activity

import android.os.Bundle
import com.chad.library.adapter.base.entity.node.BaseNode
import com.zhang.mydemo.base.activity.BaseActivity
import com.zhang.mydemo.databinding.ActivityRecyclerViewExpandListBinding
import com.zhang.mydemo.model.bean.FirstNode
import com.zhang.mydemo.model.bean.SecondNode
import com.zhang.mydemo.ui.adapter.NodeTreeAdapter
import com.zhang.utilslibiary.utils.toast.Toasty
import kotlinx.android.synthetic.main.activity_recycler_view_draggable.*

class RecyclerViewExpandListActivity : BaseActivity<ActivityRecyclerViewExpandListBinding>() {


//    var rAdapter = RecyclerViewExpandListAdapter()

    val adapter = NodeTreeAdapter()
    override fun initView(savedInstanceState: Bundle?) {
        draggableRv.adapter = adapter

        adapter.setList(getEntity())


//        adapter.setOnItemClickListener { adapter, view, position ->
//            Toasty.success("$position")
//        }
    }

    private fun getEntity(): List<BaseNode> {
        val list: MutableList<BaseNode> = ArrayList()
        for (i in 0..7) {
            val secondNodeList: MutableList<BaseNode> = ArrayList()
            for (n in 0..5) {
//                val thirdNodeList: MutableList<BaseNode> = ArrayList()
                val seNode = SecondNode( "Second Node $n")
                secondNodeList.add(seNode)
            }
            val entity = FirstNode(secondNodeList, "First Node $i")

            // 模拟 默认第0个是展开的
//            entity.isExpanded(i == 0)
//            entity.isExpanded = true
            list.add(entity)
        }
        return list
    }

    override fun initData() {

    }

    override fun setListener() {
    }
}