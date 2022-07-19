package com.zhang.utilslibiary.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.style.*
import android.util.TypedValue
import android.view.View


/**
 *  SimpleSpStringBuilder是基于SpannableStringBuilder的简单封装，全称为SimpleSpannableStringBuildUtil。
 *  SimpleSpStringBuilder的存在主要是为了解决SpannableStringBuilder所存在的两方面问题：
 *   1.由于其在使用样式时需要指定字符区间，如果我们的字符串不是固定长度，或需要动态添加时，这会很繁琐，因为我们必须构建多个Span，
 *   且实时计算下区间范围。这会产生很大的代码量，减少代码可读性，且计算下标时容易出错。
 *   2.其在添加图片时会比较复杂，这特别体现在设置图片大小和间距时。
 *  SimpleSpStringBuilder的存在解决了以上问题，并且有以下优点：
 *   1.使用建造者模式，我们可以新建一个新的对象[.create()]或者使用原来的对象[.used()]，进行字符和图片的添加，代码更简洁，可读性很强。
 *   2.内部动态维护区间值start，end，防止了开发者由于区间计算错误导致的错误。
 *   3.增加setImage()和setView()方法，使其具有更强大功能。
 *  示例如下：
 *  ~~~
 *  示例1：
 *  val stringBuilder = SimpleSpStringBuilder(context)
 *         .create()
 *         .setText(text = "hello", textColorStr = "#A4A9B3")
 *         .setText(text = "：word", textColorStr = "#181E25")
 *         .builder()
 *
 *  输出：hello：word
 *  ~~~
 *
 * ~~~
 * 示例2：
 * val stringBuilder2 = SimpleSpStringBuilder(context)
 *     .used(SpannableStringBuilder())
 *     .setText(text = "hello", textColorStr = "#A4A9B3")
 *     .setText(text = "word", textColorStr = "#181E25")
 *     .setView(view = View(context), width = 20, height = 30)
 *     .builder()
 *  ~~~
 *  输出：hello：word （image）
 */

class SimpleSpStringBuilder(private val context: Context) {


    private var builder: SpannableStringBuilder? = null
    private var start = 0
    private var end = 0

    /**
     * 创建一个新的SpannableStringBuilder()
     *
     * @return SimpleSpStringBuilder
     */
    fun create(): SimpleSpStringBuilder {
        builder = SpannableStringBuilder()
        return this
    }


    /**
     *  使用一个已有的的SpannableStringBuilder()
     *
     *  @param sb
     *  @return SimpleSpStringBuilder
     */
    fun used(sb: SpannableStringBuilder): SimpleSpStringBuilder {
        builder = sb
        return this
    }

    /**
     *  返回SpannableStringBuilder
     *
     *  @return SpannableStringBuilder
     */
    fun builder(): SpannableStringBuilder {
        return builder ?: SpannableStringBuilder()
    }

    /**
     * 拼接一个text文本并且指定相关属性
     *
     * @param text  文本内容
     * @param styleSpan 设置文字格式
     * @param textColor  文本颜色
     * @param textColorStr  文本颜色（String类型）
     * @param backGroundColor  文本背景色
     * @param clickable  点击时间
     * @param relativeSize  相对大小
     * @param absoluteSize  绝对大小
     * @param scaleX  设置字体x轴缩放
     * @param style style
     * @param addUnderline  添加下划线
     * @param addStrikethrough  添加删除线
     * @param isSuperscript  设置文字为上标
     * @param isSubscript  设置文字为下标
     * @param flags  flags
     */
    fun setText(
        text: String?,
        styleSpan: StyleSpan? = null,
        textColor: Int? = -1,
        textColorStr: String? = "",
        backGroundColor: Int? = -1,
        clickable: (() -> Unit)? = null,
        relativeSize: Float? = -1f,
        absoluteSize: Int? = -1,
        scaleX: Float? = -1f,
        style: Int? = -1,
        addUnderline: Boolean? = false,
        addStrikethrough: Boolean? = false,
        isSuperscript: Boolean? = false,
        isSubscript: Boolean? = false,
        flags: Int? = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    ): SimpleSpStringBuilder {
        start = builder?.length ?: 0
        text?.length?.let {
            end = start + it
        }
        builder?.append(text)
        if (textColor != null && textColor != -1) {
            this.setTextColor(textColor, flags)
        }
        if (styleSpan != null) {
            this.setStyleSpan(styleSpan, flags)
        }
        if (textColorStr != null && textColorStr.isNotEmpty()) {
            this.setTextColor(Color.parseColor(textColorStr), flags)
        }
        if (backGroundColor != null && backGroundColor != -1) {
            this.setBackGroundColor(backGroundColor, flags)
        }
        if (clickable != null) {
            this.setClickableSpan(clickable, textColorStr!!, flags)
        }
        if (relativeSize != null && relativeSize != -1f) {
            this.setRelativeSize(relativeSize, flags)
        }
        if (absoluteSize != null && absoluteSize != -1) {
            this.setAbsoluteSize(absoluteSize, flags)
        }
        if (scaleX != null && scaleX != -1f) {
            this.setScaleXSpan(scaleX, flags)
        }
        if (style != null && style != -1) {
            this.setStyle(style, flags)
        }
        if (addUnderline != null && addUnderline != false) {
            this.setUnderline(flags)
        }
        if (addStrikethrough != null && addStrikethrough != false) {
            this.setStrikethrough(flags)
        }
        if (isSuperscript != null && isSuperscript != false) {
            this.setSuperscript(flags = flags)
        }
        if (isSubscript != null && isSubscript != false) {
            this.setSubscript(flags = flags)
        }
        return this
    }

