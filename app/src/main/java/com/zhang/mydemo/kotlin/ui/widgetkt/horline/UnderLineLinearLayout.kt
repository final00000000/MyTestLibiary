package com.zhang.mydemo.kotlin.ui.widgetkt.horline

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.widget.LinearLayout
import com.zhang.mydemo.R

/**
 * Created by 大灯泡 on 2016/1/21.
 * 简易带有时间轴的linearlayout
 */
class UnderLineLinearLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {
    //=============================================================元素定义
    var icon: Bitmap? = null

    //line location
    private var lineMarginSide: Int
    private var lineDynamicDimen: Int

    //line property
    private var lineStrokeWidth: Int
    private var lineColor: Int

    //point property
    private var pointSize: Int
    private var pointColor: Int

    //=============================================================paint
    private var linePaint: Paint? = null
    private var pointPaint: Paint? = null

    //=============================================================其他辅助参数
    //第一个点的位置
    private var firstX = 0
    private var firstY = 0

    //最后一个图的位置
    private var lastX = 0
    private var lastY = 0

    //默认垂直
    private var curOrientation = VERTICAL

    //line gravity(默认垂直的左边)
    private var lineGravity = GRAVITY_LEFT
    private var mContext: Context? = null

    //开关
    private var drawLine = true
    private var rootLeft = 0
    private var rootMiddle = 0
    private var rootRight = 0
    private var rootTop = 0
    private var rootBottom = 0

    //参照点
    private var sideRelative = 0
    private fun initView(context: Context) {
        mContext = context
        linePaint = Paint()
        linePaint!!.isAntiAlias = true
        linePaint!!.isDither = true
        linePaint!!.color = lineColor
        linePaint!!.strokeWidth = lineStrokeWidth.toFloat()
        linePaint!!.style = Paint.Style.FILL_AND_STROKE
        pointPaint = Paint()
        pointPaint!!.isAntiAlias = true
        pointPaint!!.isDither = true
        pointPaint!!.color = pointColor
        pointPaint!!.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        calculateSideRelative()
        if (drawLine) {
            drawTimeLine(canvas)
        }
    }

    private fun calculateSideRelative() {
        rootLeft = left
        rootTop = top
        rootRight = right
        rootBottom = bottom
        if (curOrientation == VERTICAL) rootMiddle = rootLeft + rootRight shr 1
        if (curOrientation == HORIZONTAL) rootMiddle = rootTop + rootBottom shr 1
        val isCorrect = lineGravity == GRAVITY_MIDDLE || (lineGravity + curOrientation) % 2 != 0
        if (isCorrect) {
            when (lineGravity) {
                GRAVITY_TOP -> sideRelative = rootTop
                GRAVITY_BOTTOM -> sideRelative = rootBottom
                GRAVITY_LEFT -> sideRelative = rootLeft
                GRAVITY_RIGHT -> sideRelative = rootRight
                GRAVITY_MIDDLE -> sideRelative = rootMiddle
            }
        } else {
            sideRelative = 0
        }
    }

    private fun drawTimeLine(canvas: Canvas) {
        val childCount = childCount
        if (childCount > 0) {
            //大于1，证明至少有2个，也就是第一个和第二个之间连成线，第一个和最后一个分别有点/icon
            if (childCount > 1) {
                when (curOrientation) {
                    VERTICAL -> {
                        drawFirstChildViewVertical(canvas)
                        drawLastChildViewVertical(canvas)
                        drawBetweenLineVertical(canvas)
                    }
                    HORIZONTAL -> {
                        drawFirstChildViewHorizontal(canvas)
                        drawLastChildViewHorizontal(canvas)
                        drawBetweenLineHorizontal(canvas)
                    }
                    else -> {}
                }
            } else if (childCount == 1) {
                when (curOrientation) {
                    VERTICAL -> drawFirstChildViewVertical(canvas)
                    HORIZONTAL -> drawFirstChildViewHorizontal(canvas)
                    else -> {}
                }
            }
        }
    }

