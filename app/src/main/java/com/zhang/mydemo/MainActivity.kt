package com.zhang.mydemo

import android.view.KeyEvent
import android.view.View
import com.zhang.mydemo.base.BaseActivity
import com.zhang.mydemo.databinding.ActivityMainBinding
import com.zhang.utilslibiary.utils.AppActivityManager
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun initView() {
        baseTitle.visibility = View.GONE
    }

    override fun initData() {
    }

    override fun setListener() {
        tv_01.singleClick {
            toast("Hello World!")
        }
        kotlin.singleClick {
            startActivity<KotlinActivity>()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit()
            true
        } else {
            super.onKeyDown(keyCode, event)
        }
    }

    private var mExitTime: Long = 0

    private fun exit() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            toast("再按一次退出应用")

            mExitTime = System.currentTimeMillis()
        } else {
            AppActivityManager.removeAllActivity()
        }
    }

}