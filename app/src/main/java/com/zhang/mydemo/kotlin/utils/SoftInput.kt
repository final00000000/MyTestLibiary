package com.zhang.mydemo.kotlin.utils
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

/**
 * @Author : zhang
 * @Create Time : 2021/11/10 11:59
 * @Class Describe : 描述
 * @Project Name : KotlinDemo
 */
object SoftInput {

    /**
     * 显示软键盘
     */
    fun showSoftInput(context: Context, view: View?) {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
        view!!.requestFocus()
    }


    /**
     * 关闭软键盘
     */
    fun hideSoftInput(activity: Activity) {
        val imm =
            activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm != null && imm.isActive && activity.currentFocus != null) {
            imm.hideSoftInputFromWindow(
                activity.currentFocus!!.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

}