package com.zhang.mydemo.java.starbar

import android.view.View
import android.widget.TextView
import com.zhang.mydemo.R
import com.zhang.mydemo.base.BaseActivity
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_star_rating.*
import org.jetbrains.anko.toast

class StarRatingActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_star_rating
    }

    override fun initView() {
        findViewById<View>(R.id.ivPageBack).setOnClickListener { v: View? -> killMyself() }
        val tvPageTitle = findViewById<TextView>(R.id.tvPageTitle)
        tvPageTitle.text = "评分工具类"
    }

    override fun initData() {
    }

    override fun setListener() {
        btn.singleClick {
            toast("${sbv_starbar.starRating}")
        }
    }
}