package com.zhang.mydemo.ui.widget

import android.annotation.TargetApi
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.TextUtils
import android.util.AttributeSet
import android.util.SparseBooleanArray
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.zhang.mydemo.R

class ExpandTextView : LinearLayout, View.OnClickListener {
    lateinit var mTv: TextView
    lateinit var mButton // Button to expand/collapse
            : ImageButton
    private var mRelayout = false
    private var mCollapsed = true // Show short version as default.
    private var mCollapsedHeight = 0
    private var mTextHeightWithMaxLines = 0
    private var mMaxCollapsedLines = 0
    private var mMarginBetweenTxtAndBottom = 0
    private var mExpandDrawable: Drawable? = null
    private var mCollapseDrawable: Drawable? = null
    private var mAnimationDuration = 0
    private var mAnimAlphaStart = 0f
    private var mAnimating = false

    /* Listener for callback */
    private var mListener: OnExpandStateChangeListener? = null

    /* For saving collapsed status when used in ListView */
    private var mCollapsedStatus: SparseBooleanArray? = null
    private var mPosition = 0

    constructor(context: Context?) : super(context) {
        init(null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs)
    }

    override fun setOrientation(orientation: Int) {
        require(HORIZONTAL != orientation) { "ExpandableTextView only supports Vertical Orientation." }
        super.setOrientation(orientation)
    }

    override fun onClick(view: View) {
        if (mButton.visibility != VISIBLE) {
            return
        }
        mCollapsed = !mCollapsed
        mButton.setImageDrawable(if (mCollapsed) mExpandDrawable else mCollapseDrawable)
        if (mCollapsedStatus != null) {
            mCollapsedStatus!!.put(mPosition, mCollapsed)
        }

        // mark that the animation is in progress
        mAnimating = true
        val animation: Animation = if (mCollapsed) {
            ExpandCollapseAnimation(this, height, mCollapsedHeight)
        } else {
            ExpandCollapseAnimation(
                this, height, height +
                        mTextHeightWithMaxLines - mTv.height
            )
        }
        animation.fillAfter = true
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                applyAlphaAnimation(mTv, mAnimAlphaStart)
            }

            override fun onAnimationEnd(animation: Animation) {
                // clear animation here to avoid repeated applyTransformation() calls
                clearAnimation()
                // clear the animation flag
                mAnimating = false

                // notify the listener
                if (mListener != null) {
                    mListener!!.onExpandStateChanged(mTv, !mCollapsed)
                }
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        clearAnimation()
        startAnimation(animation)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        // while an animation is in progress, intercept all the touch events to children to
        // prevent extra clicks during the animation
        return mAnimating
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        findViews()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // If no change, measure and return
        if (!mRelayout || visibility == GONE) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            return
        }
        mRelayout = false

        // Setup with optimistic case
        // i.e. Everything fits. No button needed
        mButton.visibility = GONE
        mTv.maxLines = Int.MAX_VALUE

        // Measure
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        // If the text fits in collapsed mode, we are done.
        if (mTv.lineCount <= mMaxCollapsedLines) {
            return
        }

        // Saves the text height w/ max lines
        mTextHeightWithMaxLines = getRealTextViewHeight(mTv)

        // Doesn't fit in collapsed mode. Collapse text view as needed. Show
        // button.
        if (mCollapsed) {
            mTv.maxLines = mMaxCollapsedLines
        }
        mButton.visibility = VISIBLE

