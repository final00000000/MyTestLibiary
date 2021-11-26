package com.zhang.utilslibiary.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.zhang.utilslibiary.R
import jp.wasabeef.glide.transformations.BlurTransformation

/**
 * @Author : zhang
 * @Create Time : 2021/11/23
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
object GlideUtils {

    /**
     * 正常加载
     * @param context Context 上下文
     * @param url String      图片链接
     * @param view ImageView  被赋值的控件
     */
    fun loadImage(context: Context, url: String, view: ImageView) {
        Glide.with(context)
            .load(url)
            .into(view)
    }

    /**
     *
     * 带占位图的 drawable格式
     * @param context Context
     * @param url String
     * @param placeholder Drawable
     * @param view ImageView
     *
     * placeholder(placeholder) 占位图
     * fallback(placeholder) 网络图片为空时自动补充占位图
     */
    fun loadImage(context: Context, url: String, placeholder: Drawable, view: ImageView) {
        Glide.with(context)
            .load(url)
            .placeholder(placeholder)
            .fallback(placeholder)
            .into(view)
    }

    /**
     *
     * 带占位图的 int格式
     * @param context Context
     * @param url String
     * @param placeholder Drawable
     * @param view ImageView
     *
     * placeholder(placeholder) 占位图
     * fallback(placeholder) 网络图片为空时自动补充占位图
     */
    fun loadImage(context: Context, url: String, placeholder: Int, view: ImageView) {
        Glide.with(context)
            .load(url)
            .placeholder(placeholder)
            .fallback(placeholder)
            .into(view)
    }

    /**
     * 带错误图的
     * @param context Context
     * @param url String
     * @param error Any
     * @param view ImageView
     */
    fun loadImageError(context: Context, url: String, error: Any, view: ImageView) {
        Glide.with(context)
            .load(url)
            .error(error)
            .into(view)
    }

    /**
     * 加载动图
     * @param context Context
     * @param url String
     * @param view ImageView
     */
    fun loadImageGif(context: Context, url: String, view: ImageView) {
        Glide.with(context)
            .asGif()
            .load(url)
            .into(view)
    }

    /**
     * 加载动图 如果不是动图就加载错误图
     * @param context Context
     * @param url String
     * @param view ImageView
     */
    fun loadImageGif(context: Context, url: String, error: Any, view: ImageView) {
        Glide.with(context)
            .asGif()
            .error(error)
            .load(url)
            .into(view)
    }

    /**
     * 加载圆形
     * @param context Context 上下文
     * @param url String      图片链接
     * @param view ImageView  被赋值的控件
     */
    fun loadImageRound(context: Context, url: String, view: ImageView) {
        Glide.with(context)
            .load(url)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(view)
    }

    /**
     * 加载圆角
     * @param context Context
     * @param url String
     * @param corners Int
     * @param view ImageView
     */
    fun loadImageCorners(context: Context, url: Any, corners: Int, view: ImageView) {
        Glide.with(context)
            .load(url)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(corners)))
            .into(view)
    }
    
}