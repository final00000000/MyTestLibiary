package com.zhang.mydemo.ui.widget

import android.content.Context
import android.widget.EditText
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by lihang on 2017/9/13.
 */
/**
 * 打开或关闭软键盘
 *
 * @author zhy
 */
object KeyBoardUtils {
    /**
     * 打开软键盘
     *
     * @param mEditText 输入框
     * @param mContext  上下文
     */
    fun openKeybord(mEditText: EditText?, mContext: Context) {
        val imm = mContext
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN)
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
            InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    /**
     * 关闭软键盘
     *
     * @param mEditText 输入框
     * @param mContext  上下文
     */
    fun closeKeybord(mEditText: EditText, mContext: Context?) {
        if (mContext != null) {
            val imm = mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(mEditText.windowToken, 0)
        }
    }

    /**
     * 判断当前点击屏幕的地方是否是软键盘：
     *
     * @param v
     * @param event
     * @return
     */
    fun isShouldHideInput(v: View?, event: MotionEvent): Boolean {
        if (v != null && v is EditText) {
            val leftTop = intArrayOf(0, 0)
            v.getLocationInWindow(leftTop)
            val left = leftTop[0]
            val top = leftTop[1]
            val bottom = top + v.getHeight()
            val right = (left
                    + v.getWidth())
            return if (event.x > left && event.x < right && event.y > top && event.y < bottom
            ) {
                // 保留点击EditText的事件
                false
            } else {
                true
            }
        }
        return false
    } //点击屏幕空白处，隐藏软键盘
    //    @Override
    //    public boolean dispatchTouchEvent(MotionEvent ev) {
    //        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
    //            View v = getCurrentFocus();
    //            if (isShouldHideInput(v, ev)) {
    //                KeyBoardUtils.closeKeybord(binding.editPhone, LoginActivity.this);
    //            }
    //        }
    //        return super.dispatchTouchEvent(ev);
    //    }
}