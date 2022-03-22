package com.zhang.mydemo.ui.widget

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewConfiguration
import android.widget.TextView
import androidx.core.view.MotionEventCompat
import java.util.*
import java.util.regex.Pattern
import kotlin.math.abs

/**
 * 作者: by KEN on 2018/7/20 18.
 * 邮箱: gr201655@163.com
 */
class TagTextUtil {
    class Section internal constructor(//文字起始索引
        var start: Int, //文字结尾索引
        var end: Int, //话题类型
        var type: Int, //数据返回中的实际话题string
        var name: String, // 标签索引  用于在标签list中查询对应数据
        var index: Int, // @人 中的 userId   如果不是@人  默认为0（该数据没有用）
        var userId: Int
    ) {

        companion object {
            //类型可自己拓展
            const val TOPIC = 1 // ##普通标签或者话题
            const val LOCATION = 2 // ##地址标签
            const val PRICE = 3 // ##价格标签
            const val BRAND = 4 // ##品牌标签
            const val AT = 5 //@某人
        }
    }

    private var clickListener: ClickListener? = null
    fun setClickListener(clickListener: ClickListener?) {
        this.clickListener = clickListener
    }

    /**
     *
     * 点击监听
     */
    interface ClickListener {
        fun click(section: Section?)
    }

