package com.zhang.mydemo.ui.activity

import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.listener.OnItemDragListener
import com.chad.library.adapter.base.listener.OnItemSwipeListener
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zhang.mydemo.R
import com.zhang.mydemo.base.activity.BaseActivity
import com.zhang.mydemo.databinding.ActivityRecyclerViewDraggableBinding
import com.zhang.mydemo.ui.adapter.RecyclerViewDraggableAdapter
import com.zhang.utilslibiary.utils.toast.Toasty
import kotlinx.android.synthetic.main.activity_recycler_view_draggable.*
import timber.log.Timber

class RecyclerViewDraggableActivity : BaseActivity<ActivityRecyclerViewDraggableBinding>() {

    var rAdapter = RecyclerViewDraggableAdapter(mutableListOf("张三1", "张三2", "张三3", "张三4", "张三5"))

    override fun initView(savedInstanceState: Bundle?) {
        draggableRv.adapter = rAdapter
        rAdapter.draggableModule.apply {
            isDragEnabled = true
            isSwipeEnabled = true
            setOnItemDragListener(draggableListener)
            setOnItemSwipeListener(swipeListener)
        }

    }

    // 侧滑监听
    private var swipeListener: OnItemSwipeListener = object : OnItemSwipeListener {
        override fun onItemSwipeStart(viewHolder: RecyclerView.ViewHolder, pos: Int) {
            Timber.e("RecyclerViewDraggableActivity onItemDragMoving $pos ")
        }

        override fun clearView(viewHolder: RecyclerView.ViewHolder, pos: Int) {
        }

        override fun onItemSwiped(viewHolder: RecyclerView.ViewHolder, pos: Int) {
            Timber.e("RecyclerViewDraggableActivity onItemSwiped ${rAdapter.data}")

        }

        override fun onItemSwipeMoving(
            canvas: Canvas, viewHolder: RecyclerView.ViewHolder, dX: Float,
            dY: Float, isCurrentlyActive: Boolean
        ) {
            canvas.drawColor(
                ContextCompat.getColor(
                    this@RecyclerViewDraggableActivity,
                    R.color.color00DDB6
                )
            )
        }
    }

    // 拖拽监听
    private var draggableListener: OnItemDragListener = object : OnItemDragListener {
        override fun onItemDragStart(viewHolder: RecyclerView.ViewHolder, pos: Int) {
            val holder = viewHolder as BaseViewHolder

            // 开始时，item背景色变化，demo这里使用了一个动画渐变，使得自然
            val startColor = Color.WHITE
            val endColor = Color.rgb(245, 245, 245)
            val v = ValueAnimator.ofArgb(startColor, endColor)
            v.addUpdateListener { animation -> holder.itemView.setBackgroundColor(animation.animatedValue as Int) }
            v.duration = 300
            v.start()
        }

        override fun onItemDragMoving(
            source: RecyclerView.ViewHolder, from: Int, target: RecyclerView.ViewHolder, to: Int
        ) {
            Timber.e("RecyclerViewDraggableActivity onItemDragMoving ${source.layoutPosition} ${target.layoutPosition}")
        }

        override fun onItemDragEnd(viewHolder: RecyclerView.ViewHolder, pos: Int) {
            Timber.e("RecyclerViewDraggableActivity onItemDragEnd  ${rAdapter.data} ")
            val holder = viewHolder as BaseViewHolder
            // 结束时，item背景色变化，demo这里使用了一个动画渐变，使得自然
            val startColor = Color.rgb(245, 245, 245)
            val endColor = Color.WHITE
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val v = ValueAnimator.ofArgb(startColor, endColor)
            v.addUpdateListener { animation -> holder.itemView.setBackgroundColor(animation.animatedValue as Int) }
            v.duration = 300
            v.start()
//            }
        }
    }

    override fun initData() {

    }

    override fun setListener() {
    }

}