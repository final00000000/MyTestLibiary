package com.zhang.mydemo.ui.widget.horline

import android.content.Context

/**
 * Created by 大灯泡 on 2016/1/26.
 */
class UIHelper private constructor() {
    companion object {
        /**
         * dip转px
         */
        @JvmStatic
        fun dipToPx(context: Context, dip: Float): Int {
            return (dip * context.resources.displayMetrics.density + 0.5f).toInt()
        }

        /**
         * px转dip
         */
        @JvmStatic
        fun pxToDip(context: Context, pxValue: Int): Int {
            val scale = context.resources.displayMetrics.density
            return (pxValue / scale + 0.5f).toInt()
        }
    }

    init {
        throw InstantiationException("This class is not for instantiation")
    }
}