package com.zhang.mydemo.ui.activity

import com.zhang.mydemo.base.BaseActivity
import com.zhang.mydemo.databinding.ActivityKeyBoardBinding
import com.zhang.utilslibiary.utils.*
import kotlinx.android.synthetic.main.activity_key_board.*
import kotlinx.android.synthetic.main.layout_title.*


class KeyBoard : BaseActivity<ActivityKeyBoardBinding>() {

    override fun initView() {
        ivPageBack.singleClick { killMyself() }
        tvPageTitle.text = "软键盘"
    }

    override fun initData() {
        //判断键盘显示还是隐藏
        SoftKeyBoardListener.setListener(this, object :
            SoftKeyBoardListener.OnSoftKeyBoardChangeListener {
            override fun keyBoardShow(height: Int) {
                // 弹出键盘
                compileTV.text = "完成"
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
                showSoftInput(this@KeyBoard, et)
                compileTV.text = "完成"
            } else {
                hideSoftInput(this@KeyBoard)
                compileTV.text = "编辑"
            }
        }

    }

    override fun setListener() {

    }
}