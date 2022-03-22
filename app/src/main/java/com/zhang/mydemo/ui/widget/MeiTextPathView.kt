package com.zhang.mydemo.ui.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.text.Layout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.zhang.mydemo.R
import com.zhang.utilslibiary.utils.DensityUtil

/**
 * desc:文字路径控件
 * author: wens
 * date: 2018/4/29.
 */
class MeiTextPathView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var mPaint: TextPaint? = null

    //默认值为5
    var textSize = 64
        private set

    //文本宽度
    private var mTextWidth = 0f
    private var mTextHeight = 0f
    var textColor = Color.RED
        private set

    //文本
    var text: String? = "MEI_S"
        private set
    var strokeWidth = 5f
        private set
    private var mFontPath: Path? = null
    private var mDstPath: Path? = null
    private var mPathMeasure: PathMeasure? = null
    private var mPathLength = 0f
    private var mCurrentLength = 0f

    //绘画部分长度
    protected var mStop = 0f
    private var mAnimation: ValueAnimator? = null

    //动画是否循环
    var isCycle: Boolean
        private set

    //动画时长
    var duration = 6000
        private set

    //是否自动开始
    var isAutoStart: Boolean
        private set

    private fun init() {
        mPaint = TextPaint()
        mPaint!!.isAntiAlias = true
        mPaint!!.textSize = textSize.toFloat()
        mPaint!!.style = Paint.Style.STROKE
        mPaint!!.color = textColor
        mPaint!!.strokeWidth = strokeWidth
        mFontPath = Path()
        mDstPath = Path()
        mPathMeasure = PathMeasure()
    }

    //初始化文字路径
    private fun initTextPath() {
        mPathLength = 0f
        mFontPath!!.reset()
        mDstPath!!.reset()
        if (null == text || text == "") {
            text = "mei_s"
        }
        mTextWidth = Layout.getDesiredWidth(text, mPaint)
        val metrics = mPaint!!.fontMetrics
        mTextHeight = metrics.bottom - metrics.top
        mPaint!!.getTextPath(text, 0, text!!.length, 0f, -metrics.ascent, mFontPath)
        mPathMeasure!!.setPath(mFontPath, false)
        mPathLength = mPathMeasure!!.length
        while (mPathMeasure!!.nextContour()) {
            mPathLength += mPathMeasure!!.length
        }
        if (isAutoStart) {
            post { startAnimation() }
        }
    }

    /**
     * 开始动画
     */
    fun startAnimation() {
        if (mAnimation == null) {
            mAnimation = ValueAnimator.ofFloat(0f, mPathLength)
        }
        if (mAnimation!!.isRunning) return
        if (isCycle) {
            mAnimation!!.repeatCount = -1
        } else {
            mAnimation!!.repeatCount = 0
        }
        mAnimation!!.duration = duration.toLong()
        mAnimation!!.addUpdateListener { valueAnimator ->
            mCurrentLength = valueAnimator.animatedValue as Float
            invalidate()
        }
        mAnimation!!.start()
    }

    /**
     * 停止动画
     */
    fun stopAnimation() {
        if (mAnimation != null && mAnimation!!.isRunning) mAnimation!!.cancel()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        var widthSize = MeasureSpec.getSize(widthMeasureSpec)
        var heightSize = MeasureSpec.getSize(heightMeasureSpec)
        //处理包裹内容的情况
        val warpDefaultSize = DensityUtil.dip2px(context, 100f)
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            heightSize = warpDefaultSize
            widthSize = heightSize
        } else if (widthMode == MeasureSpec.AT_MOST) {
            widthSize = warpDefaultSize
        } else if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = warpDefaultSize
        }
        setMeasuredDimension(widthSize, heightSize)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.save()
        mDstPath!!.reset()
        mStop = mCurrentLength

        //在中间绘制
        canvas.translate(width / 2 - mTextWidth / 2, 0f)
        canvas.translate(0f, height / 2 - mTextHeight / 2)

        //重置路径
        mPathMeasure!!.setPath(mFontPath, false)
        while (mStop > mPathMeasure!!.length) {
            mStop = mStop - mPathMeasure!!.length
            mPathMeasure!!.getSegment(0f, mPathMeasure!!.length, mDstPath, true)
            if (!mPathMeasure!!.nextContour()) {
                break
            }
        }
        mPathMeasure!!.getSegment(0f, mStop, mDstPath, true)
        canvas.drawPath(mDstPath!!, mPaint!!)
        canvas.restore()
    }

    /**
     * 设置画笔宽度
     * @param size
     * @return
     */
    fun setTextSize(size: Int): MeiTextPathView {
        textSize = size
        mPaint!!.textSize = textSize.toFloat()
        return this
    }

    /**
     * 设置画笔颜色
     * @param color
     * @return
     */
    fun setTextColor(color: Int): MeiTextPathView {
        textColor = color
        mPaint!!.color = textColor
        return this
    }

    /**
     * 设置绘制的文本
     * @param text
     * @return
     */
    fun setText(text: String?): MeiTextPathView {
        this.text = text
        initTextPath()
        return this
    }

    /**
     * 设置动画时长
     * @param duration
     * @return
     */
    fun setDuration(duration: Int): MeiTextPathView {
        this.duration = duration
        return this
    }

    /**
     * 设置描边宽度
     * @param width
     * @return
     */
    fun setStrokeWidth(width: Int): MeiTextPathView {
        strokeWidth = width.toFloat()
        mPaint!!.strokeWidth = strokeWidth
        return this
    }

    //是否循环
    fun setCycle(cycle: Boolean): MeiTextPathView {
        isCycle = cycle
        return this
    }

    //是否自动开始
    fun setAutoStart(autoStart: Boolean): MeiTextPathView {
        isAutoStart = autoStart
        return this
    }

    init {
        //关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        val ta = context.obtainStyledAttributes(attrs, R.styleable.MeiTextPathView)
        text = ta.getString(R.styleable.MeiTextPathView_text)
        textSize = ta.getDimensionPixelSize(R.styleable.MeiTextPathView_textSize, 108)
        textColor = ta.getColor(R.styleable.MeiTextPathView_textColor, Color.RED)
        duration = ta.getInt(R.styleable.MeiTextPathView_duration, 6000)
        strokeWidth =
            ta.getDimensionPixelOffset(R.styleable.MeiTextPathView_strokeWidth, 5).toFloat()
        isCycle = ta.getBoolean(R.styleable.MeiTextPathView_cycle, false)
        isAutoStart = ta.getBoolean(R.styleable.MeiTextPathView_autoStart, true)
        ta.recycle()
        init()
        initTextPath()
    }
}