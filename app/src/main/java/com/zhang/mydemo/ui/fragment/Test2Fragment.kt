package com.zhang.mydemo.ui.fragment

import android.graphics.Canvas
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.listener.OnItemSwipeListener
import com.zhang.mydemo.R
import com.zhang.mydemo.base.fragment.BaseFragment
import com.zhang.mydemo.databinding.FragmentTest2Binding
import com.zhang.mydemo.ui.activity.RecyclerViewExpandListActivity
import com.zhang.mydemo.ui.adapter.RecyclerViewDraggableAdapter
import com.zhang.utilslibiary.utils.toast.Toasty
import kotlinx.android.synthetic.main.activity_recycler_view_draggable.*
import org.jetbrains.anko.support.v4.startActivity
import timber.log.Timber

class Test2Fragment : BaseFragment<FragmentTest2Binding>() {

    companion object {
        @JvmStatic
        fun newInstance() =
            Test2Fragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    var rAdapter = RecyclerViewDraggableAdapter(mutableListOf("张三1", "张三2", "张三3", "张三4", "张三5"))

    override fun initView() {
        draggableRv.adapter = rAdapter
        rAdapter.setOnItemClickListener { adapter, view, position ->
            startActivity<RecyclerViewExpandListActivity>()
        }

        rAdapter.addChildClickViewIds(R.id.btnDelete)
        rAdapter.setOnItemChildClickListener { adapter, view, position ->
            Toasty.error("position")
        }

        rAdapter.draggableModule.apply {
//            isDragEnabled = true
//            isSwipeEnabled = true
//            setOnItemDragListener(draggableListener)
//            setOnItemSwipeListener(swipeListener)
        }
    }

    override fun initData() {

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
                    requireActivity(),
                    R.color.color00DDB6
                )
            )
        }

    }

    override fun setListener() {

    }
}


