package com.zhang.mydemo.kotlin.ui.activity

import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import com.example.baselibiary.base.BaseActivity
import com.zhang.mydemo.databinding.ActivityJumpBinding
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_jump.*
import kotlinx.android.synthetic.main.layout_title.*

class JumpActivity : BaseActivity<ActivityJumpBinding>() {

    private val registerForActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == 999) {
                tv_show.text = it.data!!.getStringExtra("data")
            }
        }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun setListener() {
        ivPageBack.singleClick { killMyself() }
        tvPageTitle.text = "学习跳转"

        val intent = Intent(this, Jump2Activity::class.java)
        jump.singleClick {
            registerForActivityResult.launch(intent)
        }
        jump1.singleClick {
            intent.putExtra("data", "传值为:===1")
            registerForActivityResult.launch(intent)
        }
    }
}