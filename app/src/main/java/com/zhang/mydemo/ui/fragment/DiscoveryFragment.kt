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
        mViewBinding.apply {
            tv01.singleClick {
                startActivity<WebViewActivity>(
                    "url" to "https://www.baidu.com/"
                )
            }
            tv02.singleClick {
                startActivity<KeyBoard>()
            }
            tv03.singleClick {
                startActivity<RichTextActivity>()
            }
            tv04.singleClick {
                startActivity<PopUpActivity>()
            }
            tv05.singleClick {
                startActivity<TestGlideActivity>()
            }
            tv06.singleClick {
                startActivity<PickViewActivity>()
            }
            tv07.singleClick {
                startActivity<TestTag>()
            }
            tv08.singleClick {
                startActivity<JumpActivity>()
            }
            tv10.singleClick {
                startActivity<GrammarActivity>()
            }
            tv11.singleClick {
                startActivity<TabViewPagerActivity>()
            }
            tv12.singleClick {
                startActivity<MMKVActivity>()
            }
            tv14.singleClick {
                startActivity<SayWhatActivity>()
            }
            tv15.singleClick {
                startActivity<ViewWorldActivity>()
            }
            tv16.singleClick {
                startActivity<DateUtilsActivity>()
            }
            tv17.singleClick {
                startActivity<JumpTextActivity>()
            }

            tv18.singleClick {
                startActivity<TestSurfaceViewActivity>()
            }

            tv19.singleClick {
                startActivity<HorizontalProActivity>()
            }

            tv21.singleClick {
                startActivity<ExpandTextViewActivity>()
            }

            tv22.singleClick {
                startActivity<LifeCycleActivity>()
            }

            tv23.singleClick {
                startActivity<SaveImageActivity>()
            }
        }
    }
}