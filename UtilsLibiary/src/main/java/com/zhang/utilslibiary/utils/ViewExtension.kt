@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.zhang.utilslibiary.utils

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Color
import android.text.Editable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
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
    val style: SpannableStringBuilder = SpannableStringBuilder(title + content)
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

fun getRequest(): RequestOptions {
    return RequestOptions().signature(ObjectKey(System.currentTimeMillis()))
        .fitCenter()//.diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(true)
}

