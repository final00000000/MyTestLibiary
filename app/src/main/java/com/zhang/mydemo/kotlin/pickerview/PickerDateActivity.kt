package com.zhang.mydemo.kotlin.pickerview

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.CustomListener
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.bigkoo.pickerview.view.TimePickerView
import com.elvishew.xlog.XLog
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.zhang.mydemo.R
import com.zhang.mydemo.java.starbar.StarBarView
import com.zhang.mydemo.kotlin.utils.singleClick
import kotlinx.android.synthetic.main.activity_picker_date.*
import kotlinx.android.synthetic.main.item_picker.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.find
import org.jetbrains.anko.textColor
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import android.view.MotionEvent


class PickerDateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picker_date)
        initView()
    }

    fun initView() {
        btn.singleClick {
            showSelectDialog(arrayListOf("审核通过", "审核驳回"))
        }
    }


    //单项选择
    fun showSelectDialog(list: ArrayList<String>) {

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
            toast("${startbar.starRating.toInt()}")
            bot.dismiss()
        }

        bot.show()
    }

    private fun initTimePicker(
        y: Boolean, m: Boolean, d: Boolean, h: Boolean,
        mm: Boolean, ss: Boolean, listener: OnTimeSelectListener,
    ) {

        //选择出生年月日
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11

        val curDate = Date(System.currentTimeMillis())//获取当前时间
        val formatter_year = SimpleDateFormat("yyyy ")
        val year_str = formatter_year.format(curDate)
        val year_int = java.lang.Double.parseDouble(year_str).toInt()

        val formatter_mouth = SimpleDateFormat("MM ")
        val mouth_str = formatter_mouth.format(curDate)
        val mouth_int = java.lang.Double.parseDouble(mouth_str).toInt()

        // 不设置报错,调用方法传false就不显示
        val formatter_day = SimpleDateFormat("dd ")
        val day_str = formatter_day.format(curDate)
        val day_int = java.lang.Double.parseDouble(day_str).toInt()

        val selectedDate = Calendar.getInstance()//系统当前时间

        val startDate = Calendar.getInstance()
        val endDate = Calendar.getInstance()

        startDate.set(2020, 0, day_int)
        endDate.set(year_int, mouth_int - 1, day_int)

        //时间选择器
        val pvTime1: TimePickerView = TimePickerBuilder(this, listener)
            .setType(booleanArrayOf(y, m, d, h, mm, ss)) //年月日时分秒 的显示与否，不设置则默认全部显示
            .setLabel("年", "月", "日", "", "", "")//默认设置为年月日时分秒
            .isCenterLabel(false)
            .setDividerColor(Color.GRAY)
            .setTextColorCenter(Color.parseColor("#333333"))//设置选中项的颜色
            .setTextColorOut(Color.parseColor("#BBBBBB"))//设置没有被选中项的颜色
            .setDate(selectedDate)
//            .setTitleText("请选择月份") //标题
//            .setTitleSize(16)
//            .setTitleColor(Color.parseColor("#3D3D3D"))
//            .setContentTextSize(20) // 内容
            //.setCancelColor(Color.TRANSPARENT) // 取消按钮设置透明色 就看不见了
            .setCancelText("取消")
            .setCancelColor(Color.parseColor("#3D3D3D"))
            .setSubmitColor(Color.parseColor("#005BAB"))
            .setSubmitText("确定")
            .setSubmitColor(Color.parseColor("#005BAB"))
            .setSubCalSize(18)
            .setSubCalSize(16)
            .setBgColor(Color.parseColor("#F8F8F8"))//滚轮背景颜色 Night mode
            .setTextXOffset(-10, 0, 10, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]
            .setRangDate(startDate, endDate)
            .setBgColor(Color.parseColor("#F6F7F6"))
            .setDecorView(null)
            .build()
        pvTime1.show()
    }
}