    //=============================================================Vertical Draw
    private fun drawFirstChildViewVertical(canvas: Canvas) {
        if (getChildAt(0) != null) {
            val top = getChildAt(0).top
            //记录值
            firstX =
                if (sideRelative >= rootMiddle) sideRelative - lineMarginSide else sideRelative + lineMarginSide
            firstY = top + getChildAt(0).paddingTop + lineDynamicDimen
            //画一个圆
            canvas.drawCircle(firstX.toFloat(), firstY.toFloat(), pointSize.toFloat(), pointPaint!!)
        }
    }

    private fun drawLastChildViewVertical(canvas: Canvas) {
        if (getChildAt(childCount - 1) != null) {
            val top = getChildAt(childCount - 1).top
            //记录值
            lastX =
                (if (sideRelative >= rootMiddle) sideRelative - lineMarginSide else sideRelative + lineMarginSide) - (icon!!.getWidth() shr 1)
            lastY = top + getChildAt(childCount - 1).paddingTop + lineDynamicDimen
            //画一个图
            canvas.drawBitmap(icon!!, lastX.toFloat(), lastY.toFloat(), null)
        }
    }

    private fun drawBetweenLineVertical(canvas: Canvas) {
        //画剩下的
        canvas.drawLine(firstX.toFloat(),
            firstY.toFloat(),
            firstX.toFloat(),
            lastY.toFloat(),
            linePaint!!)
        for (i in 0 until childCount - 1) {
            //画了线，就画圆
            if (getChildAt(i) != null && i != 0) {
                val top = getChildAt(i).top
                //记录值
                val Y = top + getChildAt(i).paddingTop + lineDynamicDimen
                canvas.drawCircle(firstX.toFloat(), Y.toFloat(), pointSize.toFloat(), pointPaint!!)
            }
        }
    }

    //=============================================================Horizontal Draw
    private fun drawFirstChildViewHorizontal(canvas: Canvas) {
        if (getChildAt(0) != null) {
            val left = getChildAt(0).left
            //记录值
            firstX = left + getChildAt(0).paddingLeft + lineDynamicDimen
            firstY =
                if (sideRelative >= rootMiddle) sideRelative - lineMarginSide else sideRelative + lineMarginSide
            //画一个圆
            canvas.drawCircle(firstX.toFloat(), firstY.toFloat(), pointSize.toFloat(), pointPaint!!)
        }
    }

    private fun drawLastChildViewHorizontal(canvas: Canvas) {
        if (getChildAt(childCount - 1) != null) {
            val left = getChildAt(childCount - 1).left
            //记录值
            lastX = left + getChildAt(childCount - 1).paddingLeft + lineDynamicDimen
            lastY =
                (if (sideRelative >= rootMiddle) sideRelative - lineMarginSide else sideRelative + lineMarginSide) - (icon
                !!.getWidth() shr 1)
            //画一个图
            canvas.drawBitmap(icon!!, lastX.toFloat(), lastY.toFloat(), null)
        }
    }

    private fun drawBetweenLineHorizontal(canvas: Canvas) {
        //画剩下的线
        canvas.drawLine(firstX.toFloat(),
            firstY.toFloat(),
            lastX.toFloat(),
            firstY.toFloat(),
            linePaint!!)
        for (i in 0 until childCount - 1) {
            //画了线，就画圆
            if (getChildAt(i) != null && i != 0) {
                val left = getChildAt(i).left
                //记录值
                val x = left + getChildAt(i).paddingLeft + lineDynamicDimen
                canvas.drawCircle(x.toFloat(), firstY.toFloat(), pointSize.toFloat(), pointPaint!!)
            }
        }
    }

    //=============================================================Getter/Setter
    override fun setOrientation(orientation: Int) {
        super.setOrientation(orientation)
        curOrientation = orientation
        invalidate()
    }

