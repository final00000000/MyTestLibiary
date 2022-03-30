package com.zhang.utilslibiary.utils.toast

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.content.res.Configuration
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.*
import com.zhang.utilslibiary.R

@SuppressLint("InflateParams", "StaticFieldLeak")
object Toasty {
    private val LOADED_TOAST_TYPEFACE = Typeface.create("sans-serif-condensed", Typeface.NORMAL)
    private var currentTypeface = LOADED_TOAST_TYPEFACE
    private var textSize = 16 // in SP
    private var tintIcon = true
    private var allowQueue = true
    private var toastGravity = -1
    private var xOffset = -1
    private var yOffset = -1
    private var supportDarkTheme = true
    private var isRTL = false
    private var lastToast: Toast? = null
    const val LENGTH_SHORT = Toast.LENGTH_SHORT
    const val LENGTH_LONG = Toast.LENGTH_LONG
    private var mContext: Context? = null

    fun setContext(context: Context) {
        mContext = context
    }

    fun normal(@StringRes message: Int) {
        normal(mContext!!.getString(message), Toast.LENGTH_SHORT, null, false).show()
    }

    fun normal(message: CharSequence) {
        normal(message, Toast.LENGTH_SHORT, null, false).show()
    }

    fun normal(@StringRes message: Int, icon: Drawable?) {
        normal(mContext!!.getString(message), Toast.LENGTH_SHORT, icon, true).show()
    }

    fun normal(message: CharSequence, icon: Drawable?) {
        normal(message, Toast.LENGTH_SHORT, icon, true).show()
    }

    fun normal(@StringRes message: Int, duration: Int) {
        normal(mContext!!.getString(message), duration, null, false).show()
    }

    fun normal(message: CharSequence, duration: Int) {
        normal(message, duration, null, false).show()
    }

    fun normal(@StringRes message: Int, duration: Int, icon: Drawable?) {
        normal(mContext!!.getString(message), duration, icon, true).show()
    }

    fun normal(message: CharSequence, duration: Int, icon: Drawable?) {
        normal(message, duration, icon, true).show()
    }

    @CheckResult
    fun normal(
        @StringRes message: Int, duration: Int,
        icon: Drawable?, withIcon: Boolean
    ): Toast {
        return normalWithDarkThemeSupport(
            mContext!!.getString(message),
            icon,
            duration,
            withIcon
        )
    }

    @CheckResult
    fun normal(
        message: CharSequence, duration: Int,
        icon: Drawable?, withIcon: Boolean
    ): Toast {
        return normalWithDarkThemeSupport(message, icon, duration, withIcon)
    }

    fun warning(@StringRes message: Int) {
        warning(mContext!!.getString(message), Toast.LENGTH_SHORT, true).show()
    }

    fun warning(message: CharSequence) {
        warning(message, Toast.LENGTH_SHORT, true).show()
    }

    fun warning(@StringRes message: Int, duration: Int) {
        warning(mContext!!.getString(message), duration, true).show()
    }

    fun warning(message: CharSequence, duration: Int) {
        warning(message, duration, true).show()
    }

    @CheckResult
    fun warning(
        @StringRes message: Int,
        duration: Int,
        withIcon: Boolean
    ): Toast {
        return custom(
            mContext!!.getString(message),
            ToastyUtils.getDrawable(mContext!!, R.drawable.ic_error_outline_white_24dp),
            ToastyUtils.getColor(mContext!!, R.color.warningColor),
            ToastyUtils.getColor(mContext!!, R.color.defaultTextColor),
            duration,
            withIcon,
            true
        )
    }

    @CheckResult
    fun warning(message: CharSequence, duration: Int, withIcon: Boolean): Toast {
        return custom(
            message,
            ToastyUtils.getDrawable(mContext!!, R.drawable.ic_error_outline_white_24dp),
            ToastyUtils.getColor(mContext!!, R.color.warningColor),
            ToastyUtils.getColor(mContext!!, R.color.defaultTextColor),
            duration,
            withIcon,
            true
        )
    }

    fun info(@StringRes message: Int) {
        info(mContext!!.getString(message), Toast.LENGTH_SHORT, true).show()
    }

