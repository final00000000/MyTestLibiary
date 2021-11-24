package com.zhang.mydemo.kotlin.pickerview

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.zhang.mydemo.R
import com.zhang.mydemo.base.BaseActivity
import com.zhang.mydemo.java.starbar.StarBarView
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_picker_date.*
import kotlinx.android.synthetic.main.item_picker.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.find
import org.jetbrains.anko.textColor
import java.util.*


class PickerDateActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_picker_date

    override fun initView() {
        ivPageBack.singleClick { killMyself() }
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
        val bot = BottomSheetDialog(this@PickerDateActivity)
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

        stateTV.singleClick {
            stateTV.textColor = Color.parseColor("#3D3D3E")
            stateTV.textSize = 20F
            stateTV.backgroundColor = Color.parseColor("#f8f8f8")
            resultTV.textColor = Color.parseColor("#BBBBBB")
            resultTV.textSize = 16F
            resultTV.backgroundColor = Color.parseColor("#ffffff")
            val task: TimerTask = object : TimerTask() {
                override fun run() {
                    runOnUiThread {
                        bot.dismiss()
                        showScore()
                    }
                }
            }
            val timer = Timer()
            timer.schedule(task, 100)
        }

        resultTV.singleClick {
            resultTV.textColor = Color.parseColor("#3D3D3E")
            resultTV.textSize = 20F
            resultTV.backgroundColor = Color.parseColor("#f8f8f8")

            stateTV.textColor = Color.parseColor("#BBBBBB")
            stateTV.textSize = 16F
            stateTV.backgroundColor = Color.parseColor("#ffffff")
            val task: TimerTask = object : TimerTask() {
                override fun run() {
                    runOnUiThread {
                        bot.dismiss()
                        showScore()
                    }
                }
            }
            val timer = Timer()
            timer.schedule(task, 100)
        }

        cancelTV.singleClick {
            bot.dismiss()
        }

        bot.show()
    }

    fun showScore() {
        val bot = BottomSheetDialog(this@PickerDateActivity)
        val inflate = layoutInflater.inflate(R.layout.item_score, null)
        inflate.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        //设置点击dialog外部不消失
        bot.setCanceledOnTouchOutside(false)

        bot.setContentView(inflate)

        val startbar = inflate.find<StarBarView>(R.id.startbar)
        startbar.singleClick {
            inflate.find<TextView>(R.id.number).text = "${startbar.starRating.toInt()}分"
        }

        inflate.find<TextView>(R.id.cancel).singleClick { bot.dismiss() }
        inflate.find<TextView>(R.id.submit).singleClick {
            bot.dismiss()
            myToast("操作成功")
            btn.text = "操作成功：${startbar.starRating.toInt()}分"
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