package com.zhang.mydemo.kotlin.ui.widgetkt

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow
import com.zhang.mydemo.kotlin.ui.widgetkt.PopupController.PopupParams

/**
 * Created by MQ on 2017/5/2.
 */
class CommonPopupWindow private constructor(context: Context) : PopupWindow() {
    val controller: PopupController
    override fun getWidth(): Int {
        return controller.mPopupView!!.measuredWidth
    }

    override fun getHeight(): Int {
        return controller.mPopupView!!.measuredHeight
    }

    interface PopItemListener {
        fun getChildView(view: View?)
    }

    override fun dismiss() {
        super.dismiss()
        setBackgroundAlpha(controller.context as Activity, 1f)
    }

    //显示在控件上方
    fun showUpView(view: View) {
        this.showAsDropDown(view,
            -(this.width - view.measuredWidth) / 2,
            -(this.height + view.measuredHeight))
    }

    //显示控件上方，并带背景阴影
    fun showUpView(view: View, bgAlpha: Float) {
        this.showAsDropDown(view,
            -(this.width - view.measuredWidth) / 2,
            -(this.height + view.measuredHeight))
        setBackgroundAlpha(controller.context as Activity, bgAlpha)
    }

    fun showBottom(view: View?, bgAlpha: Float) {
        showAtLocation(view, Gravity.BOTTOM, 0, 0)
        setBackgroundAlpha(controller.context as Activity, bgAlpha)
    }

    //显示控件下方
    fun showDownView(view: View) {
        this.showAsDropDown(view, -(this.width - view.measuredWidth) / 2, 0)
    }

    //显示控件下方，并带背景阴影
    fun showDownView(view: View, bgAlpha: Float) {
        this.showAsDropDown(view, -(this.width - view.measuredWidth) / 2, 0)
        setBackgroundAlpha(controller.context as Activity, bgAlpha)
    }

    //显示控件左方
    fun showLeftView(view: View) {
        this.showAsDropDown(view, -view.measuredWidth, -(this.height + view.measuredHeight) / 2)
    }

    //显示控件左方，并带背景阴影
    fun showLeftView(view: View, bgAlpha: Float) {
        this.showAsDropDown(view, -view.measuredWidth, -(this.height + view.measuredHeight) / 2)
        setBackgroundAlpha(controller.context as Activity, bgAlpha)
    }

    //显示控件右方
    fun showRightView(view: View) {
        this.showAsDropDown(view, view.measuredWidth, -(this.height + view.measuredHeight) / 2)
    }

    //显示控件右方，并带背景阴影
    fun showRightView(view: View, bgAlpha: Float) {
        this.showAsDropDown(view, view.measuredWidth, -(this.height + view.measuredHeight) / 2)
        setBackgroundAlpha(controller.context as Activity, bgAlpha)
    }

    fun setBackgroundAlpha(activity: Activity, bgAlpha: Float) {
        val lp = activity.window.attributes
        lp.alpha = bgAlpha
        if (bgAlpha == 1f) {
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND) //不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
        } else {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND) //此行代码主要是解决在华为手机上半透明效果无效的bug
        }
        activity.window.attributes = lp
    }

    class Builder(context: Context?) {
        private val params: PopupParams
        private var listener: PopItemListener? = null

        /**
         * @param layoutResId 设置PopupWindow 布局ID
         * @return Builder
         */
        fun setView(layoutResId: Int): Builder {
            params.mView = null
            params.layoutResId = layoutResId
            return this
        }

        /**
         * @param view 设置PopupWindow布局
         * @return Builder
         */
        fun setView(view: View?): Builder {
            params.mView = view
            params.layoutResId = 0
            return this
        }

        /**
         * 设置子View
         *
         * @param listener ViewInterface
         * @return Builder
         */
        fun setViewOnclickListener(listener: PopItemListener?): Builder {
            this.listener = listener
            return this
        }

        /**
         * 设置宽度和高度 如果不设置 默认是wrap_content
         *
         * @param width 宽
         * @return Builder
         */
        fun setWidthAndHeight(width: Int, height: Int): Builder {
            params.mWidth = width
            params.mHeight = height
            return this
        }

        /**
         * 是否可点击Outside消失
         *
         * @param touchable 是否可点击
         * @return Builder
         */
        fun setOutsideTouchable(touchable: Boolean): Builder {
            params.isTouchable = touchable
            return this
        }

        /**
         * 设置动画
         *
         * @return Builder
         */
        fun setAnimationStyle(animationStyle: Int): Builder {
            params.isShowAnim = true
            params.animationStyle = animationStyle
            return this
        }

        fun create(): CommonPopupWindow {
            val popupWindow = CommonPopupWindow(params.mContext)
            params.apply(popupWindow.controller)
            if (listener != null && params.layoutResId != 0) {
                listener!!.getChildView(popupWindow.controller.mPopupView)
            }
            measureWidthAndHeight(popupWindow.controller.mPopupView)
            return popupWindow
        }

        init {
            params = PopupParams(context!!)
        }
    }

    fun showAsDropDownLeo(anchor: View?, xoff: Int, yoff: Int, alpha: Float) {
        this.showAsDropDown(anchor, xoff, yoff)
        setBackgroundAlpha(controller.context as Activity, alpha)
    }

    companion object {
        /**
         * 测量View的宽高
         *
         * @param view View
         */
        fun measureWidthAndHeight(view: View?) {
            val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            val heightMeasureSpec =
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            view!!.measure(widthMeasureSpec, heightMeasureSpec)
        }
    }

    init {
        controller = PopupController(context, this)
    }
}