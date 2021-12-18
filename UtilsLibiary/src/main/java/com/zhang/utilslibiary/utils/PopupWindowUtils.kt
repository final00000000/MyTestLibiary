package com.zhang.utilslibiary.utils

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.PopupWindow

/**
 * @Author : zhang
 * @Create Time : 2021/9/24 20:08
 * @Class Describe : 描述
 * @Project Name : arm
 */
object PopupWindowUtils {
    fun Popup(context: Activity, view: View,width : Int,height : Int, gravity: Int): PopupWindow {
        val popupWindow = PopupWindow(
            view,
            width,
            height,
            true
        )

        popupWindow.setBackgroundDrawable(ColorDrawable())
        popupWindow.isOutsideTouchable = true


        popupWindow.showAtLocation(view, gravity, 0, 0)

        val lp: WindowManager.LayoutParams = context.window?.attributes!!

        lp.alpha = 0.5f //代表透明程度，范围为0 - 1.0f
        context.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        context.window?.attributes = lp
        //退出popupWindow时取消暗背景
        popupWindow.setOnDismissListener {
            lp.alpha = 1.0f
            context.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            context.window?.attributes = lp
        }
        return popupWindow
    }
    fun DownPopup(context: Activity, view: View,width : Int,height : Int, gravity: Int,DownView : View): PopupWindow {
        val popupWindow = PopupWindow(
            view,
            width,
            height,
            true
        )

        popupWindow.setBackgroundDrawable(ColorDrawable())
        popupWindow.isOutsideTouchable = true


//        popupWindow.showAtLocation(DownView, gravity, 0, 0)
        popupWindow.showAsDropDown(DownView)

        val lp: WindowManager.LayoutParams = context.window?.attributes!!

        lp.alpha = 0.5f //代表透明程度，范围为0 - 1.0f
        context.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        context.window?.attributes = lp
        //退出popupWindow时取消暗背景
        popupWindow.setOnDismissListener {
            lp.alpha = 1.0f
            context.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            context.window?.attributes = lp
        }
        return popupWindow
    }

}