    fun info(message: CharSequence) {
        info(message, Toast.LENGTH_SHORT, true).show()
    }

    fun info(@StringRes message: Int, duration: Int) {
        info(mContext!!.getString(message), duration, true).show()
    }

    fun info(message: CharSequence, duration: Int) {
        info(message, duration, true).show()
    }

    @CheckResult
    fun info(@StringRes message: Int, duration: Int, withIcon: Boolean): Toast {
        return custom(
            mContext!!.getString(message),
            ToastyUtils.getDrawable(mContext!!, R.drawable.ic_info_outline_white_24dp),
            ToastyUtils.getColor(mContext!!, R.color.infoColor),
            ToastyUtils.getColor(mContext!!, R.color.defaultTextColor),
            duration,
            withIcon,
            true
        )
    }

    @CheckResult
    fun info(message: CharSequence, duration: Int, withIcon: Boolean): Toast {
        return custom(
            message,
            ToastyUtils.getDrawable(mContext!!, R.drawable.ic_info_outline_white_24dp),
            ToastyUtils.getColor(mContext!!, R.color.infoColor),
            ToastyUtils.getColor(mContext!!, R.color.defaultTextColor),
            duration,
            withIcon,
            true
        )
    }

    fun success(@StringRes message: Int) {
        success(mContext!!.getString(message), Toast.LENGTH_SHORT, true).show()
    }

    fun success(message: CharSequence) {
        success(message, Toast.LENGTH_SHORT, true).show()
    }

    fun success(@StringRes message: Int, duration: Int) {
        success(mContext!!.getString(message), duration, true).show()
    }

    fun success(message: CharSequence, duration: Int) {
        success(message, duration, true).show()
    }

    @CheckResult
    fun success(
        @StringRes message: Int,
        duration: Int,
        withIcon: Boolean
    ): Toast {
        return custom(
            mContext!!.getString(message),
            ToastyUtils.getDrawable(mContext!!, R.drawable.ic_check_white_24dp),
            ToastyUtils.getColor(mContext!!, R.color.successColor),
            ToastyUtils.getColor(mContext!!, R.color.defaultTextColor),
            duration,
            withIcon,
            true
        )
    }

    @CheckResult
    fun success(message: CharSequence, duration: Int, withIcon: Boolean): Toast {
        return custom(
            message,
            ToastyUtils.getDrawable(mContext!!, R.drawable.ic_check_white_24dp),
            ToastyUtils.getColor(mContext!!, R.color.successColor),
            ToastyUtils.getColor(mContext!!, R.color.defaultTextColor),
            duration,
            withIcon,
            true
        )
    }

    fun error(@StringRes message: Int) {
        error(mContext!!.getString(message), Toast.LENGTH_SHORT, true).show()
    }

    fun error(message: CharSequence) {
        error(message, Toast.LENGTH_SHORT, true).show()
    }

    fun error(@StringRes message: Int, duration: Int) {
        error(mContext!!.getString(message), duration, true).show()
    }

    fun error(message: CharSequence, duration: Int) {
        error(message, duration, true).show()
    }

    @CheckResult
    fun error(@StringRes message: Int, duration: Int, withIcon: Boolean): Toast {
        return custom(
            mContext!!.getString(message),
            ToastyUtils.getDrawable(mContext!!, R.drawable.ic_clear_white_24dp),
            ToastyUtils.getColor(mContext!!, R.color.errorColor),
            ToastyUtils.getColor(mContext!!, R.color.defaultTextColor),
            duration,
            withIcon,
            true
        )
    }

    @CheckResult
    fun error(message: CharSequence, duration: Int, withIcon: Boolean): Toast {
        return custom(
            message,
            ToastyUtils.getDrawable(mContext!!, R.drawable.ic_clear_white_24dp),
            ToastyUtils.getColor(mContext!!, R.color.errorColor),
            ToastyUtils.getColor(mContext!!, R.color.defaultTextColor),
            duration,
            withIcon,
            true
        )
    }

    @CheckResult
    fun custom(
        @StringRes message: Int, icon: Drawable?,
        duration: Int, withIcon: Boolean
    ): Toast {
        return custom(
            mContext!!.getString(message),
            icon,
            -1,
            ToastyUtils.getColor(mContext!!, R.color.defaultTextColor),
            duration,
            withIcon,
            false
        )
    }

