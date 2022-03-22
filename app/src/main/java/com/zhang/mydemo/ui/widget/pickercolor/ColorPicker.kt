package com.zhang.mydemo.ui.widget.pickercolor

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.ImageView
import com.zhang.mydemo.R
import kotlin.math.abs
import kotlin.math.sqrt

/**
 * Created by zzhoujay on 2015/2/20 0020.
 * 色轮调色板
 */
@SuppressLint("AppCompatCustomView")
class ColorPicker @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr), OnTouchListener {
    private var bp //色轮图片
            : Bitmap?
    private val bw: Int
    private val bh //色轮图片的尺寸
            : Int
    private var x1 = 0f
    private var y1 = 0f
    private var radio = 0f
    var onColorSelectListener: OnColorSelectListener? = null
    private val paint: Paint
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        val xx = event.x
        val yy = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP -> if (!inCircle(
                    xx,
                    yy
                )
            ) {
                return false
            }
        }
        x1 = xx
        y1 = yy
        invalidate()
        if (onColorSelectListener != null) {
            onColorSelectListener!!.onColorSelect(getColor(xx, yy))
        }
        return true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        x1 = (width / 2).toFloat()
        y1 = (height / 2).toFloat()
        radio = if (x1 > y1) y1 else x1
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPoint(x1, y1, paint)
    }

    private fun getColor(x: Float, y: Float): Int {
        x1 = x
        y1 = y
        val w = width
        val h = height
        val dx = (x / w * bw).toInt()
        val dy = (y / h * bh).toInt()
        var color = Color.BLACK
        try {
            color = bp!!.getPixel(dx, dy)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return color
    }

    private fun inCircle(x: Float, y: Float): Boolean {
        val cx = (width / 2).toFloat()
        val cy = (height / 2).toFloat()
        val d = abs(sqrt(((x - cx) * (x - cx) + (y - cy) * (y - cy)).toDouble()))
            .toFloat()
        return d <= radio
    }

    interface OnColorSelectListener {
        fun onColorSelect(color: Int)
    }

    /**
     * 回收Bitmap内存
     */
    fun recycle() {
        if (bp != null) {
            if (!bp!!.isRecycled) {
                bp!!.recycle()
            }
            bp = null
        }
    }

    val isRecycled: Boolean
        get() = bp == null || bp!!.isRecycled

    init {
        bp = BitmapFactory.decodeResource(context.resources, R.drawable.circle)
        bw = bp!!.width
        bh = bp!!.height
        setImageBitmap(bp)
        setOnTouchListener(this)
        paint = Paint()
        paint.strokeWidth = 10f
        paint.color = Color.BLACK
        paint.isAntiAlias = true
        isClickable = true
    }
}