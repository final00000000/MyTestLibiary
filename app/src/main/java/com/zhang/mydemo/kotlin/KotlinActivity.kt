package com.zhang.mydemo.kotlin

import com.zhang.mydemo.R
import com.zhang.mydemo.base.BaseActivity
import com.zhang.mydemo.kotlin.ui.activity.*
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_kotlin.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.startActivity

class KotlinActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_kotlin

    override fun initView() {
        ivPageBack.singleClick { killMyself() }
        tvPageTitle.text = "Kotlin"
    }

    override fun initData() {
/*  val networkConnected = IsNetwork.isNetworkConnected(this)
        if (networkConnected) {
            toast("有网络")
        } else {
            toast("没有网络")
        }*/
    }

    override fun setListener() {
        tv_01.singleClick {
            startActivity<WebViewActivity>(
                "url" to "https://www.baidu.com/"
            )
        }
        tv_02.singleClick {
            startActivity<KeyBoard>()
        }
        tv_03.singleClick {
            startActivity<RichTextActivity>()
        }
        tv_04.singleClick {
            startActivity<PopUpActivity>()
        }
        tv_05.singleClick {
            startActivity<TestGlideActivity>()
        }
        tv_06.singleClick {
            startActivity<PickViewActivity>()
        }
        tv_07.singleClick {
            startActivity<TestTag>()
        }
        tv_08.singleClick {
            startActivity<JumpActivity>()
        }
        tv_09.singleClick {
            startActivity<MotionLayoutyActivity>()
        }
        tv_10.singleClick {
            startActivity<GrammarActivity>()
        }
        tv_11.singleClick {
            startActivity<TabViewPagerActivity>()
        }
        tv_12.singleClick {
            startActivity<MMKVActivity>()
        }
    }
}