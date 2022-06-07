package com.zhang.mydemo.ui.activity

import android.os.Bundle
import com.zhang.mydemo.base.activity.BaseActivity
import com.zhang.mydemo.databinding.ActivityKeyBoardBinding
import com.zhang.utilslibiary.utils.*
import com.zhang.utilslibiary.utils.toast.Toasty
import kotlinx.android.synthetic.main.activity_key_board.*
import kotlinx.android.synthetic.main.layout_title.*


class KeyBoard : BaseActivity<ActivityKeyBoardBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
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
                Toasty.normal(formatBigNum(if(et.text.toString().isNotEmpty())et.text.toString().toLong() else 1))
            }
        }

        et.addTextChangedListener(object : MyTextWatcher(){
            override fun onMyTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s!!.length==4)
                Toasty.normal("4") else Toasty.normal("false")
            }

        })
    }

    override fun setListener() {

    }
}