package com.zhang.mydemo.ui.fragment

import android.os.Bundle
import com.zhang.mydemo.base.fragment.BaseFragment
import com.zhang.mydemo.databinding.FragmentDiscoveryBinding
import com.zhang.mydemo.ui.activity.*
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.fragment_discovery.*
import org.jetbrains.anko.support.v4.startActivity

class DiscoveryFragment : BaseFragment<FragmentDiscoveryBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = DiscoveryFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }

    override fun initView() {

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
        tv_10.singleClick {
            startActivity<GrammarActivity>()
        }
        tv_11.singleClick {
            startActivity<TabViewPagerActivity>()
        }
        tv_12.singleClick {
            startActivity<MMKVActivity>()
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
        tv_21.singleClick {
            startActivity<ExpandTextViewActivity>()
        }
        tv_22.singleClick {
            startActivity<LifeCycleActivity>()
        }
        tv_23.singleClick {
            startActivity<SaveImageActivity>()
        }
    }
}