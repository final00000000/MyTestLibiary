package com.zhang.mydemo.ui.activity

import com.zhang.mydemo.base.BaseActivity
import com.zhang.mydemo.databinding.ActivityVerificationBinding
import com.zhang.mydemo.ui.adapter.VerificationAdapter
import kotlinx.android.synthetic.main.activity_verification.*

class VerificationActivity : BaseActivity<ActivityVerificationBinding>() {

    lateinit var vAdapter: VerificationAdapter

    override fun initView() {
        vAdapter = VerificationAdapter(mutableListOf())
        rvVerification.adapter = vAdapter
        vAdapter.setNewInstance(mutableListOf("", "", "", ""))
    }

    override fun initData() {
    }

    override fun setListener() {
    }
}