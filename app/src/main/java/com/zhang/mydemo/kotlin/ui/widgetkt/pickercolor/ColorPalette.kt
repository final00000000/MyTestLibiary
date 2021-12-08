package com.zhang.mydemo.kotlin.ui.widgetkt.pickercolor

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView

/**
 * Created by zzhoujay on 2015/2/17 0017.
 */
class ColorPalette @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {
    private val colorBoard //色块拾取器
            : ColorBoard
    private val colorPicker //色轮拾取器
            : ColorPicker?
    private val lastColor: View
    private val currColor //上次选择的颜色，当前选择的颜色
            : View
    private val alphaSeekBar: SeekBar
    private val lightSeekBar //透明度、亮度seekbar
            : SeekBar
    private val alphaStart: TextView
    private val alphaEnd: TextView
    private val lightStart: TextView
    private val lightEnd: TextView
    private var alpha = 255
    private var light = 255
    private var red = 0
    private var green = 0
    private var blue = 0
    var selectColor = 0
        private set
    private var lastSelectColor = Color.WHITE
    var onColorSelectListener: OnColorSelectListener? = null
    private fun initEvent() {
        colorPicker!!.onColorSelectListener = ColorPicker.OnColorSelectListener { color ->
            alpha = Color.alpha(color)
            red = Color.red(color)
            green = Color.green(color)
            blue = Color.blue(color)
            reflashColor()
        }
        alphaSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                alpha = progress
                alphaEnd.text = "" + progress
                reflashColor()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        lightSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                light = progress
                lightEnd.text = "" + progress
                reflashColor()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        lastColor.isClickable = true
        lastColor.setOnClickListener {
            selectColor = lastSelectColor
            currColor.setBackgroundColor(selectColor)
        }
        colorBoard.onColorSelectListener = object : ColorBoard.OnColorSelectListener {
            override fun onColorSelect(color: Int) {
                selectColor = color
                currColor.setBackgroundColor(selectColor)
            }
        }
    }

    private val color: Int
        private get() {
            val d = light / 255.0f
            return Color.argb(alpha, (red * d).toInt(), (green * d).toInt(), (blue * d).toInt())
        }

    private fun reflashColor() {
        selectColor = color
        currColor.setBackgroundColor(selectColor)
    }

    fun setLastColor(color: Int) {
        lastSelectColor = color
        lastColor.setBackgroundColor(color)
    }

    fun setCurrColor(color: Int) {
        selectColor = color
        currColor.setBackgroundColor(color)
    }

    interface OnColorSelectListener {
        fun onColorSelect(color: Int)
    }

    fun recycle() {
        if (colorPicker != null) {
            if (!colorPicker.isRecycled) {
                colorPicker.recycle()
            }
        }
    }

    val isRecycle: Boolean
        get() = colorPicker == null || colorPicker.isRecycled

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER_HORIZONTAL
        setPadding(5, 10, 5, 2)
        colorBoard = ColorBoard(context)
        colorPicker =
            ColorPicker(context)
        lastColor = View(context)
        currColor = View(context)
        alphaSeekBar = SeekBar(context)
        lightSeekBar = SeekBar(context)
        alphaStart = TextView(context)
        alphaEnd = TextView(context)
        lightStart = TextView(context)
        lightEnd = TextView(context)
        alphaEnd.gravity = Gravity.CENTER_VERTICAL
        alphaStart.gravity = Gravity.CENTER_VERTICAL
        lightEnd.gravity = Gravity.CENTER_VERTICAL
        lightStart.gravity = Gravity.CENTER_VERTICAL
        alphaStart.text = "A:"
        alphaEnd.text = "255"
        lightStart.text = "L:"
        lightEnd.text = "255"
        alphaSeekBar.max = 255
        lightSeekBar.max = 255
        alphaSeekBar.progress = 255
        lightSeekBar.progress = 255
        alphaSeekBar.isFocusable = false
        lightSeekBar.isFocusable = false
        lastColor.setBackgroundColor(Color.WHITE)
        currColor.setBackgroundColor(Color.WHITE)

        //ColorPicker
        val p1 = LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0, 1F)
        //SeekbarLayout
        val p2 =
            LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        //ColorLayout
        val p3 = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100)
        //ColorBoardLayout
        val p4 =
            LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val p33 = LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1F)
        p33.setMargins(40, 20, 40, 20)
        p2.setMargins(10, 10, 10, 10)
        val colorColor = LinearLayout(context)
        colorColor.minimumHeight = 120
        colorColor.orientation = HORIZONTAL
        colorColor.addView(lastColor, p33)
        colorColor.addView(currColor, p33)
        val alphaLayout = LinearLayout(context)
        val lightLayout = LinearLayout(context)

        //SeekbarText
        val px =
            LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT)
        //SeekBar
        val pppp = LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1F)
        px.setMargins(10, 0, 10, 0)
        alphaLayout.addView(alphaStart, px)
        alphaLayout.addView(alphaSeekBar, pppp)
        alphaLayout.addView(alphaEnd, px)
        lightLayout.addView(lightStart, px)
        lightLayout.addView(lightSeekBar, pppp)
        lightLayout.addView(lightEnd, px)
        addView(colorPicker, p1)
        addView(alphaLayout, p2)
        addView(lightLayout, p2)
        addView(colorColor, p3)
        addView(colorBoard, p4)
        isClickable = true
        initEvent()
    }
}