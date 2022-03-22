package com.zhang.utilslibiary.utils

import android.app.Activity
import androidx.fragment.app.Fragment
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.config.SelectModeConfig
import com.luck.pictureselector.GlideEngine

/**
 * @Author : zhang
 * @Create Time : 2021/11/23
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */


//   ======================================= 选择图片================================================

//单选图片 带相机
fun selectImageSingleCamera(activity: Activity) {
    return PictureSelector.create(activity)
        .openGallery(SelectMimeType.ofImage())
        .setImageEngine(GlideEngine.createGlideEngine())
        .setSelectionMode(SelectModeConfig.SINGLE)
        .isPreviewImage(true)   // 是否可预览图片 true or false
        .setImageSpanCount(4)// 每行个数
        .forResult(SelectMimeType.TYPE_IMAGE)
}

fun selectImageSingleCamera(fragment: Fragment) {
    return PictureSelector.create(fragment)
        .openGallery(SelectMimeType.ofImage())
        .setImageEngine(GlideEngine.createGlideEngine())
        .setSelectionMode(SelectModeConfig.SINGLE)
        .isPreviewImage(true)   // 是否可预览图片 true or false
        .setImageSpanCount(4)// 每行个数
        .forResult(SelectMimeType.TYPE_IMAGE)
}

// 单选图片 不带相机
fun selectImageSingleNoCamera(activity: Activity) {
    return PictureSelector.create(activity)
        .openGallery(SelectMimeType.ofImage())
        .setImageEngine(GlideEngine.createGlideEngine())
        .isDisplayCamera(false)
        .setSelectionMode(SelectModeConfig.SINGLE)
        .isPreviewImage(true)   // 是否可预览图片 true or false
        .setImageSpanCount(4)// 每行个数
        .forResult(SelectMimeType.TYPE_IMAGE)
}

fun selectImageSingleNoCamera(fragment: Fragment) {
    return PictureSelector.create(fragment)
        .openGallery(SelectMimeType.ofImage())
        .setImageEngine(GlideEngine.createGlideEngine())
        .isDisplayCamera(false)
        .setSelectionMode(SelectModeConfig.SINGLE)
        .isPreviewImage(true)   // 是否可预览图片 true or false
        .setImageSpanCount(4)// 每行个数
        .forResult(SelectMimeType.TYPE_IMAGE)
}

// 多选图片 带相机
fun selectImageMultipleCamera(activity: Activity) {
    return PictureSelector.create(activity)
        .openGallery(SelectMimeType.ofImage())
        .setImageEngine(GlideEngine.createGlideEngine())
        .setSelectionMode(SelectModeConfig.MULTIPLE)
        .isPreviewImage(true)   // 是否可预览图片 true or false
        .setImageSpanCount(4)// 每行个数
        .forResult(SelectMimeType.TYPE_IMAGE)
}

fun selectImageMultipleCamera(fragment: Fragment) {
    return PictureSelector.create(fragment)
        .openGallery(SelectMimeType.ofImage())
        .setImageEngine(GlideEngine.createGlideEngine())
        .setSelectionMode(SelectModeConfig.MULTIPLE)
        .isPreviewImage(true)   // 是否可预览图片 true or false
        .setImageSpanCount(4)// 每行个数
        .forResult(SelectMimeType.TYPE_IMAGE)
}

//多选图片 带相机 一次性最大张数
fun selectImageMultipleCameraMax(activity: Activity, maxSelectNum: Int) {
    return PictureSelector.create(activity)
        .openGallery(SelectMimeType.ofImage())
        .setMaxSelectNum(maxSelectNum)
        .setImageEngine(GlideEngine.createGlideEngine())
        .setSelectionMode(SelectModeConfig.MULTIPLE)
        .isPreviewImage(true)   // 是否可预览图片 true or false
        .setImageSpanCount(4)// 每行个数
        .forResult(SelectMimeType.TYPE_IMAGE)
}

