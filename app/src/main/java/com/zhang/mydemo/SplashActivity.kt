package com.zhang.mydemo

import android.os.Bundle
import android.view.KeyEvent
import com.zhang.mydemo.base.activity.BaseActivity
import com.zhang.mydemo.databinding.ActivitySplashBinding
import org.jetbrains.anko.startActivity
import timber.log.Timber
import java.util.*

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun isLayoutToolbar(): Boolean = false

    override fun initView() {
        val task: TimerTask = object : TimerTask() {
            override fun run() {
                startActivity<MainActivity>()
                killMyself()
            }
        }
        val timer = Timer()
        timer.schedule(task, 1000)
    }

    override fun initData() {
    }

    override fun setListener() {
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode != KeyEvent.KEYCODE_BACK) {
            return super.onKeyDown(keyCode, event)
        } else {
            return false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.e("SplashActivity_42行_2022/3/23_17:04：${"onCreate"}")
    }

    override fun onStart() {
        super.onStart()
        Timber.e("SplashActivity_49行_2022/2/8_12:01：${"onStart"}")
    }

    override fun onRestart() {
        super.onRestart()
        startActivity<MainActivity>()
        killMyself()
        Timber.e("SplashActivity_49行_2022/2/8_12:01：${"onRestart"}")
    }

    override fun onResume() {
        super.onResume()
        Timber.e("SplashActivity_49行_2022/2/8_12:01：${"onResume"}")
    }

    override fun onPause() {
        super.onPause()
        Timber.e("SplashActivity_49行_2022/2/8_12:01：${"onPause"}")
    }

    override fun onStop() {
        super.onStop()
        Timber.e("SplashActivity_49行_2022/2/8_12:01：${"onStop"}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.e("SplashActivity_49行_2022/2/8_12:01：${"onDestroy"}")
    }
}
