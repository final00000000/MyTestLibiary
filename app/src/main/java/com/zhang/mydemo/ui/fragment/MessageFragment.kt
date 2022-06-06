package com.zhang.mydemo.ui.fragment

import android.os.Bundle
import com.zhang.mydemo.base.fragment.BaseNetWorkFragment
import com.zhang.mydemo.databinding.FragmentMessageBinding
import com.zhang.mydemo.viewmodel.MessageViewModel
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.fragment_message.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class MessageFragment : BaseNetWorkFragment<FragmentMessageBinding, MessageViewModel>() {

    companion object {
        @JvmStatic
        fun newInstance() = MessageFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun setListener() {
        messageTV.singleClick {
            CoroutineScope(Dispatchers.IO).launch {
                mViewModel.getCurrentLiveData()
                    .postValue(mViewModel.getCurrentLiveData().value!! + 1)
            }
        }
    }

    override fun createObserver() {
        mViewModel.getCurrentLiveData().observe(requireActivity()) {
            textTV.text = "LiveData 测试$it"
        }
    }
}