fun selectImageMultipleCameraMax(fragment: Fragment, maxSelectNum: Int) {
    return PictureSelector.create(fragment)
        .openGallery(SelectMimeType.ofImage())
        .setMaxSelectNum(maxSelectNum)
        .setImageEngine(GlideEngine.createGlideEngine())
        .setSelectionMode(SelectModeConfig.MULTIPLE)
        .isPreviewImage(true)   // 是否可预览图片 true or false
        .setImageSpanCount(4)// 每行个数
        .forResult(SelectMimeType.TYPE_IMAGE)
}

//多选图片 不带相机
fun selectImageMultipleNoCamera(activity: Activity) {
    return PictureSelector.create(activity)
        .openGallery(SelectMimeType.ofImage())
        .setImageEngine(GlideEngine.createGlideEngine())
        .isDisplayCamera(false)
        .setSelectionMode(SelectModeConfig.MULTIPLE)
        .isPreviewImage(true)   // 是否可预览图片 true or false
        .setImageSpanCount(4)// 每行个数
        .forResult(SelectMimeType.TYPE_IMAGE)
}

fun selectImageMultipleNoCamera(fragment: Fragment) {
    return PictureSelector.create(fragment)
        .openGallery(SelectMimeType.ofImage())
        .setImageEngine(GlideEngine.createGlideEngine())
        .isDisplayCamera(false)
        .setSelectionMode(SelectModeConfig.MULTIPLE)
        .isPreviewImage(true)   // 是否可预览图片 true or false
        .setImageSpanCount(4)// 每行个数
        .forResult(SelectMimeType.TYPE_IMAGE)
}

//多选图片 不带相机  一次性最大张数
fun selectImageMultipleNoCameraMax(activity: Activity, maxSelectNum: Int) {
    return PictureSelector.create(activity)
        .openGallery(SelectMimeType.ofImage())
        .setImageEngine(GlideEngine.createGlideEngine())
        .setMaxSelectNum(maxSelectNum)
        .isDisplayCamera(false)
        .setSelectionMode(SelectModeConfig.MULTIPLE)
        .isPreviewImage(true)   // 是否可预览图片 true or false
        .setImageSpanCount(4)// 每行个数
        .forResult(SelectMimeType.TYPE_IMAGE)
}

//   ======================================= 选择视频================================================

//单独选择视频 带相机
fun selectVideoCamera(activity: Activity) {
    return PictureSelector.create(activity)
        .openGallery(SelectMimeType.ofVideo())
        .setImageEngine(GlideEngine.createGlideEngine())
        .setSelectionMode(SelectModeConfig.SINGLE)
        .isPreviewImage(true)   // 是否可预览图片 true or false
        .setImageSpanCount(4)// 每行个数
        .forResult(SelectMimeType.TYPE_IMAGE)
}

fun selectVideoCamera(fragment: Fragment) {
    return PictureSelector.create(fragment)
        .openGallery(SelectMimeType.ofVideo())
        .setImageEngine(GlideEngine.createGlideEngine())
        .setSelectionMode(SelectModeConfig.SINGLE)
        .isPreviewImage(true)   // 是否可预览图片 true or false
        .setImageSpanCount(4)// 每行个数
        .forResult(SelectMimeType.TYPE_VIDEO)
}

//单独选择视频 不带相机
fun selectVideoNoCamera(activity: Activity) {
    return PictureSelector.create(activity)
        .openGallery(SelectMimeType.ofVideo())
        .setImageEngine(GlideEngine.createGlideEngine())
        .isDisplayCamera(false)
        .setSelectionMode(SelectModeConfig.SINGLE)
        .isPreviewImage(true)   // 是否可预览图片 true or false
        .setImageSpanCount(4)// 每行个数
        .forResult(SelectMimeType.TYPE_VIDEO)
}

fun selectVideoNoCamera(fragment: Fragment) {
    return PictureSelector.create(fragment)
        .openGallery(SelectMimeType.ofVideo())
        .setImageEngine(GlideEngine.createGlideEngine())
        .isDisplayCamera(false)
        .setSelectionMode(SelectModeConfig.SINGLE)
        .isPreviewImage(true)   // 是否可预览图片 true or false
        .setImageSpanCount(4)// 每行个数
        .forResult(SelectMimeType.TYPE_VIDEO)
}
