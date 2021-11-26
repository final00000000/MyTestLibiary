package com.zhang.mydemo.kotlin.ui.widgetkt

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color

/**
 * Created by zzhoujay on 2015/2/21 0021.
 * 颜色选择器：ArtDialog
 */
class ColorSelectDialog(context: Context?) {
    private val dialog: AlertDialog
    private val colorPalette: ColorPalette
    var onColorSelectListener: OnColorSelectListener? = null
    private var color = -1
    fun show() {
        if (color == -1) {
            color = Color.BLACK
        }
        colorPalette.setLastColor(color)
        colorPalette.setCurrColor(color)
        dialog.show()
    }

    interface OnColorSelectListener {
        fun onSelectFinish(color: Int)
    }

    fun setLastColor(color: Int) {
        colorPalette.setLastColor(color)
    }

    init {
        val builder = AlertDialog.Builder(context)
        colorPalette = ColorPalette(context)
        colorPalette.setLastColor(Color.BLUE)
        builder.setView(colorPalette)
        builder.setPositiveButton("确定") { dialog, which ->
            if (onColorSelectListener != null) {
                color = colorPalette.selectColor
                onColorSelectListener!!.onSelectFinish(color)
            }
        }
        builder.setNegativeButton("取消", null)
        dialog = builder.create()
    }
}