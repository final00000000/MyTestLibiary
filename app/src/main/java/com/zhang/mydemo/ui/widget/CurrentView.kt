package com.zhang.mydemo.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.zhang.mydemo.R
import com.zhang.utilslibiary.utils.getScreenHeight
import com.zhang.utilslibiary.utils.getScreenWidth
import org.jetbrains.anko.attr

/**
 * @Author : zhang
 * @Create Time : 2022/3/9
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
class CurrentView : View {

    var firstHeight = 0
    var firstWidth = 0

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(context, attrs)
    }

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val path = Path()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = ContextCompat.getColor(context, R.color.color005BAB)
        /**
         *  FILL 是填充模式(默认)
         *  STROKE 是画线模式（即勾边模式）
         *  FILL_AND_STROKE 是两种模式一并使用：既画线又填充。
         */
        paint.style = Paint.Style.FILL_AND_STROKE
        // 线条宽度
        paint.strokeWidth = 10f
        canvas.drawCircle(
            (getScreenWidth(context) / 2).toFloat(),
            (getScreenHeight(context) / 2).toFloat(),
            200f,
            paint
        )
    }

    private fun initView(context: Context?, attrs: AttributeSet?) {
        val firstView = context!!.obtainStyledAttributes(attrs, R.styleable.FirstView)
        firstHeight = firstView.getDimension(R.styleable.FirstView_firstHeight, 0f).toInt()
        firstWidth = firstView.getDimension(R.styleable.FirstView_firstWidth, 0f).toInt()
        firstView.recycle()
    }

}