    /**
     * 拼接一个图片并且指定相关属性
     *
     * @param  drawable  图片
     * @param  width  宽
     * @param  height  高
     * @param  marginStart 左间距
     * @param  marginTop  上间距
     * @param  isDpForValue  是否传入的是dp值
     * @param  imageStyle  图片风格
     * @param  flags  flags
     */
    fun setImage(
        drawable: Drawable?,
        width: Int = -1,
        height: Int = -1,
        marginStart: Int = 0,
        marginTop: Int = 0,
        isDpForValue: Boolean = true,
        imageStyle: Int = ImageSpan.ALIGN_BASELINE,
        flags: Int = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    ): SimpleSpStringBuilder {
        if (drawable == null) return this
        start = builder?.length ?: 0
        end += 1
        builder?.append(" ")
        if (width != -1 || height != -1) {
            val scale = drawable.intrinsicWidth / drawable.intrinsicHeight
            val iconWidth = if (width == -1) scale * width else width
            val iconHeight = if (height == -1) scale * height else height
            if (isDpForValue) {
                drawable.setBounds(
                    dp2px(marginStart),
                    dp2px(marginTop),
                    dp2px(iconWidth + marginStart),
                    dp2px(iconHeight + marginTop)
                )
            } else {
                drawable.setBounds(marginStart, marginTop, width + marginStart, height + marginTop)
            }
        }
        val imageSpan = ImageSpan(drawable, imageStyle)
        builder?.setSpan(imageSpan, start, end, flags)
        return this
    }

    /**
     * 设置View
     *
     * @param  view  图片
     * @param  width  宽
     * @param  height  高
     * @param  marginStart 左间距
     * @param  marginTop  上间距
     * @param  isDpForValue  是否传入的是dp值
     * @param  imageStyle  图片风格
     * @param  flags  flags
     */
    fun setView(
        view: View,
        width: Int = -1,
        height: Int = -1,
        marginStart: Int = 0,
        marginTop: Int = 0,
        isDpForValue: Boolean = true,
        imageStyle: Int = ImageSpan.ALIGN_BASELINE,
        flags: Int = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    ): SimpleSpStringBuilder {
        val drawable = getDrawable(view)
        this.setImage(
            drawable,
            width,
            height,
            marginStart,
            marginTop,
            isDpForValue,
            imageStyle,
            flags
        )
        return this
    }

