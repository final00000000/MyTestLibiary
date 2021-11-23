package com.zhang.utilslibiary.utils
import android.view.View

/**
 * 防双击
 */
class SingleClickListener(
    private val isCheckLogin: Boolean = false,
    private val click: (v: View) -> Unit
) : View.OnClickListener {

    companion object {
        // 获取的值为300ms，在快速点击时300ms太小，达不到过滤快速点击的效果，此处将其设置为1s
        private const val DOUBLE_CLICK_TIMEOUT = 1 * 1000
    }

    private var clickTimeOut = DOUBLE_CLICK_TIMEOUT

    constructor(isCheckLogin: Boolean, t: Int, click: (View) -> Unit) : this(isCheckLogin, click) {
        clickTimeOut = t
    }

    private var lastClick: Long = 0L

    override fun onClick(v: View) {
        if (getLastClickTimeout() > clickTimeOut) {
            lastClick = System.currentTimeMillis()
            click(v)
        }
    }

    private fun getLastClickTimeout(): Long {
        return System.currentTimeMillis() - lastClick
    }

}