    @CheckResult
    fun custom(
        message: CharSequence, icon: Drawable?,
        duration: Int, withIcon: Boolean
    ): Toast {
        return custom(
            message, icon, -1, ToastyUtils.getColor(mContext!!, R.color.defaultTextColor),
            duration, withIcon, false
        )
    }

    @CheckResult
    fun custom(
        @StringRes message: Int, @DrawableRes iconRes: Int,
        @ColorRes tintColorRes: Int, duration: Int,
        withIcon: Boolean, shouldTint: Boolean
    ): Toast {
        return custom(
            mContext!!.getString(message),
            ToastyUtils.getDrawable(mContext!!, iconRes),
            ToastyUtils.getColor(mContext!!, tintColorRes),
            ToastyUtils.getColor(mContext!!, R.color.defaultTextColor),
            duration,
            withIcon,
            shouldTint
        )
    }

    @CheckResult
    fun custom(
        message: CharSequence, @DrawableRes iconRes: Int,
        @ColorRes tintColorRes: Int, duration: Int,
        withIcon: Boolean, shouldTint: Boolean
    ): Toast {
        return custom(
            message,
            ToastyUtils.getDrawable(mContext!!, iconRes),
            ToastyUtils.getColor(mContext!!, tintColorRes),
            ToastyUtils.getColor(mContext!!, R.color.defaultTextColor),
            duration,
            withIcon,
            shouldTint
        )
    }

    @CheckResult
    fun custom(
        @StringRes message: Int, icon: Drawable?,
        @ColorRes tintColorRes: Int, duration: Int,
        withIcon: Boolean, shouldTint: Boolean
    ): Toast {
        return custom(
            mContext!!.getString(message),
            icon,
            ToastyUtils.getColor(mContext!!, tintColorRes),
            ToastyUtils.getColor(mContext!!, R.color.defaultTextColor),
            duration,
            withIcon,
            shouldTint
        )
    }

    @CheckResult
    fun custom(
        @StringRes message: Int, icon: Drawable?,
        @ColorRes tintColorRes: Int, @ColorRes textColorRes: Int, duration: Int,
        withIcon: Boolean, shouldTint: Boolean
    ): Toast {
        return custom(
            mContext!!.getString(message),
            icon,
            ToastyUtils.getColor(mContext!!, tintColorRes),
            ToastyUtils.getColor(mContext!!, textColorRes),
            duration,
            withIcon,
            shouldTint
        )
    }

    @SuppressLint("ShowToast")
    @CheckResult
    fun custom(
        message: CharSequence, icon: Drawable?,
        @ColorInt tintColor: Int, @ColorInt textColor: Int, duration: Int,
        withIcon: Boolean, shouldTint: Boolean
    ): Toast {
        val currentToast = Toast.makeText(mContext!!, "", duration)
        val toastLayout =
            (mContext!!.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater)
                .inflate(R.layout.toast_layout, null)
        val toastRoot = toastLayout.findViewById<LinearLayout>(R.id.toast_root)
        val toastIcon = toastLayout.findViewById<ImageView>(R.id.toast_icon)
        val toastTextView = toastLayout.findViewById<TextView>(R.id.toast_text)
        val drawableFrame: Drawable
        drawableFrame = if (shouldTint) ToastyUtils.tint9PatchDrawableFrame(
            mContext!!,
            tintColor
        ) else ToastyUtils.getDrawable(mContext!!, R.drawable.toast_frame)!!
        ToastyUtils.setBackground(toastLayout, drawableFrame)
        if (withIcon) {
            requireNotNull(icon) { "Avoid passing 'icon' as null if 'withIcon' is set to true" }
            if (isRTL && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) toastRoot.layoutDirection =
                View.LAYOUT_DIRECTION_RTL
            ToastyUtils.setBackground(
                toastIcon,
                if (tintIcon) ToastyUtils.tintIcon(icon, textColor) else icon
            )
        } else {
            toastIcon.visibility = View.GONE
        }
        toastTextView.text = message
        toastTextView.setTextColor(textColor)
        toastTextView.typeface = currentTypeface
        toastTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize.toFloat())
        currentToast.view = toastLayout
        if (!allowQueue) {
            if (lastToast != null) lastToast!!.cancel()
            lastToast = currentToast
        }