    /**
     *  使用ForegroundColorSpan设置颜色
     */
    private fun setTextColor(
        color: Int, flags: Int? = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    ): SimpleSpStringBuilder {
        val foregroundColorSpan = ForegroundColorSpan(color)
        builder?.setSpan(
            foregroundColorSpan,
            start,
            end,
            flags ?: Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return this
    }

    /**
     * 设置 文字格式
     * @param styleSpan StyleSpan?
     * @param flags Int?
     * @return SimpleSpStringBuilder
     */
    private fun setStyleSpan(
        styleSpan: StyleSpan?, flags: Int? = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    ): SimpleSpStringBuilder {
        builder?.setSpan(
            styleSpan,
            start,
            end,
            flags ?: Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return this
    }

    /**
     *  使用BackGroudColorSpan设置背景颜色
     */
    private fun setBackGroundColor(
        color: Int, flags: Int? = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    ): SimpleSpStringBuilder {
        val backgroundColorSpan = BackgroundColorSpan(color)
        builder?.setSpan(
            backgroundColorSpan,
            start,
            end,
            flags ?: Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return this
    }

    /**
     *  使用ClickableSpan设置点击效果
     */
    private fun setClickableSpan(
        onClick: () -> Unit,
        textColor: String,
        flags: Int? = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    ): SimpleSpStringBuilder {
        builder?.setSpan(object : ClickableSpan() {
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.parseColor(textColor)
            }

            override fun onClick(widget: View) {
                onClick.invoke()
            }

        }, start, end, flags ?: Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return this
    }

    /**
     *  使用RelativeSizeSpan设置字体的相对大小
     */
    private fun setRelativeSize(
        value: Float, flags: Int? = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    ): SimpleSpStringBuilder {
        val relativeSizeSpan = RelativeSizeSpan(value)
        builder?.setSpan(relativeSizeSpan, start, end, flags ?: Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return this
    }

    /**
     *  使用AbsoluteSizeSpan设置字体的绝对大小(dp)
     */
    private fun setAbsoluteSize(
        value: Int, flags: Int? = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    ): SimpleSpStringBuilder {
        val absoluteSizeSpan = AbsoluteSizeSpan(value, true)
        builder?.setSpan(absoluteSizeSpan, start, end, flags ?: Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return this
    }

    /**
     *  使用ScaleXSpan设置字体x轴缩放
     */
    private fun setScaleXSpan(
        value: Float, flags: Int? = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    ): SimpleSpStringBuilder {
        val scaleXSpan = ScaleXSpan(value)
        builder?.setSpan(scaleXSpan, start, end, flags ?: Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return this
    }

    /**
     *  使用StyleSpan设置文字样式
     */
    private fun setStyle(
        style: Int, flags: Int? = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    ): SimpleSpStringBuilder {
        val boldSpan = StyleSpan(style)
        builder?.setSpan(boldSpan, start, end, flags ?: Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return this
    }

    /**
     *  使用UnderlineSpan设置文字下划线
     */
    private fun setUnderline(
        flags: Int? = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    ): SimpleSpStringBuilder {
        val underlineSpan = UnderlineSpan()
        builder?.setSpan(underlineSpan, start, end, flags ?: Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return this
    }

    /**
     *  使用StrikethroughSpan设置文字删除线
     */
    private fun setStrikethrough(
        flags: Int? = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    ): SimpleSpStringBuilder {
        val strikethroughSpan = StrikethroughSpan()
        builder?.setSpan(strikethroughSpan, start, end, flags ?: Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return this
    }

    /**
     *  使用SuperscriptSpan设置文字为上标
     *
     *  @value 缩放大小
     */
    private fun setSuperscript(
        value: Float = -1f,
        flags: Int? = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    ): SimpleSpStringBuilder {
        if (value != -1f) {
            val relativeSizeSpan = RelativeSizeSpan(value)
            builder?.setSpan(
                relativeSizeSpan,
                start,
                end,
                flags ?: Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        val superscriptSpan = SuperscriptSpan()
        builder?.setSpan(superscriptSpan, start, end, flags ?: Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return this
    }

    /**
     *  使用SubscriptSpan设置文字为下标
     *
     *  @value 缩放大小
     */
    private fun setSubscript(
        value: Float = -1f,
        flags: Int? = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    ): SimpleSpStringBuilder {
        if (value != -1f) {
            val relativeSizeSpan = RelativeSizeSpan(value)
            builder?.setSpan(
                relativeSizeSpan,
                start,
                end,
                flags ?: Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        val subscriptSpan = SubscriptSpan()
        builder?.setSpan(subscriptSpan, start, end, flags ?: Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return this
    }


    private fun getDrawable(view: View): Drawable {
        val bitmap: Bitmap = createViewBitmap(view)
        return BitmapDrawable(context.resources, bitmap)
    }

    private fun createViewBitmap(v: View): Bitmap {
        v.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        v.layout(0, 0, v.measuredWidth, v.measuredHeight)
        val bitmap = Bitmap.createBitmap(v.width, v.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        v.draw(canvas)
        return bitmap
    }

    private fun dp2px(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }
}