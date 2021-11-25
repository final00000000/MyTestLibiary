package com.zhang.mydemo

import android.os.Bundle
import android.os.PersistableBundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import com.elvishew.xlog.XLog
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar
import com.zhang.utilslibiary.utils.AppManager
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.util.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        ImmersionBar
//            .with(this)
//            .hideBar(BarHide.FLAG_HIDE_BAR)
//            .init()
        initView()
    }


    fun initView() {
        val task: TimerTask = object : TimerTask() {
            override fun run() {
                startActivity<MainActivity>()
                AppManager.addActivity(this@SplashActivity)
            }
        }
        val timer = Timer()
        timer.schedule(task, 2000)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode != KeyEvent.KEYCODE_BACK) {
            return super.onKeyDown(keyCode, event)
        } else {
            return false
        }
    }
}