package com.zhang.mydemo.ui.widget

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow

/**
 * Created by MQ on 2017/5/2.
 */
class PopupController(var context: Context, private val popupWindow: PopupWindow) {
    private var layoutResId = 0 //布局id

    @JvmField
    var mPopupView: View? = null //弹窗布局View

    private var mView: View? = null

    fun setView(layoutResId: Int) {
        mView = null
        this.layoutResId = layoutResId
        installContent()
    }

    fun setView(view: View?) {
        mView = view
        layoutResId = 0
        installContent()
    }

    private fun installContent() {
        if (layoutResId != 0) {
            mPopupView = LayoutInflater.from(context).inflate(layoutResId, null)
        } else if (mView != null) {
            mPopupView = mView
        }
        popupWindow.contentView = mPopupView
    }

    /**
     * 设置宽度
     *
     * @param width  宽
     * @param height 高
     */
    private fun setWidthAndHeight(width: Int, height: Int) {
        if (width == 0 || height == 0) {
            //如果没设置宽高，默认是WRAP_CONTENT
            popupWindow.width = ViewGroup.LayoutParams.WRAP_CONTENT
            popupWindow.height = ViewGroup.LayoutParams.WRAP_CONTENT
        } else {
            popupWindow.width = width
            popupWindow.height = height
        }
    }

    /**
     * 设置动画
     */
    private fun setAnimationStyle(animationStyle: Int) {
        popupWindow.animationStyle = animationStyle
    }

    /**
     * 设置Outside是否可点击
     *
     * @param touchable 是否可点击
     */
    private fun setOutsideTouchable(touchable: Boolean) {
        popupWindow.setBackgroundDrawable(ColorDrawable(0x00000000)) //设置透明背景
        popupWindow.isOutsideTouchable = touchable //设置outside可点击
        popupWindow.isFocusable = touchable
    }

    internal class PopupParams(var mContext: Context) {
        @JvmField
        var layoutResId //布局id
                = 0

        @JvmField
        var mWidth = 0

        @JvmField
        var mHeight //弹窗的宽和高
                = 0

        @JvmField
        var isShowAnim = false

        @JvmField
        var animationStyle //动画Id
                = 0

        @JvmField
        var mView: View? = null

        @JvmField
        var isTouchable = true
        fun apply(controller: PopupController) {
            when {
                mView != null -> {
                    controller.setView(mView)
                }
                layoutResId != 0 -> {
                    controller.setView(layoutResId)
                }
                else -> {
                    throw IllegalArgumentException("PopupView's contentView is null")
                }
            }
            controller.setWidthAndHeight(mWidth, mHeight)
            controller.setOutsideTouchable(isTouchable) //设置outside可点击
            if (isShowAnim) {
                controller.setAnimationStyle(animationStyle)
            }
        }
    }
}