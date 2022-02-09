package com.zhang.mydemo

import android.os.Bundle
import android.view.KeyEvent
import com.elvishew.xlog.XLog
import com.zhang.mydemo.base.BaseActivity
import org.jetbrains.anko.startActivity
import java.util.*

class SplashActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun isLayoutToolbar(): Boolean = false

    override fun initView() {
        val task: TimerTask = object : TimerTask() {
            override fun run() {
                startActivity<MainActivity>()
                killMyself()
            }
        }
        val timer = Timer()
        timer.schedule(task, 2000)
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
        XLog.e("SplashActivity_49行_2022/2/8_12:01：${"onCreate"}")
    }

    override fun onStart() {
        super.onStart()
        XLog.e("SplashActivity_49行_2022/2/8_12:01：${"onStart"}")
    }

    override fun onRestart() {
        super.onRestart()
        startActivity<MainActivity>()
        killMyself()
        XLog.e("SplashActivity_49行_2022/2/8_12:01：${"onRestart"}")
    }

    override fun onResume() {
        super.onResume()
        XLog.e("SplashActivity_49行_2022/2/8_12:01：${"onResume"}")
    }

    override fun onPause() {
        super.onPause()
        XLog.e("SplashActivity_49行_2022/2/8_12:01：${"onPause"}")
    }

    override fun onStop() {
        super.onStop()
        XLog.e("SplashActivity_49行_2022/2/8_12:01：${"onStop"}")
    }

    override fun onDestroy() {
        super.onDestroy()
        XLog.e("SplashActivity_49行_2022/2/8_12:01：${"onDestroy"}")
    }
}
