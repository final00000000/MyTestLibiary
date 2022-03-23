package com.zhang.utilslibiary.utils

import android.view.WindowManager
import android.util.DisplayMetrics
import android.annotation.TargetApi
import android.view.ViewConfiguration
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Rect
import android.os.Build
import java.lang.Exception
import java.lang.UnsupportedOperationException

/**
 * @描述 : 屏幕工具类
 */

/**
 * 获得屏幕宽度
 *
 * @param context
 * @return
 */
fun getScreenWidth(context: Context): Int {
    val wm = context
        .getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val outMetrics = DisplayMetrics()
    wm.defaultDisplay.getMetrics(outMetrics)
    return outMetrics.widthPixels
}

/**
 * 获得屏幕高度
 *
 * @param context
 * @return
 */
fun getScreenHeight(context: Context): Int {
    val wm = context
        .getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val outMetrics = DisplayMetrics()
    wm.defaultDisplay.getMetrics(outMetrics)
    return outMetrics.heightPixels
}

/**
 * 获得状态栏的高度
 *
 * @param context
 * @return
 */
fun getStatusHeight(context: Context): Int {
    var statusHeight = -1
    try {
        val clazz = Class.forName("com.android.internal.R\$dimen")
        val `object` = clazz.newInstance()
        val height = clazz.getField("status_bar_height")[`object`].toString().toInt()
        statusHeight = context.resources.getDimensionPixelSize(height)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return statusHeight
}

//获取虚拟按键的高度
fun getNavigationBarHeight(context: Context): Int {
    var result = 0
    if (hasNavBar(context)) {
        val res = context.resources
        val resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = res.getDimensionPixelSize(resourceId)
        }
    }
    return result
}

/**
 * 检查是否存在虚拟按键栏
 *
 * @param context
 * @return
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
fun hasNavBar(context: Context): Boolean {
    val res = context.resources
    val resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android")
    return if (resourceId != 0) {
        var hasNav = res.getBoolean(resourceId)
        // check override flag
        val sNavBarOverride = navBarOverride
        if ("1" == sNavBarOverride) {
            hasNav = false
        } else if ("0" == sNavBarOverride) {
            hasNav = true
        }
        hasNav
    } else { // fallback
        !ViewConfiguration.get(context).hasPermanentMenuKey()
    }
}

/**
 * 判断虚拟按键栏是否重写
 *
 * @return
 */
private val navBarOverride: String?
    private get() {
        var sNavBarOverride: String? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                val c = Class.forName("android.os.SystemProperties")
                val m = c.getDeclaredMethod("get", String::class.java)
                m.isAccessible = true
                sNavBarOverride = m.invoke(null, "qemu.hw.mainkeys") as String
            } catch (e: Throwable) {
            }
        }
        return sNavBarOverride
    }

/**
 * 获取当前屏幕截图，包含状态栏
 *
 * @param activity
 * @return
 */
fun snapShotWithStatusBar(activity: Activity): Bitmap? {
    val view = activity.window.decorView
    view.isDrawingCacheEnabled = true
    view.buildDrawingCache()
    val bmp = view.drawingCache
    val width = getScreenWidth(activity)
    val height = getScreenHeight(activity)
    var bp: Bitmap? = null
    bp = Bitmap.createBitmap(bmp, 0, 0, width, height)
    view.destroyDrawingCache()
    return bp
}

/**
 * 获取当前屏幕截图，不包含状态栏
 *
 * @param activity
 * @return
 */
fun snapShotWithoutStatusBar(activity: Activity): Bitmap? {
    val view = activity.window.decorView
    view.isDrawingCacheEnabled = true
    view.buildDrawingCache()
    val bmp = view.drawingCache
    val frame = Rect()
    activity.window.decorView.getWindowVisibleDisplayFrame(frame)
    val statusBarHeight = frame.top
    val width = getScreenWidth(activity)
    val height = getScreenHeight(activity)
    var bp: Bitmap? = null
    bp = Bitmap.createBitmap(
        bmp, 0, statusBarHeight, width, height
                - statusBarHeight
    )
    view.destroyDrawingCache()
    return bp
}

/**
 * @return 获取屏幕的高 单位：px
 */
fun getScreenHeightPx(context: Context): Int {
    val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val dm = DisplayMetrics()
    if (windowManager != null) {
//            windowManager.getDefaultDisplay().getMetrics(dm);
        windowManager.defaultDisplay.getRealMetrics(dm)
        return dm.heightPixels
    }
    return 0
}
