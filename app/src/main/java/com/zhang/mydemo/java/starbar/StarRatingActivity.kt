package com.zhang.mydemo.java.starbar

import android.os.Bundle
import com.zhang.kotlindemo.base.BaseActivity
import com.zhang.mydemo.R
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_star_rating.*
import org.jetbrains.anko.toast

class StarRatingActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_star_rating)
        btn.singleClick {
            toast("${sbv_starbar.starRating}")
        }
    }
}