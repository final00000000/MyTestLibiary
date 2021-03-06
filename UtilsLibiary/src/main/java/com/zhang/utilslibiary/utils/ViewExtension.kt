@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.zhang.utilslibiary.utils

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.text.Editable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.util.*


/**
 * Created by 宗传
 * Time 2018/8/31  下午4:57
 *
 * View扩展类
 **/

fun View.singleClick(isCheckLogin: Boolean = false, listener: (View?) -> Unit) {
    setOnClickListener(SingleClickListener(isCheckLogin, listener))
}

fun View.singleClick(isCheckLogin: Boolean = false, t: Int, listener: (View?) -> Unit) {
    setOnClickListener(SingleClickListener(isCheckLogin, t, listener))
}

fun TextView.setText(title: String, content: String) {
    val style = SpannableStringBuilder(title + content)
    style.setSpan(
        ForegroundColorSpan(Color.parseColor("#222222")),
        0,
        title.length,
        Spannable.SPAN_EXCLUSIVE_INCLUSIVE
    )
    style.setSpan(
        ForegroundColorSpan(Color.parseColor("#656565")),
        title.length,
        content.length,
        Spannable.SPAN_EXCLUSIVE_INCLUSIVE
    )
    this.text = style
}

fun EditText.inputMoney() {
    this.addTextChangedListener(object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            var s = s
            // 限制最多能输入9位整数
            if (s.toString().contains(".")) {
                if (s.toString().indexOf(".") > 9) {
                    s = s.toString().subSequence(0, 9).toString() + s.toString()
                        .substring(s.toString().indexOf("."))
                    this@inputMoney.setText(s)
                    this@inputMoney.setSelection(9)
                }
            } else {
                if (s.toString().length > 9) {
                    s = s.toString().subSequence(0, 9)
                    this@inputMoney.setText(s)
                    this@inputMoney.setSelection(9)
                }
            }
            // 判断小数点后只能输入两位
            if (s.toString().contains(".")) {
                if (s.length - 1 - s.toString().indexOf(".") > 2) {
                    s = s.toString().subSequence(
                        0,
                        s.toString().indexOf(".") + 3
                    )
                    this@inputMoney.setText(s)
                    this@inputMoney.setSelection(s.length)
                }
            }
            //如果第一个数字为0，第二个不为点，就不允许输入
            if (s.toString().startsWith("0") && s.toString().trim { it <= ' ' }.length > 1) {
                if (s.toString().substring(1, 2) != ".") {
                    this@inputMoney.setText(s.subSequence(0, 1))
                    this@inputMoney.setSelection(1)
                    return
                }
            }
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

        }

        override fun afterTextChanged(s: Editable) {
            if (this@inputMoney.text.toString().trim() != "") {
                if (this@inputMoney.text.toString().trim().substring(0, 1) == ".") {
                    this@inputMoney.setText("0" + this@inputMoney.text.toString().trim())
                    this@inputMoney.setSelection(1)
                }
            }
        }
    })
}

/**
 * 软键盘弹出 隐藏
 */
fun EditText.showInputWindow(activity: Activity, isboolean: Boolean) {
    if (isboolean) {
        this.isFocusable = true
        this.isFocusableInTouchMode = true
        this.requestFocus()
        activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    } else {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                val manager = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(
                    activity.currentFocus?.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        }, 1000)
    }
}

/**
 * 判断是否有网络连接
 * @param context Context?
 * @return Boolean
 */
fun isNetworkConnected(context: Context?): Boolean {
    if (context != null) {
        val mConnectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val mNetworkInfo = mConnectivityManager.activeNetworkInfo
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable
        }
    }
    return false
}

/**
 * 显示软键盘
 */
fun showSoftInput(context: Context, view: View?) {
    val inputMethodManager =
        context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
    view!!.requestFocus()
}

/**
 * 打开软键盘
 *
 * @param mEditText 输入框
 * @param mContext  上下文
 */
fun openKeybord(mEditText: EditText?, mContext: Context) {
    val imm = mContext
        .getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN)
    imm.toggleSoftInput(
        InputMethodManager.SHOW_FORCED,
        InputMethodManager.HIDE_IMPLICIT_ONLY
    )
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

/**
 * 关闭软键盘
 *
 * @param mEditText 输入框
 * @param mContext  上下文
 */
fun closeKeybord(mEditText: EditText, mContext: Context?) {
    if (mContext != null) {
        val imm = mContext
            .getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
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
        return !(event.x > left && event.x < right && event.y > top && event.y < bottom)
    }
    return false
}

/**
 * 获取颜色值
 * @receiver Activity
 * @param id Int
 * @return Int
 */
fun Activity.getColorRes(@ColorRes id: Int): Int = ContextCompat.getColor(applicationContext, id)

/**
 * 获取资源图片
 * @receiver Activity
 * @param id Int
 * @return Drawable?
 */
fun Activity.getDrawableRes(@DrawableRes id: Int): Drawable? =
    ContextCompat.getDrawable(applicationContext, id)
