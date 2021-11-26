package com.zhang.mydemo.kotlin.ui.widgetkt

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import kotlin.jvm.JvmOverloads
import android.widget.LinearLayout
import com.zhang.mydemo.kotlin.ui.widgetkt.ColorBoard
import android.view.Gravity
import android.view.View
import android.view.ViewGroup

/**
 * Created by zzhoujay on 2015/2/20 0020.
 * 颜色选择板
 */
class ColorBoard @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private val params: LayoutParams
    private val ppp: LayoutParams
    private val layouts: Array<LinearLayout?>
    private val colors //色块
            : Array<Array<View?>>
    private val colorValue //色块的颜色
            : Array<IntArray>
    var onColorSelectListener: OnColorSelectListener? = null
    private fun resetColor() {
        for (i in 0 until heightSize) {
            for (j in 0 until widthSize) {
                colors[i][j]!!.setBackgroundColor(colorValue[i][j])
            }
        }
    }

    //设置色块的颜色
    fun setColors(cs: Array<IntArray>) {
        for (i in 0 until heightSize) {
            for (j in 0 until widthSize) {
                colorValue[i][j] = cs[i][j]
                colors[i][j]!!.setBackgroundColor(cs[i][j])
            }
        }
    }

    //设置某个色块的颜色
    fun setColor(x: Int, y: Int, color: Int) {
        colorValue[x][y] = color
        colors[x][y]!!.setBackgroundColor(color)
    }

    //获取某个色块的颜色
    fun getColor(x: Int, y: Int): Int {
        return colorValue[x][y]
    }

    interface OnColorSelectListener {
        fun onColorSelect(color: Int)
    }

    companion object {
        const val widthSize = 5 //色块横向数目
        const val heightSize = 2 //色块纵向数目
        const val colorHeight = 100 //色块的最小高度
        const val colorWidth = 120 //色块的最小宽度
    }

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER_HORIZONTAL
        layouts = arrayOfNulls(heightSize)
        ppp = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1F)
        for (i in 0 until heightSize) {
            layouts[i] = LinearLayout(context)
            layouts[i]!!.orientation = HORIZONTAL
            addView(layouts[i], ppp)
        }
        params = LayoutParams(colorWidth, colorHeight, 1F)
        params.setMargins(5, 5, 5, 5)
        colors = Array(heightSize) { arrayOfNulls(widthSize) }
        colorValue = Array(heightSize) { IntArray(widthSize) }
        for (i in 0 until heightSize) {
            for (j in 0 until widthSize) {
                colors[i][j] = View(context)
                colors[i][j]!!.isClickable = true
                layouts[i]!!.addView(colors[i][j], params)
                colors[i][j]!!.setOnClickListener {
                    if (onColorSelectListener != null) {
                        onColorSelectListener!!.onColorSelect(colorValue[i][j])
                        Log.d("colorBoard", "" + colorValue[i][j])
                    }
                }
            }
        }
        colorValue[0][0] = Color.RED
        colorValue[0][1] = Color.YELLOW
        colorValue[0][2] = Color.GREEN
        colorValue[0][3] = Color.GRAY
        colorValue[0][4] = Color.BLUE
        colorValue[1][0] = Color.LTGRAY
        colorValue[1][1] = Color.DKGRAY
        colorValue[1][2] = Color.MAGENTA
        colorValue[1][3] = Color.WHITE
        colorValue[1][4] = Color.BLACK
        resetColor()
    }
}