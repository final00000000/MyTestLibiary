package com.zhang.mydemo.kotlin.RecyclerView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elvishew.xlog.XLog
import com.zhang.mydemo.R
import com.zhang.mydemo.kotlin.adapter.KtRecyclerViewAdapter
import com.zhang.mydemo.kotlin.utils.singleClick
import kotlinx.android.synthetic.main.activity_recycler_view.*
import org.jetbrains.anko.toast

class RecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        addData()
        initView()
    }

    lateinit var rvAdapter: KtRecyclerViewAdapter

    var mutableList: MutableList<String> = mutableListOf()

    fun initView() {
        rvAdapter = KtRecyclerViewAdapter(mutableListOf())
        rv.adapter = rvAdapter
        rvAdapter.setNewInstance(mutableList)

        rvAdapter.setOnItemClickListener { adapter, view, position ->
            XLog.e("点击了数据--- $position  ${adapter.getItem(position) as String}")
        }
        get_data.singleClick {
            toast("${rvAdapter.data}")
        }



    }
    fun addData() {
        for (i in 0..50) {
            mutableList.add("这是第${i}个数据")
        }
    }

}