        // Make sure to use default values for non-specified ones.
        currentToast.setGravity(
            if (toastGravity == -1) currentToast.gravity else toastGravity,
            if (xOffset == -1) currentToast.xOffset else xOffset,
            if (yOffset == -1) currentToast.yOffset else yOffset
        )
        return currentToast
    }

    private fun normalWithDarkThemeSupport(
        message: CharSequence, icon: Drawable?,
        duration: Int, withIcon: Boolean
    ): Toast {
        return if (supportDarkTheme && Build.VERSION.SDK_INT >= 29) {
            val uiMode =
                mContext!!.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            if (uiMode == Configuration.UI_MODE_NIGHT_NO) {
                withLightTheme(message, icon, duration, withIcon)
            } else withDarkTheme(message, icon, duration, withIcon)
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
                withLightTheme(message, icon, duration, withIcon)
            } else {
                withDarkTheme(message, icon, duration, withIcon)
            }
        }
    }

    private fun withLightTheme(
        message: CharSequence, icon: Drawable?,
        duration: Int, withIcon: Boolean
    ): Toast {
        return custom(
            message, icon, ToastyUtils.getColor(mContext!!, R.color.defaultTextColor),
            ToastyUtils.getColor(mContext!!, R.color.normalColor), duration, withIcon, true
        )
    }

    private fun withDarkTheme(
        message: CharSequence, icon: Drawable?,
        duration: Int, withIcon: Boolean
    ): Toast {
        return custom(
            message, icon, ToastyUtils.getColor(mContext!!, R.color.normalColor),
            ToastyUtils.getColor(mContext!!, R.color.defaultTextColor), duration, withIcon, true
        )
    }

    class Config private constructor() {
        private var typeface = currentTypeface
        private var textSize = Toasty.textSize
        private var tintIcon = Toasty.tintIcon
        private var allowQueue = true
        private var toastGravity = Toasty.toastGravity
        private var xOffset = Toasty.xOffset
        private var yOffset = Toasty.yOffset
        private var supportDarkTheme = true
        private var isRTL = false

        @CheckResult
        fun setToastTypeface(typeface: Typeface): Config {
            this.typeface = typeface
            return this
        }

        @CheckResult
        fun setTextSize(sizeInSp: Int): Config {
            this.textSize = sizeInSp
            return this
        }

        @CheckResult
        fun tintIcon(tintIcon: Boolean): Config {
            this.tintIcon = tintIcon
            return this
        }

        @CheckResult
        fun allowQueue(allowQueue: Boolean): Config {
            this.allowQueue = allowQueue
            return this
        }

        @CheckResult
        fun setGravity(gravity: Int, xOffset: Int, yOffset: Int): Config {
            this.toastGravity = gravity
            this.xOffset = xOffset
            this.yOffset = yOffset
            return this
        }

        @CheckResult
        fun setGravity(gravity: Int): Config {
            this.toastGravity = gravity
            return this
        }

        @CheckResult
        fun supportDarkTheme(supportDarkTheme: Boolean): Config {
            this.supportDarkTheme = supportDarkTheme
            return this
        }

        fun setRTL(isRTL: Boolean): Config {
            this.isRTL = isRTL
            return this
        }

        fun apply() {
            currentTypeface = typeface
            Toasty.textSize = textSize
            Toasty.tintIcon = tintIcon
            Toasty.allowQueue = allowQueue
            Toasty.toastGravity = toastGravity
            Toasty.xOffset = xOffset
            Toasty.yOffset = yOffset
            Toasty.supportDarkTheme = supportDarkTheme
            Toasty.isRTL = isRTL
        }

        companion object {
            @get:CheckResult
            val instance: Config
                get() = Config()

            fun reset() {
                currentTypeface = LOADED_TOAST_TYPEFACE
                textSize = 16
                tintIcon = true
                allowQueue = true
                toastGravity = -1
                xOffset = -1
                yOffset = -1
                supportDarkTheme = true
                isRTL = false
            }
        }
    }
}