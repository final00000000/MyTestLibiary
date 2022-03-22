package com.zhang.utilslibiary.utils

import android.util.DisplayMetrics
import android.app.Activity
import android.annotation.TargetApi
import android.content.Context
import android.graphics.Point
import android.graphics.Rect
import android.os.Build
import android.view.WindowManager
import java.lang.Exception
import java.lang.reflect.Field

/**
 * 尺寸转换工具类
 */
object DensityUtil {
    private val deviceWidthHeight = IntArray(2)
    fun getDeviceInfo(context: Context): IntArray {
        if (deviceWidthHeight[0] == 0 && deviceWidthHeight[1] == 0) {
            val metrics = DisplayMetrics()
            (context as Activity).windowManager.defaultDisplay
                .getMetrics(metrics)
            deviceWidthHeight[0] = metrics.widthPixels
            deviceWidthHeight[1] = metrics.heightPixels
        }
        return deviceWidthHeight
    }

    /**
     * @param context 上下文
     * @param dpValue dp数值
     * @return dp to  px
     */
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 获取屏幕尺寸
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    fun getScreenSize(context: Context): Point {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point(display.width, display.height)
        } else {
            val point = Point()
            display.getSize(point)
            point
        }
    }

    /**
     * @param context 上下文
     * @param pxValue px的数值
     * @return px to dp
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 获取状态栏高度
     *
     * @param activity
     * @return
     */
    fun getStatusBarHeight(activity: Activity): Int {
        var statusBarHeight = 0
        //尝试第一种获取方式
        val resourceId = activity.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            statusBarHeight = activity.resources.getDimensionPixelSize(resourceId)
            if (statusBarHeight > 0) {
                return statusBarHeight
            }
        }
        if (statusBarHeight <= 0) {
            //第一种失败时, 尝试第二种获取方式
            val rectangle = Rect()
            val window = activity.window
            window.decorView.getWindowVisibleDisplayFrame(rectangle)
            statusBarHeight = rectangle.top
            if (statusBarHeight > 0) {
                return statusBarHeight
            }
        }
        if (statusBarHeight <= 0) {
            try {
                var c: Class<*>? = null
                var obj: Any? = null
                var field: Field? = null
                var x = 0
                c = Class.forName("com.android.internal.R\$dimen")
                obj = c.newInstance()
                field = c.getField("status_bar_height")
                x = field[obj].toString().toInt()
                statusBarHeight = activity.resources.getDimensionPixelSize(x)
                return statusBarHeight
            } catch (e1: Exception) {
                e1.printStackTrace()
            }
        }
        return dip2px(activity, 24f)
    }

    /**
     * 获取导航栏高度
     *
     * @param context
     * @return
     */
    fun getNavigationHeight(context: Context): Int {
        var resourceId = 0
        val rid = context.resources.getIdentifier("config_showNavigationBar", "bool", "android")
        if (rid != 0) {
            resourceId =
                context.resources.getIdentifier("navigation_bar_height", "dimen", "android")
            return context.resources.getDimensionPixelSize(resourceId)
        }
        return 0
    }

    /**
     * 是否存在导航栏
     *
     * @param context
     * @return
     */
    fun checkDeviceHasNavigationBar(context: Context?): Boolean {
        if (context is Activity) {
            val windowManager = context.windowManager
            val d = windowManager.defaultDisplay
            val realDisplayMetrics = DisplayMetrics()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                d.getRealMetrics(realDisplayMetrics)
            }
            val realHeight = realDisplayMetrics.heightPixels
            val realWidth = realDisplayMetrics.widthPixels
            val displayMetrics = DisplayMetrics()
            d.getMetrics(displayMetrics)
            val displayHeight = displayMetrics.heightPixels
            val displayWidth = displayMetrics.widthPixels
            return realWidth - displayWidth > 0 || realHeight - displayHeight > 0
        }
        return false
    }
}