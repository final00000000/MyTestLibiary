package com.zhang.utilslibiary.utils

import android.content.Context
import android.content.res.Resources

/**
 *
 * @author wuhan
 * @date 2018/11/23 10:23
 */
object DisplayUtils {
    /**
     * dp转px
     *
     * @param dipValue
     * @return
     */
    fun dip2px(dipValue: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }

    fun px2dip(px: Int): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (px / scale + 0.5f).toInt()
    }

    fun px2dip(px:Float):Int{
        val scale = Resources.getSystem().displayMetrics.density
        return (px / scale + 0.5f).toInt()
    }

    fun dip2Px(dipValue: Int): Float {
        val scale: Float = Resources.getSystem().displayMetrics.density
        return (dipValue * scale + 0.5f)
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

}