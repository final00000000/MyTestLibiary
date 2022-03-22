package com.zhang.mydemo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zhang.mydemo.R
import com.zhang.mydemo.ui.adapter.HorizontalProgressListAdapter.OrderProgressViewHolder
import com.zhang.mydemo.ui.widget.Node

/**
 * Description :取消订单处理进度
 * Author : Liun
 * Date   : 2016 16/9/7 15:39.
 * Email  : liun_coolman@foxmail.com
 */
class HorizontalProgressListAdapter(private val list: List<Node>?) :
    RecyclerView.Adapter<OrderProgressViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderProgressViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.node_item,
            parent,
            false
        )
        return OrderProgressViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: OrderProgressViewHolder, position: Int) {
        val node = list!![position]
        if (position == 0) {
            // 第一个节点:隐藏line
            holder.leftLine.visibility = View.INVISIBLE
        }
        if (position == list.size - 1) {
            // 最后一个隐藏line
            holder.rightLine.visibility = View.INVISIBLE
            holder.middleLine.visibility = View.INVISIBLE
        }
        if (node.nodeStatus == 0) {
            // 已处理
            holder.nodeImage.setBackgroundResource(R.mipmap.progress_finish)
            holder.rightLine.setBackgroundResource(R.color.red)
        } else if (node.nodeStatus == 1) {
            // 正在处理
            holder.nodeImage.setBackgroundResource(R.mipmap.progress_select)
            holder.rightLine.setBackgroundResource(R.color.grey)
            holder.middleLine.setBackgroundResource(R.color.grey)
        } else if (node.nodeStatus == -1) {
            // 待处理
            holder.nodeImage.setBackgroundResource(R.mipmap.progress_unselect)
            holder.leftLine.setBackgroundResource(R.color.grey)
            holder.rightLine.setBackgroundResource(R.color.grey)
            holder.middleLine.setBackgroundResource(R.color.grey)
        }
        holder.nodeName.text = node.nodeName
    }

    inner class OrderProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var leftLine: TextView
        var rightLine: TextView
        var middleLine: TextView
        var nodeName: TextView
        var nodeImage: ImageView

        init {

            // 节点左边的线
            leftLine = itemView.findViewById<View>(R.id.left_line) as TextView
            // 节点右边的线
            rightLine = itemView.findViewById<View>(R.id.right_line) as TextView
            // 节点与节点中间的线
            middleLine = itemView.findViewById<View>(R.id.middle_line) as TextView
            // 节点下面文字
            nodeName = itemView.findViewById<View>(R.id.nodeName) as TextView
            // 节点图片
            nodeImage = itemView.findViewById<View>(R.id.image) as ImageView
        }
    }
}