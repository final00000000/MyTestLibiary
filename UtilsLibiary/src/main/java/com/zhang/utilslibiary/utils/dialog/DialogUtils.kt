package com.zhang.utilslibiary.utils.dialog

import android.app.Dialog
import com.zhang.utilslibiary.R
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.annotation.StyleRes
import androidx.appcompat.widget.AppCompatImageView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.zhang.utilslibiary.utils.singleClick
import com.zhang.utilslibiary.utils.verification.DragImageView
import com.zhang.utilslibiary.utils.verification.SwipeCaptchaView
import org.jetbrains.anko.layoutInflater
import java.util.*

object DialogUtils {
    fun getDialogS(context: Context, view: View, gravity: Int): Dialog {

        //   val view = LayoutInflater.from(context).inflate(layoutId, null)

        val dialog = Dialog(context, R.style.BottomSheetDialogRadius)
        dialog.setContentView(view)
        dialog.show()

        val window = dialog.window
        window!!.setGravity(gravity)
        window.setWindowAnimations(R.style.dialog_animation)
        window.decorView.setPadding(0, 0, 0, 0)

        val lp = window.attributes
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.attributes = lp
        return dialog
    }

    /* fun getDialogM(context: Context, view: View, gravity: Int): Dialog {

        //   val view = LayoutInflater.from(context).inflate(layoutId, null)

        val dialog = Dialog(context, R.style.BottomSheetDialogRadius)
        dialog.setContentView(view)
        dialog.show()

        val window = dialog.window
        window!!.setGravity(gravity)
        window.setWindowAnimations(R.style.dialog_animation)
        window.decorView.setPadding(0, 0, 0, 0)

        val lp = window.attributes
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.attributes = lp
        return dialog
    }


    fun showToast(activity: Activity, view: View, gravity: Int) {
        val myToast = Toast(activity)
        myToast.duration = Toast.LENGTH_LONG
        myToast.setGravity(gravity, 0, 0)
        myToast.view = view
        myToast.show()
    }

    */

    /**
     *
     * @param context Context   上下文
     * @param layout View       布局
     * @return BottomSheetDialog
     */
    fun bottomSheetDialog(context: Context, layout: View): BottomSheetDialog {
        val dialog = BottomSheetDialog(context, R.style.BottomSheetDialog)
        layout.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog.setContentView(layout)

        dialog.show()
        return dialog
    }

    /**
     *
     * @param context Context   上下文
     * @param layout View       布局
     * @param isDraggable Boolean 是否可以拖动
     * @return BottomSheetDialog
     */
    fun bottomSheetDialog(context: Context, layout: View, @StyleRes theme: Int): BottomSheetDialog {
        val dialog = BottomSheetDialog(context, theme)
        layout.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog.setContentView(layout)

        dialog.show()
        return dialog
    }

    /**
     *
     * @param context Context   上下文
     * @param layout View       布局
     * @param isDraggable Boolean 是否可以拖动
     * @return BottomSheetDialog
     */
    fun bottomSheetDialog(context: Context, layout: View, isDraggable: Boolean): BottomSheetDialog {
        val dialog = BottomSheetDialog(context, R.style.BottomSheetDialog)
        layout.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        // 禁止拖拽
        dialog.behavior.isDraggable = isDraggable
        dialog.setContentView(layout)

        dialog.show()
        return dialog
    }

    // 居中显示dialog  点击外部是否消失
    fun getCenterDialog(context: Context, view: View, gravity: Int, isDraggable: Boolean): Dialog {
        val dialog = Dialog(context, R.style.BottomSheetDialog)
        dialog.setContentView(view)
        dialog.show()
        dialog.setCanceledOnTouchOutside(isDraggable)
        val window = dialog.window
        window!!.setGravity(gravity)
        window.setWindowAnimations(R.style.dialog_animation)
        window.decorView.setPadding(80, 0, 80, 0)

        val lp = window.attributes
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.attributes = lp
        return dialog
    }
    private var mArray: IntArray = intArrayOf(
        R.drawable.picture,
        R.drawable.drawable1,
        R.drawable.drawable2,
        R.drawable.drawable3,
        R.drawable.drawable4,
        R.drawable.drawable5
    )

    private var mArray1: IntArray = intArrayOf(
        R.drawable.picture,
        R.drawable.drawable6,
        R.drawable.drawable7,
        R.drawable.drawable8,
        R.drawable.drawable9,
        R.drawable.drawable10
    )

    /**
     * 公共验证码弹窗 只需要上下文
     * @param context Context
     * @param success Function1<[@kotlin.ParameterName] Boolean, Unit>
     */
    fun getVerificationCodeDialog(context: Context, success: (any: Boolean) -> Unit) {
        val view = context.layoutInflater.inflate(R.layout.verification_dialog, null)
        val dialogS = getDialogS(context, view, Gravity.CENTER)

        view.findViewById<ImageView>(R.id.close_dialog).singleClick { dialogS.dismiss() }
        val mSwipeCaptchaView = view.findViewById<SwipeCaptchaView>(R.id.swipeCaptchaView)
        val mSeekBar = view.findViewById<DragImageView>(R.id.seekBar1)

        mSeekBar.SwipeVerifi(mSwipeCaptchaView)

        // 设置默认图片
        val index: Int = Random().nextInt(6)
        mSwipeCaptchaView.setImageBitmap(
            BitmapFactory.decodeResource(
                context.resources,
                mArray[index]
            )
        )
        mSwipeCaptchaView.createCaptcha()

        mSwipeCaptchaView.onCaptchaMatchCallback =
            object : SwipeCaptchaView.OnCaptchaMatchCallback {
                override fun matchSuccess(swipeCaptchaView: SwipeCaptchaView?) {

                    mSeekBar.ok()
                    val handler = Handler(Looper.getMainLooper())
                    handler.postDelayed({
                        mSeekBar.reset()
                        dialogS.dismiss()
                        swipeCaptchaView!!.resetCaptcha()
                        success(true)
                        mSeekBar.isEnabled = false
                    }, 1500)
                }

                override fun matchFailed(swipeCaptchaView: SwipeCaptchaView?) {
//                    swipeCaptchaView!!.resetCaptcha()
                    mSeekBar.fail()
                    success(false)
                }
            }

        // 刷新按钮
        view.findViewById<AppCompatImageView>(R.id.btnChange).singleClick {
            val rondom: Int = Random().nextInt(6)
            mSeekBar.reset()
            mSwipeCaptchaView.setImageBitmap(
                BitmapFactory.decodeResource(
                    context.resources,
                    mArray1[rondom]
                )
            )
            mSwipeCaptchaView.createCaptcha()
            mSeekBar.isEnabled = true
        }

        dialogS.show()

    }

}