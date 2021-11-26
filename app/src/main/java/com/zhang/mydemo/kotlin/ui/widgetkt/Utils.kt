package com.zhang.mydemo.kotlin.ui.widgetkt

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.graphics.drawable.BitmapDrawable
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.Base64
import java.io.ByteArrayOutputStream

/**
 * Created by ZQiong on 2018/3/22.
 */
class Utils private constructor() {
    companion object {
        @JvmStatic
        fun toBase64(bitmap: Bitmap): String {
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
            val bytes = baos.toByteArray()
            return Base64.encodeToString(bytes, Base64.NO_WRAP)
        }

        @JvmStatic
        fun toBitmap(drawable: Drawable): Bitmap {
            if (drawable is BitmapDrawable) {
                return drawable.bitmap
            }
            var width = drawable.intrinsicWidth
            width = if (width > 0) width else 1
            var height = drawable.intrinsicHeight
            height = if (height > 0) height else 1
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            return bitmap
        }

        @JvmStatic
        fun decodeResource(context: Context, resId: Int): Bitmap {
            return BitmapFactory.decodeResource(context.resources, resId)
        }

        @JvmStatic
        val currentTime: Long
            get() = System.currentTimeMillis()
    }

    init {
        throw InstantiationException("This class is not for instantiation")
    }
}