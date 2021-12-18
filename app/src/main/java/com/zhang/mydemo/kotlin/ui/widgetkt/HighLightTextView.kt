package com.zhang.mydemo.kotlin.ui.widgetkt

import android.content.Context
import android.content.res.TypedArray
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.zhang.mydemo.R
import java.util.regex.Matcher
import java.util.regex.Pattern


/**
 *@author hugo
 *@time    2021/9/17下午1:58
 *@project  arm
 *Think Twice, Code Once!
 */
class HighLightTextView : AppCompatTextView {

    //原始文本
    private var mOriginalText = ""

    //关键字
    var highLightText: String = ""

    //关键字颜色
    var mSignTextColor = 0

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initializeAttrs(attrs)
    }

    //初始化自定义属性
    private fun initializeAttrs(attrs: AttributeSet?) {
        val typedArray: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.HighLightTextView)
        //获取关键字
        highLightText =
            typedArray.getString(R.styleable.HighLightTextView_heightLightText)?:""
        //获取关键字颜色
        mSignTextColor = typedArray.getColor(
            R.styleable.HighLightTextView_heightLightColor,
            textColors.defaultColor
        )
        typedArray.recycle()
    }

    //重写setText方法
    override fun setText(text: CharSequence, type: BufferType?) {
        mOriginalText = text.toString()
        super.setText(matcherSignText(), type)
    }

    /**
     * 匹配关键字，并返回SpannableStringBuilder对象
     * @return
     */
    private fun matcherSignText(): SpannableStringBuilder {
        if (TextUtils.isEmpty(mOriginalText)) {
            return SpannableStringBuilder("")
        }
        if (TextUtils.isEmpty(highLightText)) {
            return SpannableStringBuilder(mOriginalText)
        }
        val builder = SpannableStringBuilder(mOriginalText)

        val foregroundColorSpan = ForegroundColorSpan(mSignTextColor)
        //  for (element in highLightText) {
        //       val c = element
        val p: Pattern = Pattern.compile(highLightText)
        val m: Matcher = p.matcher(mOriginalText)

      //  for(i:Int in 0..mOriginalText.length){
            while (m.find()) {
                val start: Int = m.start()
                val end: Int = m.end()
                builder.setSpan(
                    foregroundColorSpan, start, end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
     //   }
        //  }
        return builder
    }

    /**
     * 设置关键字
     * @param signText 关键字
     */
    fun setHightLightText(signText: String) {
        highLightText = signText
        text = mOriginalText
    }

    /**
     * 设置关键字颜色
     * @param signTextColor 关键字颜色
     */
    fun setHightLightColor(signTextColor: Int) {
        mSignTextColor = signTextColor
        text = mOriginalText
    }
}