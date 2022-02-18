package com.zhang.mydemo.kotlin.ui.widgetkt.tag

import android.text.SpannableString
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.TextView

/**
 * Created by liuz on 16/6/7.
 */
class ClickableTextViewMentionLinkOnTouchListener : OnTouchListener {
    private var find = false
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        val layout = (v as TextView).layout ?: return false
        val x = event.x.toInt()
        val y = event.y.toInt()
        val line = layout.getLineForVertical(y)
        val offset = layout.getOffsetForHorizontal(line, x.toFloat())
        val tv = v
        val value = SpannableString.valueOf(tv.text)
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                val urlSpans = value.getSpans(0, value.length, MyURLSpan::class.java)
                var findStart = 0
                var findEnd = 0
                for (urlSpan in urlSpans) {
                    val start = value.getSpanStart(urlSpan)
                    val end = value.getSpanEnd(urlSpan)
                    if (start <= offset && offset <= end) {
                        find = true
                        findStart = start
                        findEnd = end
                        break
                    }
                }
                val lineWidth = layout.getLineWidth(line)
                find = find and (lineWidth >= x)
                if (find) {
                    LongClickableLinkMovementMethod.instance?.onTouchEvent(tv, value, event)
                    val backgroundColorSpan = BackgroundColorSpan(-0x161617)
                    value.setSpan(
                        backgroundColorSpan, findStart, findEnd,
                        Spanned.SPAN_INCLUSIVE_INCLUSIVE
                    )
                    //Android has a bug, sometime TextView wont change its value when you modify SpannableString,
                    // so you must setText again, test on Android 4.3 Nexus4
                    tv.text = value
                }
                return find
            }
            MotionEvent.ACTION_MOVE -> if (find) {
                LongClickableLinkMovementMethod.instance?.onTouchEvent(tv, value, event)
            }
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> if (find) {
                LongClickableLinkMovementMethod.instance?.onTouchEvent(tv, value, event)
                LongClickableLinkMovementMethod.instance?.removeLongClickCallback()
                val backgroundColorSpans = value
                    .getSpans(0, value.length, BackgroundColorSpan::class.java)
                for (backgroundColorSpan in backgroundColorSpans) {
                    value.removeSpan(backgroundColorSpan)
                }
                tv.text = value
                find = false
            }
        }
        return false
    }
}