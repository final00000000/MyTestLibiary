package com.zhang.mydemo.ui.widget.tag

import android.graphics.Color
import android.net.Uri
import android.os.Parcel
import android.text.ParcelableSpan
import android.text.TextPaint
import android.text.TextUtils
import android.text.style.UnderlineSpan
import android.view.View
import com.zhang.mydemo.MainActivity
import org.jetbrains.anko.startActivity

/**
 * Created by liuz on 16/6/7.
 */
class MyURLSpan(url: String, color: String) : UnderlineSpan(), ParcelableSpan {

    private val uRL: String = url

    private var mColor = "#EF8200"

    init {
        if (!TextUtils.isEmpty(color)) {
            mColor = color
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(uRL)
    }

    fun onClick(widget: View) {
        if (!TextUtils.isEmpty(uRL)) {
            val uri = Uri.parse(uRL)
            if (uri.scheme!!.startsWith(WeiboPatterns.WEB_COMPARE_HTTP) || uri.scheme!!.startsWith(
                    WeiboPatterns.WEB_COMPARE_HTTPS
                )) {
                // TODO:跳转链接页面
            } else if (uri.scheme!!.startsWith(WeiboPatterns.TOPIC_COMPARE_SCHEME)) {
                var topic = uRL
                topic = topic.substring(WeiboPatterns.TOPIC_SCHEME.length, topic.length)
                // TODO: 跳转#字话题页面
                widget.context.startActivity<MainActivity>()
            } else if (uri.scheme!!.startsWith(WeiboPatterns.MENTION_COMPARE_SCHEME)) {
                // TODO: 跳转@人页面
            }
        }
    }

    override fun updateDrawState(tp: TextPaint) {
        tp.color = Color.parseColor(mColor)
    }
}