    fun getLineStrokeWidth(): Int {
        return lineStrokeWidth
    }

    fun setLineStrokeWidth(lineStrokeWidth: Int) {
        this.lineStrokeWidth = lineStrokeWidth
        invalidate()
    }

    fun isDrawLine(): Boolean {
        return drawLine
    }

    fun setDrawLine(drawLine: Boolean) {
        this.drawLine = drawLine
        invalidate()
    }

    fun getLinePaint(): Paint? {
        return linePaint
    }

    fun setLinePaint(linePaint: Paint?) {
        this.linePaint = linePaint
        invalidate()
    }

    fun getPointSize(): Int {
        return pointSize
    }

    fun setPointSize(pointSize: Int) {
        this.pointSize = pointSize
        invalidate()
    }

    fun getPointColor(): Int {
        return pointColor
    }

    fun setPointColor(pointColor: Int) {
        this.pointColor = pointColor
        invalidate()
    }

    fun getPointPaint(): Paint? {
        return pointPaint
    }

    fun setPointPaint(pointPaint: Paint?) {
        this.pointPaint = pointPaint
        invalidate()
    }

    fun getLineColor(): Int {
        return lineColor
    }

    fun setLineColor(lineColor: Int) {
        this.lineColor = lineColor
        invalidate()
    }

    fun getLineMarginSide(): Int {
        return lineMarginSide
    }

    fun setLineMarginSide(lineMarginSide: Int) {
        this.lineMarginSide = lineMarginSide
        invalidate()
    }

    fun getLineDynamicDimen(): Int {
        return lineDynamicDimen
    }

    fun setLineDynamicDimen(lineDynamicDimen: Int) {
        this.lineDynamicDimen = lineDynamicDimen
        invalidate()
    }

    fun setIcon(resId: Int) {
        if (resId == 0) return
        val temp = mContext!!.resources.getDrawable(resId) as BitmapDrawable
        if (temp != null) icon = temp.bitmap
        invalidate()
    }

    fun getLineGravity(): Int {
        return lineGravity
    }

    fun setLineGravity(lineGravity: Int) {
        this.lineGravity = lineGravity
        invalidate()
    }

    companion object {
        //=============================================================line gravity常量定义
        const val GRAVITY_LEFT = 2
        const val GRAVITY_RIGHT = 4
        const val GRAVITY_MIDDLE = 0
        const val GRAVITY_TOP = 1
        const val GRAVITY_BOTTOM = 3
    }

    init {
        val attr = context.obtainStyledAttributes(attrs, R.styleable.UnderLineLinearLayout)
        lineMarginSide =
            attr.getDimensionPixelOffset(R.styleable.UnderLineLinearLayout_line_margin_side, 10)
        lineDynamicDimen =
            attr.getDimensionPixelOffset(R.styleable.UnderLineLinearLayout_line_dynamic_dimen, 0)
        lineStrokeWidth =
            attr.getDimensionPixelOffset(R.styleable.UnderLineLinearLayout_line_stroke_width, 2)
        lineColor = attr.getColor(R.styleable.UnderLineLinearLayout_line_color, -0xc22e5b)
        pointSize = attr.getDimensionPixelSize(R.styleable.UnderLineLinearLayout_point_size, 8)
        pointColor = attr.getColor(R.styleable.UnderLineLinearLayout_point_color, -0xc22e5b)
        lineGravity = attr.getInt(R.styleable.UnderLineLinearLayout_line_gravity, GRAVITY_LEFT)
        val iconRes =
            attr.getResourceId(R.styleable.UnderLineLinearLayout_icon_src, R.drawable.ic_ok)
        val temp = context.resources.getDrawable(iconRes) as BitmapDrawable
        if (temp != null) icon = temp.bitmap
        curOrientation = orientation
        attr.recycle()
        setWillNotDraw(false)
        initView(context)
    }
}