        // Re-measure with new setup
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (mCollapsed) {
            // Gets the margin between the TextView's bottom and the ViewGroup's bottom
            mTv.post { mMarginBetweenTxtAndBottom = height - mTv.height }
            // Saves the collapsed height of this ViewGroup
            mCollapsedHeight = measuredHeight
        }
    }

    /**
     * 设置伸展状态监听
     *
     * @param listener
     */
    fun setOnExpandStateChangeListener(listener: OnExpandStateChangeListener?) {
        mListener = listener
    }

    fun setText(text: CharSequence?, collapsedStatus: SparseBooleanArray, position: Int) {
        mCollapsedStatus = collapsedStatus
        mPosition = position
        val isCollapsed = collapsedStatus[position, true]
        clearAnimation()
        mCollapsed = isCollapsed
        mButton.setImageDrawable(if (mCollapsed) mExpandDrawable else mCollapseDrawable)
        mText = text
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        requestLayout()
    }

    var mText: CharSequence?
        get() = mTv.text
        set(text) {
            mRelayout = true
            mTv.text = text
            visibility = if (TextUtils.isEmpty(text)) GONE else VISIBLE
        }

    private fun init(attrs: AttributeSet?) {
        initAttr(attrs)

        // enforces vertical orientation
        orientation = VERTICAL

        // default visibility is gone
        visibility = GONE
    }

    private fun initAttr(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandTextView)
        mMaxCollapsedLines = typedArray.getInt(
            R.styleable.ExpandTextView_etv_maxCollapsedLines,
            MAX_COLLAPSED_LINES
        )
        mAnimationDuration = typedArray.getInt(
            R.styleable.ExpandTextView_etv_animDuration,
            DEFAULT_ANIM_DURATION
        )
        mAnimAlphaStart = typedArray.getFloat(
            R.styleable.ExpandTextView_etv_animAlphaStart,
            DEFAULT_ANIM_ALPHA_START
        )
        mExpandDrawable = typedArray.getDrawable(R.styleable.ExpandTextView_etv_expandDrawable)
        mCollapseDrawable =
            typedArray.getDrawable(R.styleable.ExpandTextView_etv_collapseDrawable)
        if (mExpandDrawable == null) {
            mExpandDrawable = getDrawable(context, R.drawable.ic_expand)
        }
        if (mCollapseDrawable == null) {
            mCollapseDrawable = getDrawable(context, R.drawable.ic_collapse)
        }
        typedArray.recycle()
    }

    private fun findViews() {
        mTv = findViewById(R.id.expandable_text)
        mTv.setOnClickListener(this)
        mButton = findViewById(R.id.expand_collapse)
        mButton.setImageDrawable(if (mCollapsed) mExpandDrawable else mCollapseDrawable)
        mButton.setOnClickListener(this)
    }

    internal inner class ExpandCollapseAnimation(
        private val mTargetView: View,
        private val mStartHeight: Int,
        private val mEndHeight: Int
    ) : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            val newHeight = ((mEndHeight - mStartHeight) * interpolatedTime + mStartHeight).toInt()
            mTv.maxHeight = newHeight - mMarginBetweenTxtAndBottom
            if (mAnimAlphaStart.compareTo(1.0f) != 0) {
                applyAlphaAnimation(
                    mTv,
                    mAnimAlphaStart + interpolatedTime * (1.0f - mAnimAlphaStart)
                )
            }
            mTargetView.layoutParams.height = newHeight
            mTargetView.requestLayout()
        }

        override fun willChangeBounds(): Boolean {
            return true
        }

        init {
            duration = mAnimationDuration.toLong()
        }
    }

    interface OnExpandStateChangeListener {
        /**
         * Called when the expand/collapse animation has been finished
         *
         * @param textView   - TextView being expanded/collapsed
         * @param isExpanded - true if the TextView has been expanded
         */
        fun onExpandStateChanged(textView: TextView?, isExpanded: Boolean)
    }

    companion object {
        /* The default number of lines */
        private const val MAX_COLLAPSED_LINES = 3

        /* The default animation duration */
        private const val DEFAULT_ANIM_DURATION = 300

        /* The default alpha value when the animation starts */
        private const val DEFAULT_ANIM_ALPHA_START = 0.7f
        private val isPostHoneycomb: Boolean
            get() = true
        private val isPostLolipop: Boolean
            get() = true

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        private fun applyAlphaAnimation(view: View?, alpha: Float) {
            if (isPostHoneycomb) {
                view!!.alpha = alpha
            } else {
                val alphaAnimation = AlphaAnimation(alpha, alpha)
                // make it instant
                alphaAnimation.duration = 0
                alphaAnimation.fillAfter = true
                view!!.startAnimation(alphaAnimation)
            }
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        private fun getDrawable(context: Context, @DrawableRes resId: Int): Drawable {
            val resources = context.resources
            return if (isPostLolipop) {
                resources.getDrawable(resId, context.theme)
            } else {
                resources.getDrawable(resId)
            }
        }

        private fun getRealTextViewHeight(textView: TextView): Int {
            val layout = textView.layout
            val textHeight = layout?.getLineTop(textView.lineCount) ?: 0
            val padding = textView.compoundPaddingTop + textView.compoundPaddingBottom
            return textHeight + padding
        }
    }
}