    fun getTagContent(
        source: String,
        context: Context?,
        textView: TextView,
        clickListener: ClickListener?
    ): SpannableStringBuilder {
        var source = source
        this.clickListener = clickListener
        val sections = ArrayList<Section>()

        //设置正则
        val pattern = Pattern.compile(ALL)
        val matcher = pattern.matcher(source)
        var replaceCount = 0
        while (matcher.find()) {
            val topic = matcher.group()

            //处理##话题
            if (topic != null) {
                try {  //由于数据出错的可能性太大  可能用户恰好写上了标签格式  所以捕获异常
                    val start = matcher.start(1)
                    val i = topic.indexOf("&", 2)
                    val index = topic.substring(2, i)
                    var valueIndex: Int
                    valueIndex = try {
                        index.toInt()
                    } catch (e: Exception) {
                        continue
                    }
                    var last: Int
                    var typeString: String
                    var type: Int
                    //对多种不同的标签进行 前置符处理，根据项目需求更改
                    if (topic.endsWith("#^")) {
                        typeString = "#"
                        type = Section.TOPIC
                        last = topic.lastIndexOf("#")
                    } else if (topic.endsWith("$^")) {
                        typeString = "$"
                        type = Section.PRICE
                        last = topic.lastIndexOf("$")
                    } else if (topic.endsWith("!^")) {
                        type = Section.BRAND
                        last = topic.lastIndexOf("!")
                        typeString = "!"
                    } else {
                        type = Section.AT
                        last = topic.lastIndexOf("@")
                        typeString = "@"
                    }
                    val content = topic.substring(i + 2, last)
                    var realStart: Int
                    realStart = start - replaceCount
                    source = source.replace(matcher.group(), typeString + content)
                    val section = Section(
                        realStart,
                        realStart + content.length + 1,
                        type,
                        topic,
                        valueIndex,
                        if (type == Section.AT) valueIndex else 0
                    )
                    sections.add(section)
                    replaceCount += topic.length - content.length - 1
                } catch (e: Exception) {
                    Log.e(TAG, "匹配异常 == " + e.message)
                }
            }
        }
        val spannableStringBuilder = SpannableStringBuilder(source)
        for (i in sections.indices) {
            val foregroundColorSpan = ForegroundColorSpan(Color.BLUE)
            spannableStringBuilder.setSpan(
                foregroundColorSpan,
                sections[i].start,
                sections[i].end,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
        }
        val span = BackgroundColorSpan(Color.RED)
        val slop = ViewConfiguration.get(context).scaledTouchSlop
        val touchListener: OnTouchListener = object : OnTouchListener {
            var downX = 0
            var downY = 0
            var downSection: Section? = null
            var id = 0
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                val action = MotionEventCompat.getActionMasked(event)
                val layout = textView.layout
                if (layout == null) {
                    Log.d(TAG, "layout is null")
                    return false
                }
                var line = 0
                var index = 0
                when (action) {
                    MotionEvent.ACTION_DOWN -> {
                        val actionIndex = event.actionIndex
                        id = event.getPointerId(actionIndex)
                        downX = event.getX(actionIndex).toInt()
                        downY = event.getY(actionIndex).toInt()
                        Log.d(TAG, "ACTION_down,x:" + event.x + ",y:" + event.y)
                        line = layout.getLineForVertical(
                            textView.scrollY + event.y
                                .toInt()
                        )
                        index = layout.getOffsetForHorizontal(line, event.x)
                        val lastRight = layout.getLineRight(line).toInt()
                        Log.d(TAG, "lastRight:$lastRight")
                        if (lastRight < event.x) {  //文字最后为话题时，如果点击在最后一行话题之后，也会造成话题被选中效果
                            return false
                        }
                        Log.d(TAG, " index:" + index + ",sections:" + sections.size)
                        for (section in sections) {
                            if (index >= section.start && index <= section.end) {
                                spannableStringBuilder.setSpan(
                                    span,
                                    section.start,
                                    section.end,
                                    Spannable.SPAN_INCLUSIVE_INCLUSIVE
                                )
                                downSection = section
                                textView.text = spannableStringBuilder
                                textView.parent.requestDisallowInterceptTouchEvent(true) //不允许父view拦截
                                Log.d(TAG, "downSection == sssss" + downSection.toString())
                                return true
                            }
                        }
                        return false
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val indexMove = event.findPointerIndex(id)
                        val currentX = event.getX(indexMove).toInt()
                        val currentY = event.getY(indexMove).toInt()
                        Log.d(TAG, "ACTION_MOVE,x:$currentX,y:$currentY")
                        if (abs(currentX - downX) < slop && abs(currentY - downY) < slop) {
                            if (downSection == null) {
                                Log.d(TAG, "downSection is null")
                                textView.parent.requestDisallowInterceptTouchEvent(false) //允许父view拦截
                                return false
                            }
                            return false
                        }
                        downSection = null
                        textView.parent.requestDisallowInterceptTouchEvent(false) //允许父view拦截
                        Log.d(TAG, "ACTION_CANCEL")
                        val indexUp = event.findPointerIndex(id)
                        spannableStringBuilder.removeSpan(span)
                        textView.text = spannableStringBuilder
                        val upX = event.getX(indexUp).toInt()
                        val upY = event.getY(indexUp).toInt()
                        Log.d(TAG, "ACTION_UP,x:$upX,y:$upY")
                        if (Math.abs(upX - downX) < slop && Math.abs(upY - downY) < slop) {
                            //TODO 此处回调
                            downSection = if (downSection != null) {
                                Log.e(TAG, "onTouch: dosss  == $downSection")
                                /**
                                 * 此处回调
                                 */
                                if (this@TagTextUtil.clickListener != null) this@TagTextUtil.clickListener!!.click(
                                    downSection
                                )
                                null
                            } else {
                                return false
                            }
                        } else {
                            Log.d(TAG, "false")
                            downSection = null
                            return false
                        }
                    }
                    MotionEvent.ACTION_CANCEL -> {
                        Log.d(TAG, "ACTION_CANCEL")
                        val indexUp = event.findPointerIndex(id)
                        spannableStringBuilder.removeSpan(span)
                        textView.text = spannableStringBuilder
                        val upX = event.getX(indexUp).toInt()
                        val upY = event.getY(indexUp).toInt()
                        Log.d(TAG, "ACTION_UP,x:$upX,y:$upY")
                        if (Math.abs(upX - downX) < slop && Math.abs(upY - downY) < slop) {
                            downSection = if (downSection != null) {
                                Log.e(TAG, "onTouch: dosss  == $downSection")
                                if (this@TagTextUtil.clickListener != null) this@TagTextUtil.clickListener!!.click(
                                    downSection
                                )
                                null
                            } else {
                                return false
                            }
                        } else {
                            Log.d(TAG, "false")
                            downSection = null
                            return false
                        }
                    }
                    MotionEvent.ACTION_UP -> {
                        val indexUp = event.findPointerIndex(id)
                        spannableStringBuilder.removeSpan(span)
                        textView.text = spannableStringBuilder
                        val upX = event.getX(indexUp).toInt()
                        val upY = event.getY(indexUp).toInt()
                        Log.d(TAG, "ACTION_UP,x:$upX,y:$upY")
                        if (Math.abs(upX - downX) < slop && Math.abs(upY - downY) < slop) {
                            downSection = if (downSection != null) {
                                Log.e(TAG, "onTouch: dosss  == $downSection")
                                if (this@TagTextUtil.clickListener != null) this@TagTextUtil.clickListener!!.click(
                                    downSection
                                )
                                null
                            } else {
                                return false
                            }
                        } else {
                            Log.d(TAG, "false")
                            downSection = null
                            return false
                        }
                    }
                }
                Log.d(TAG, "true")
                return true
            }
        }
        textView.setOnTouchListener(touchListener)
        return spannableStringBuilder
    }

    companion object {
        private const val TAG = "zgr"
        private const val TOPIC = "(\\^[#$!@]).+?([#!$@]\\^)" // ##标签正则匹配
        private const val ALL = TOPIC
    }
}