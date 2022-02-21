package com.zhang.mydemo.kotlin.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zhang.mydemo.R
import com.zhang.mydemo.base.BaseActivity
import com.zhang.mydemo.kotlin.ui.adapter.VerificationAdapter
import kotlinx.android.synthetic.main.activity_verification.*

class VerificationActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_verification

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