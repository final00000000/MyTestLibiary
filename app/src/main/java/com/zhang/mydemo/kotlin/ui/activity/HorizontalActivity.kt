package com.zhang.mydemo.kotlin.ui.activity

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.zhang.mydemo.R
import com.zhang.mydemo.base.BaseActivity
import com.zhang.mydemo.kotlin.ui.widgetkt.horline.UnderLineLinearLayout
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_underline_horizontal.*
import kotlinx.android.synthetic.main.layout_title.*

class HorizontalActivity : BaseActivity() {
    var t = 1

    override fun getLayoutId(): Int = R.layout.activity_underline_horizontal

    override fun initView() {
        ivPageBack.singleClick { killMyself() }
        tvPageTitle.text = "横向时间轴"
    }

    override fun initData() {

    }

    override fun setListener() {
        add.singleClick { addItem() }
        sub.singleClick { subItem() }
        vertical.singleClick { killMyself() }
        line_gravity.singleClick {
            underline_layout.setLineGravity(LINE_GRAVITY[t % 3])
            line_gravity.text = LINE_GRAVITY_STR[t % 3]
            t++
        }
    }

    var i = 0

    private fun addItem() {
        val v: View =
            LayoutInflater.from(this).inflate(R.layout.item_horizontal, underline_layout, false)
        (v.findViewById<View>(R.id.tx_action) as TextView).text = "第${i}次主材验收"
        underline_layout.addView(v)
        i++
    }

    private fun subItem() {
        if (underline_layout!!.childCount > 0) {
            underline_layout!!.removeViews(underline_layout!!.childCount - 1, 1)
            i--
        }
    }


    companion object {
        val LINE_GRAVITY =
            intArrayOf(UnderLineLinearLayout.GRAVITY_TOP, UnderLineLinearLayout.GRAVITY_MIDDLE,
                UnderLineLinearLayout.GRAVITY_BOTTOM)
        val LINE_GRAVITY_STR = arrayOf("TOP", "MIDDLE", "BOTTOM")
    }
}