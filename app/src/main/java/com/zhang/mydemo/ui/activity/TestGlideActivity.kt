package com.zhang.mydemo.ui.activity

import android.os.Bundle
import com.zhang.mydemo.R
import com.zhang.mydemo.base.activity.BaseActivity
import com.zhang.mydemo.databinding.ActivityTestGlideBinding
import com.zhang.utilslibiary.utils.GlideUtils
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_test_glide.*
import kotlinx.android.synthetic.main.layout_title.*

class TestGlideActivity : BaseActivity<ActivityTestGlideBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        tvPageTitle.text = "测试Glide"
    }

    override fun initData() {

    }

    override fun setListener() {
        tv1.singleClick {
            GlideUtils.loadImage(
                this,
                "https://cdn.pixabay.com/photo/2021/11/17/15/07/paris-6803796_960_720.jpg",
                iv_bg1
            )
        }
        tv2.singleClick {
            GlideUtils.loadImage(this, "", R.drawable.ic_pl, iv_bg2)
        }
        tv3.singleClick {
            /*GlideUtils.loadImageError(
                this,
                "",
                "https://cdn.pixabay.com/photo/2021/11/15/05/52/red-fox-6796430_960_720.jpg",
                iv_bg3
            )*/
        }
        tv4.singleClick {
            GlideUtils.loadImageGif(
                this,
                "https://pic2.zhimg.com/v2-04f41cb0fcbcdd5b0b69a587f34ccd89_b.webp",
                iv_bg4
            )
        }
        tv5.singleClick {
            GlideUtils.loadImageRound(
                this,
                "https://cdn.pixabay.com/photo/2021/11/12/18/13/greenfinch-6789772_960_720.jpg",
                iv_bg5
            )
        }
        tv6.singleClick {
            GlideUtils.loadImageCorners(
                this,
                "https://cdn.pixabay.com/photo/2021/10/20/14/01/bull-6726185_960_720.jpg",
                30,
                iv_bg6
            )
        }
    }
}