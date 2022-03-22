package com.zhang.mydemo.ui.widget.tag

import android.os.Handler
import android.text.NoCopySpan.Concrete
import android.text.Selection
import android.text.Spannable
import android.text.method.ScrollingMovementMethod
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.widget.TextView

/**
 * Created by liuz on 16/6/7.
 *
 * A movement method that traverses links in the text buffer and scrolls if necessary.
 * Supports clicking on links with DPad Center or Enter.
 */
class LinkLongClickable : ScrollingMovementMethod() {
    private var mHasPerformedLongPress = false
    private var mPendingCheckForLongPress: CheckForLongPress? = null
    var isPressed = false
        private set
    private val handler = Handler()
    private var longClickable = true
    fun setLongClickable(value: Boolean) {
        longClickable = value
    }

    override fun handleMovementKey(
        widget: TextView, buffer: Spannable, keyCode: Int,
        movementMetaState: Int, event: KeyEvent
    ): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_DPAD_CENTER, KeyEvent.KEYCODE_ENTER -> if (KeyEvent.metaStateHasNoModifiers(
                    movementMetaState
                )
            ) {
                if (event.action == KeyEvent.ACTION_DOWN && event.repeatCount == 0 && action(
                        CLICK,
                        widget,
                        buffer
                    )
                ) {
                    return true
                }
            }
        }
        return super.handleMovementKey(widget, buffer, keyCode, movementMetaState, event)
    }

    override fun up(widget: TextView, buffer: Spannable): Boolean {
        return if (action(UP, widget, buffer)) {
            true
        } else super.up(widget, buffer)
    }

    override fun down(widget: TextView, buffer: Spannable): Boolean {
        return if (action(DOWN, widget, buffer)) {
            true
        } else super.down(widget, buffer)
    }

    override fun left(widget: TextView, buffer: Spannable): Boolean {
        return if (action(UP, widget, buffer)) {
            true
        } else super.left(widget, buffer)
    }

    override fun right(widget: TextView, buffer: Spannable): Boolean {
        return if (action(DOWN, widget, buffer)) {
            true
        } else super.right(widget, buffer)
    }

    private fun action(what: Int, widget: TextView, buffer: Spannable): Boolean {
        val layout = widget.layout
        val padding = widget.totalPaddingTop +
                widget.totalPaddingBottom
        val areatop = widget.scrollY
        val areabot = areatop + widget.height - padding
        val linetop = layout.getLineForVertical(areatop)
        val linebot = layout.getLineForVertical(areabot)
        val first = layout.getLineStart(linetop)
        val last = layout.getLineEnd(linebot)
        val candidates = buffer.getSpans(first, last, MyURLSpan::class.java)
        val a = Selection.getSelectionStart(buffer)
        val b = Selection.getSelectionEnd(buffer)
        var selStart = Math.min(a, b)
        var selEnd = Math.max(a, b)
        if (selStart < 0) {
            if (buffer.getSpanStart(FROM_BELOW) >= 0) {
                selEnd = buffer.length
                selStart = selEnd
            }
        }
        if (selStart > last) {
            selEnd = Int.MAX_VALUE
            selStart = selEnd
        }
        if (selEnd < first) {
            selEnd = -1
            selStart = selEnd
        }
        var beststart: Int
        var bestend: Int
        when (what) {
            CLICK -> {
                if (selStart == selEnd) {
                    return false
                }
                val link = buffer.getSpans(selStart, selEnd, MyURLSpan::class.java)
                if (link.size != 1) {
                    return false
                }
                link[0].onClick(widget)
            }
            UP -> {
                beststart = -1
                bestend = -1
                var i = 0
                while (i < candidates.size) {
                    val end = buffer.getSpanEnd(candidates[i])
                    if (end < selEnd || selStart == selEnd) {
                        if (end > bestend) {
                            beststart = buffer.getSpanStart(candidates[i])
                            bestend = end
                        }
                    }
                    i++
                }
                if (beststart >= 0) {
                    Selection.setSelection(buffer, bestend, beststart)
                    return true
                }
            }
            DOWN -> {
                beststart = Int.MAX_VALUE
                bestend = Int.MAX_VALUE
                var i = 0
                while (i < candidates.size) {
                    val start = buffer.getSpanStart(candidates[i])
                    if (start > selStart || selStart == selEnd) {
                        if (start < beststart) {
                            beststart = start
                            bestend = buffer.getSpanEnd(candidates[i])
                        }
                    }
                    i++
                }
                if (bestend < Int.MAX_VALUE) {
                    Selection.setSelection(buffer, beststart, bestend)
                    return true
                }
            }
        }
        return false
    }

    private var lastEvent = FloatArray(2)
    override fun onTouchEvent(
        widget: TextView, buffer: Spannable,
        event: MotionEvent
    ): Boolean {
        val action = event.action
        if (action == MotionEvent.ACTION_UP ||
            action == MotionEvent.ACTION_DOWN
        ) {
            val layout = widget.layout ?: return super.onTouchEvent(widget, buffer, event)
            var x = event.x.toInt()
            var y = event.y.toInt()
            x -= widget.totalPaddingLeft
            y -= widget.totalPaddingTop
            x += widget.scrollX
            y += widget.scrollY
            val line = layout.getLineForVertical(y)
            val off = layout.getOffsetForHorizontal(line, x.toFloat())
            val link = buffer.getSpans(off, off, MyURLSpan::class.java)
            if (link.size != 0) {
                if (action == MotionEvent.ACTION_UP) {
                    if (!mHasPerformedLongPress) {
                        link[0].onClick(widget)
                    }
                    isPressed = false
                    lastEvent = FloatArray(2)
                } else if (action == MotionEvent.ACTION_DOWN) {
                    isPressed = true
                    lastEvent[0] = event.x
                    lastEvent[1] = event.y
                    checkForLongClick(link, widget)
                }
                return true
            }
        } else if (action == MotionEvent.ACTION_MOVE) {
            val position = floatArrayOf(event.x, event.y)
            //            int slop = ViewConfiguration.get(widget.getContext()).getScaledTouchSlop();
            val slop = 6
            val xInstance = Math.abs(lastEvent[0] - position[0])
            val yInstance = Math.abs(lastEvent[1] - position[1])
            val instance = Math.sqrt(Math.hypot(xInstance.toDouble(), yInstance.toDouble()))
            if (instance > slop) {
                isPressed = false
            }
        } else if (action == MotionEvent.ACTION_CANCEL) {
            isPressed = false
            lastEvent = FloatArray(2)
        } else {
            isPressed = false
            lastEvent = FloatArray(2)
        }
        return super.onTouchEvent(widget, buffer, event)
    }

    private fun checkForLongClick(spans: Array<MyURLSpan>, widget: View) {
        mHasPerformedLongPress = false
        mPendingCheckForLongPress = CheckForLongPress(spans, widget)
        handler.postDelayed(
            mPendingCheckForLongPress!!,
            ViewConfiguration.getLongPressTimeout().toLong()
        )
    }

    fun removeLongClickCallback() {
        if (mPendingCheckForLongPress != null) {
            handler.removeCallbacks(mPendingCheckForLongPress!!)
            mPendingCheckForLongPress = null
        }
    }

    internal inner class CheckForLongPress(var spans: Array<MyURLSpan>, var widget: View) :
        Runnable {
        override fun run() {
            if (isPressed && longClickable) {
//                spans[0].onLongClick(widget);
                mHasPerformedLongPress = true
            }
        }
    }

    private fun performLongClick() {}
    override fun initialize(widget: TextView, text: Spannable) {
        Selection.removeSelection(text)
        text.removeSpan(FROM_BELOW)
    }

    override fun onTakeFocus(view: TextView, text: Spannable, dir: Int) {
        Selection.removeSelection(text)
        if (dir and View.FOCUS_BACKWARD != 0) {
            text.setSpan(FROM_BELOW, 0, 0, Spannable.SPAN_POINT_POINT)
        } else {
            text.removeSpan(FROM_BELOW)
        }
    }

    companion object {
        private const val CLICK = 1
        private const val UP = 2
        private const val DOWN = 3
        val instance: LinkLongClickable?
            get() {
                if (sInstance == null) {
                    sInstance = LinkLongClickable()
                }
                return sInstance
            }
        private var sInstance: LinkLongClickable? = null
        private val FROM_BELOW: Any = Concrete()
    }
}