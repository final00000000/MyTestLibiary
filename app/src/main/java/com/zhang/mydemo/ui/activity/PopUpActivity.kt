package com.zhang.mydemo.ui.activity

import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.zhang.mydemo.R
import com.zhang.mydemo.base.activity.BaseActivity
import com.zhang.mydemo.databinding.ActivityPopupBinding
import com.zhang.mydemo.ui.widget.SimpleRatingBar
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_popup.*
import kotlinx.android.synthetic.main.item_picker.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.find
import org.jetbrains.anko.textColor
import java.util.*
import kotlin.math.ceil


class PopUpActivity : BaseActivity<ActivityPopupBinding>() {

    override fun initView() {
        tvPageTitle.text = "底部弹窗功能"

    }

    override fun initData() {

    }

    override fun setListener() {
        btn.singleClick {
            if (btn.text.toString() != "") {
                btn.text = ""
                showSelectDialog()
            } else showSelectDialog()
        }

    }


    //单项选择
    fun showSelectDialog() {
        val bot = BottomSheetDialog(this@PopUpActivity)
        val inflate = layoutInflater.inflate(R.layout.item_picker, null)
        inflate.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        //设置点击dialog外部不消失
        bot.setCanceledOnTouchOutside(false)

        bot.setContentView(inflate)

        val stateTV = inflate.find<TextView>(R.id.stateTV)
        val resultTV = inflate.find<TextView>(R.id.resultTV)
        val cancelTV = inflate.find<TextView>(R.id.cancel)

        stateTV.isPressed = true
        stateTV.singleClick {
            stateTV.isPressed = true
            stateTV.textColor = Color.parseColor("#3D3D3E")
            stateTV.textSize = 20F
            resultTV.textColor = Color.parseColor("#BBBBBB")
            resultTV.textSize = 16F
            bot.dismiss()
            showScore()
        }

        resultTV.singleClick {
            resultTV.isPressed = true
            resultTV.textColor = Color.parseColor("#3D3D3E")
            resultTV.textSize = 20F
            stateTV.textColor = Color.parseColor("#BBBBBB")
            stateTV.textSize = 16F
            bot.dismiss()
        }

        cancelTV.singleClick {
            bot.dismiss()
            btn.text = "审核驳回"
        }

        bot.show()
    }

    fun showScore() {
        val bot = BottomSheetDialog(this@PopUpActivity)
        val inflate = layoutInflater.inflate(R.layout.item_score, null)
        inflate.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        //设置点击dialog外部不消失
        bot.setCanceledOnTouchOutside(false)
        bot.setContentView(inflate)

        // 默认五分
        var score = 5
        inflate.find<SimpleRatingBar>(R.id.startbar)
            .setOnRatingBarChangeListener(object : SimpleRatingBar.OnRatingChangeListener {
                override fun onRatingChanged(
                    ratingBar: SimpleRatingBar,
                    grade: Float,
                    touch: Boolean
                ) {
                    inflate.find<TextView>(R.id.number).text = "${ceil(grade.toDouble()).toInt()}分"
                    score = ceil(grade).toInt()
                }
            })

        inflate.find<TextView>(R.id.cancel).singleClick { bot.dismiss() }
        inflate.find<TextView>(R.id.submit).singleClick {
            bot.dismiss()
            myToast("操作成功")
            btn.text = "操作成功：${score}分"
        }

        bot.show()
    }

    fun myToast(str: String) {
        val view: View = LayoutInflater.from(this).inflate(R.layout.dynamic_view_toast_custom, null)
        view.findViewById<TextView>(R.id.tvToast).text = str
        val toast = Toast(this)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.duration = Toast.LENGTH_LONG
        toast.view = view
        toast.show()
    }

}