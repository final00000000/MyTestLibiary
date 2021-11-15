package com.zhang.mydemo.kotlin.keyboard

import android.os.Bundle
import com.zhang.kotlindemo.base.BaseActivity
import com.zhang.mydemo.R
import com.zhang.mydemo.kotlin.utils.SoftInput
import com.zhang.mydemo.kotlin.utils.singleClick
import kotlinx.android.synthetic.main.activity_key_board.*


class KeyBoard : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_key_board)

        initData()
    }

    fun initData() {
        et.setOnClickListener {
            SoftInput.showSoftInput(this@KeyBoard, et)
            compileTV.text = "完成"
        }

        //判断键盘显示还是隐藏
        SoftKeyBoardListener.setListener(this, object :
            SoftKeyBoardListener.OnSoftKeyBoardChangeListener {
            override fun keyBoardShow(height: Int) {
                // 弹出键盘
            }

            override fun keyBoardHide(height: Int) {
                // 隐藏键盘
                et.clearFocus()
                compileTV.text = "编辑"
            }
        })


        // 点击按钮 弹起 or 隐藏键盘
        compileTV.singleClick {
            if (compileTV.text.toString() == "编辑") {
                SoftInput.showSoftInput(this@KeyBoard, et)
                compileTV.text = "完成"
            } else {
                SoftInput.hideSoftInput(this@KeyBoard)
                compileTV.text = "编辑"
            }
        }

    }


}