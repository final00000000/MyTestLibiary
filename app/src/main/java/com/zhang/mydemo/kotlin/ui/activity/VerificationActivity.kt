package com.zhang.mydemo.kotlin.ui.activity

import com.example.baselibiary.base.BaseActivity
import com.zhang.mydemo.databinding.ActivityVerificationBinding
import com.zhang.mydemo.kotlin.ui.adapter.VerificationAdapter
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