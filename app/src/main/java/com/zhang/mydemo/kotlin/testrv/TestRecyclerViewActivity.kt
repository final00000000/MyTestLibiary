package com.zhang.mydemo.kotlin.testrv

import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhang.mydemo.R
import com.zhang.mydemo.base.BaseActivity
import com.zhang.mydemo.kotlin.adapter.TestRvAdapter
import com.zhang.mydemo.kotlin.model.Data.TestRVData
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_test_recycler_view.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

class TestRecyclerViewActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_test_recycler_view

    lateinit var tAdapter: TestRvAdapter

    var list = mutableListOf<TestRVData>()

    override fun initView() {
        ivPageBack.singleClick { killMyself() }
        tvPageTitle.text = "RecyclerView练习"
    }

    override fun initData() {
        addData()
        tAdapter = TestRvAdapter(mutableListOf())
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = layoutManager.orientation
        testRV.layoutManager = layoutManager
        testRV.adapter = tAdapter

        val Header = layoutInflater.inflate(R.layout.item_test_rv_header, null)
        Header.find<TextView>(R.id.tvHeader).singleClick { toast("头部") }
        tAdapter.addHeaderView(Header)

        val Footer = layoutInflater.inflate(R.layout.item_test_rv_footer, null)
        Footer.find<TextView>(R.id.tvFooter).singleClick { toast("尾部") }
        tAdapter.addFooterView(Footer)

        tAdapter.setNewInstance(list)

    }

    fun addData() {
        for (i in 0..10) {
            list.add(TestRVData("这是第${i}个张三",
                "https://cdn.pixabay.com/photo/2020/06/10/02/22/caricature-5280770_960_720.jpg"))
        }
    }

    override fun setListener() {

    }
}