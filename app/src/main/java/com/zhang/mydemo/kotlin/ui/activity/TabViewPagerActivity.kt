package com.zhang.mydemo.kotlin.ui.activity

import com.zhang.mydemo.R
import com.zhang.mydemo.base.BaseActivity
import com.zhang.mydemo.kotlin.ui.adapter.TabViewPagerAdapter
import kotlinx.android.synthetic.main.activity_tab_view_pager.*
import androidx.recyclerview.widget.DividerItemDecoration
import com.zhang.mydemo.kotlin.ui.activity.tab.Tab1Activity
import com.zhang.mydemo.kotlin.ui.activity.tab.TabActivity
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.startActivity


class TabViewPagerActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_tab_view_pager

    lateinit var rvTpAdapter: TabViewPagerAdapter
    var mData = mutableListOf<String>()

    override fun initView() {
        addData()
        rvTpAdapter = TabViewPagerAdapter(mData)
        rv_tp.adapter = rvTpAdapter
        //添加Android自带的分割线
        rv_tp.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        rvTpAdapter.setOnItemClickListener { adapter, view, position ->
            when (position) {
                0 -> startActivity<TabActivity>()
                1 ->startActivity<Tab1Activity>()
            }
        }
    }

    fun addData() {
        mData.add("原生Tab+ViewPager")
        mData.add("MagicIndicator+ViewPager")
    }

    override fun initData() {
    }

    override fun setListener() {
        tvPageTitle.text = "Tab+ViewPager"
    }
}