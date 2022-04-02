package com.zhang.mydemo

import com.zhang.mydemo.base.activity.BaseActivity
import com.zhang.mydemo.base.manager.NetWorkState
import com.zhang.mydemo.databinding.ActivityKotlinBinding
import com.zhang.mydemo.ui.activity.*
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_kotlin.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.startActivity

class KotlinActivity : BaseActivity<ActivityKotlinBinding>() {

    override fun initView() {
        ivPageBack.singleClick { killMyself() }
        tvPageTitle.text = "Kotlin"
    }

    override fun initData() {
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
        tv_13.singleClick {
            startActivity<VerificationActivity>()
        }
        tv_14.singleClick {
            startActivity<SayWhatActivity>()
        }
        tv_15.singleClick {
            startActivity<ViewWorldActivity>()
        }
        tv_16.singleClick {
            startActivity<DateUtilsActivity>()
        }
        tv_17.singleClick {
            startActivity<JumpTextActivity>()
        }
        tv_18.singleClick {
            startActivity<TestSurfaceViewActivity>()
        }
        tv_19.singleClick {
            startActivity<HorizontalProActivity>()
        }
        tv_20.singleClick {
            startActivity<BRVActivity>()
        }
        tv_21.singleClick {
            startActivity<ExpandTextViewActivity>()
        }
    }
}