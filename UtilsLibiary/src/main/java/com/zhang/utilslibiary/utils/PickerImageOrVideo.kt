package com.zhang.utilslibiary.utils

import android.app.Activity
import androidx.fragment.app.Fragment
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia

/**
 * @Author : zhang
 * @Create Time : 2021/11/23
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */

object PickerImageOrVideo {

//   ======================================= 选择图片================================================

    //单选图片 带相机
    fun selectImageSingleCamera(activity: Activity) {
        return PictureSelector.create(activity)
            .openGallery(PictureMimeType.ofImage())
            .imageEngine(GlideEngine.createGlideEngine())
            .selectionMode(PictureConfig.SINGLE)
            .isPreviewImage(true)   // 是否可预览图片 true or false
            .cutOutQuality(50)     // 图片裁剪质量,默认无损
            .imageSpanCount(4) // 每行个数
            .isEnableCrop(false)   // 是否裁剪 true or false
            .isCompress(false)// 是否压缩 true or false
            .minimumCompressSize(1024) // 小于 100kb 的图片不压缩
            .forResult(PictureConfig.TYPE_IMAGE)
    }

    fun selectImageSingleCamera(fragment: Fragment) {
        return PictureSelector.create(fragment)
            .openGallery(PictureMimeType.ofImage())
            .imageEngine(GlideEngine.createGlideEngine())
            .selectionMode(PictureConfig.SINGLE)
            .isPreviewImage(true)   // 是否可预览图片 true or false
            .cutOutQuality(50)     // 图片裁剪质量,默认无损
            .imageSpanCount(4) // 每行个数
            .isEnableCrop(false)   // 是否裁剪 true or false
            .isCompress(false)// 是否压缩 true or false
            .minimumCompressSize(1024) // 小于 100kb 的图片不压缩
            .forResult(PictureConfig.TYPE_IMAGE)
    }

    // 单选图片 不带相机
    fun selectImageSingleNoCamera(activity: Activity) {
        return PictureSelector.create(activity)
            .openGallery(PictureMimeType.ofImage())
            .imageEngine(GlideEngine.createGlideEngine())
            .isCamera(false)
            .selectionMode(PictureConfig.SINGLE)
            .isPreviewImage(true)   // 是否可预览图片 true or false
            .cutOutQuality(50)     // 图片裁剪质量,默认无损
            .imageSpanCount(4) // 每行个数
            .isEnableCrop(false)   // 是否裁剪 true or false
            .isCompress(false)// 是否压缩 true or false
            .minimumCompressSize(1024) // 小于 100kb 的图片不压缩
            .forResult(PictureConfig.TYPE_IMAGE)
    }

    fun selectImageSingleNoCamera(fragment: Fragment) {
        return PictureSelector.create(fragment)
            .openGallery(PictureMimeType.ofImage())
            .imageEngine(GlideEngine.createGlideEngine())
            .isCamera(false)
            .selectionMode(PictureConfig.SINGLE)
            .isPreviewImage(true)   // 是否可预览图片 true or false
            .cutOutQuality(50)     // 图片裁剪质量,默认无损
            .imageSpanCount(4) // 每行个数
            .isEnableCrop(false)   // 是否裁剪 true or false
            .isCompress(false)// 是否压缩 true or false
            .minimumCompressSize(1024) // 小于 100kb 的图片不压缩
            .forResult(PictureConfig.TYPE_IMAGE)
    }

    // 多选图片 带相机
    fun selectImageMultipleCamera(activity: Activity) {
        return PictureSelector.create(activity)
            .openGallery(PictureMimeType.ofImage())
            .imageEngine(GlideEngine.createGlideEngine())
            .selectionMode(PictureConfig.MULTIPLE)
            .isPreviewImage(true)   // 是否可预览图片 true or false
            .cutOutQuality(50)     // 图片裁剪质量,默认无损
            .imageSpanCount(4) // 每行个数
            .isEnableCrop(false)   // 是否裁剪 true or false
            .isCompress(false)// 是否压缩 true or false
            .minimumCompressSize(1024) // 小于 100kb 的图片不压缩
            .forResult(PictureConfig.TYPE_IMAGE)
    }

    fun selectImageMultipleCamera(fragment: Fragment) {
        return PictureSelector.create(fragment)
            .openGallery(PictureMimeType.ofImage())
            .imageEngine(GlideEngine.createGlideEngine())
            .selectionMode(PictureConfig.MULTIPLE)
            .isPreviewImage(true)   // 是否可预览图片 true or false
            .cutOutQuality(50)     // 图片裁剪质量,默认无损
            .imageSpanCount(4) // 每行个数
            .isEnableCrop(false)   // 是否裁剪 true or false
            .isCompress(false)// 是否压缩 true or false
            .minimumCompressSize(1024) // 小于 100kb 的图片不压缩
            .forResult(PictureConfig.TYPE_IMAGE)
    }

    //多选图片 带相机 一次性最大张数
    fun selectImageMultipleCameraMax(activity: Activity, maxSelectNum: Int) {
        return PictureSelector.create(activity)
            .openGallery(PictureMimeType.ofImage())
            .maxSelectNum(maxSelectNum)
            .imageEngine(GlideEngine.createGlideEngine())
            .selectionMode(PictureConfig.MULTIPLE)
            .isPreviewImage(true)   // 是否可预览图片 true or false
            .cutOutQuality(50)     // 图片裁剪质量,默认无损
            .imageSpanCount(4) // 每行个数
            .isEnableCrop(false)   // 是否裁剪 true or false
            .isCompress(false)// 是否压缩 true or false
            .minimumCompressSize(1024) // 小于 100kb 的图片不压缩
            .forResult(PictureConfig.TYPE_IMAGE)
    }

    fun selectImageMultipleCameraMax(fragment: Fragment, maxSelectNum: Int) {
        return PictureSelector.create(fragment)
            .openGallery(PictureMimeType.ofImage())
            .maxSelectNum(maxSelectNum)
            .imageEngine(GlideEngine.createGlideEngine())
            .selectionMode(PictureConfig.MULTIPLE)
            .isPreviewImage(true)   // 是否可预览图片 true or false
            .cutOutQuality(50)     // 图片裁剪质量,默认无损
            .imageSpanCount(4) // 每行个数
            .isEnableCrop(false)   // 是否裁剪 true or false
            .isCompress(false)// 是否压缩 true or false
            .minimumCompressSize(1024) // 小于 100kb 的图片不压缩
            .forResult(PictureConfig.TYPE_IMAGE)
    }

    //多选图片 不带相机
    fun selectImageMultipleNoCamera(activity: Activity) {
        return PictureSelector.create(activity)
            .openGallery(PictureMimeType.ofImage())
            .imageEngine(GlideEngine.createGlideEngine())
            .isCamera(false)
            .selectionMode(PictureConfig.MULTIPLE)
            .isPreviewImage(true)   // 是否可预览图片 true or false
            .cutOutQuality(50)     // 图片裁剪质量,默认无损
            .imageSpanCount(4) // 每行个数
            .isEnableCrop(false)   // 是否裁剪 true or false
            .isCompress(false)// 是否压缩 true or false
            .minimumCompressSize(1024) // 小于 100kb 的图片不压缩
            .forResult(PictureConfig.TYPE_IMAGE)
    }

    fun selectImageMultipleNoCamera(fragment: Fragment) {
        return PictureSelector.create(fragment)
            .openGallery(PictureMimeType.ofImage())
            .imageEngine(GlideEngine.createGlideEngine())
            .isCamera(false)
            .selectionMode(PictureConfig.MULTIPLE)
            .isPreviewImage(true)   // 是否可预览图片 true or false
            .cutOutQuality(50)     // 图片裁剪质量,默认无损
            .imageSpanCount(4) // 每行个数
            .isEnableCrop(false)   // 是否裁剪 true or false
            .isCompress(false)// 是否压缩 true or false
            .minimumCompressSize(1024) // 小于 100kb 的图片不压缩
            .forResult(PictureConfig.TYPE_IMAGE)
    }

    //多选图片 不带相机  一次性最大张数
    fun selectImageMultipleNoCameraMax(activity: Activity, maxSelectNum: Int) {
        return PictureSelector.create(activity)
            .openGallery(PictureMimeType.ofImage())
            .imageEngine(GlideEngine.createGlideEngine())
            .maxSelectNum(maxSelectNum)
            .isCamera(false)
            .selectionMode(PictureConfig.MULTIPLE)
            .isPreviewImage(true)   // 是否可预览图片 true or false
            .cutOutQuality(50)     // 图片裁剪质量,默认无损
            .imageSpanCount(4) // 每行个数
            .isEnableCrop(false)   // 是否裁剪 true or false
            .isCompress(false)// 是否压缩 true or false
            .minimumCompressSize(1024) // 小于 100kb 的图片不压缩
            .forResult(PictureConfig.TYPE_IMAGE)
    }

//   ======================================= 选择视频================================================

    //单独选择视频 带相机
    fun selectVideoCamera(activity: Activity) {
        return PictureSelector.create(activity)
            .openGallery(PictureMimeType.ofVideo())
            .imageEngine(GlideEngine.createGlideEngine())
            .selectionMode(PictureConfig.SINGLE)
            .isPreviewImage(true)   // 是否可预览图片 true or false
            .cutOutQuality(50)     // 图片裁剪质量,默认无损
            .imageSpanCount(4) // 每行个数
            .isEnableCrop(false)   // 是否裁剪 true or false
            .isCompress(false)// 是否压缩 true or false
            .minimumCompressSize(1024) // 小于 100kb 的图片不压缩
            .forResult(PictureConfig.TYPE_VIDEO)
    }

    fun selectVideoCamera(fragment: Fragment) {
        return PictureSelector.create(fragment)
            .openGallery(PictureMimeType.ofVideo())
            .imageEngine(GlideEngine.createGlideEngine())
            .selectionMode(PictureConfig.SINGLE)
            .isPreviewImage(true)   // 是否可预览图片 true or false
            .cutOutQuality(50)     // 图片裁剪质量,默认无损
            .imageSpanCount(4) // 每行个数
            .isEnableCrop(false)   // 是否裁剪 true or false
            .isCompress(false)// 是否压缩 true or false
            .minimumCompressSize(1024) // 小于 100kb 的图片不压缩
            .forResult(PictureConfig.TYPE_VIDEO)
    }

    //单独选择视频 不带相机
    fun selectVideoNoCamera(activity: Activity) {
        return PictureSelector.create(activity)
            .openGallery(PictureMimeType.ofVideo())
            .imageEngine(GlideEngine.createGlideEngine())
            .isCamera(false)
            .selectionMode(PictureConfig.SINGLE)
            .isPreviewImage(true)   // 是否可预览图片 true or false
            .cutOutQuality(50)     // 图片裁剪质量,默认无损
            .imageSpanCount(4) // 每行个数
            .isEnableCrop(false)   // 是否裁剪 true or false
            .isCompress(false)// 是否压缩 true or false
            .minimumCompressSize(1024) // 小于 100kb 的图片不压缩
            .forResult(PictureConfig.TYPE_VIDEO)
    }

    fun selectVideoNoCamera(fragment: Fragment) {
        return PictureSelector.create(fragment)
            .openGallery(PictureMimeType.ofVideo())
            .imageEngine(GlideEngine.createGlideEngine())
            .isCamera(false)
            .selectionMode(PictureConfig.SINGLE)
            .isPreviewImage(true)   // 是否可预览图片 true or false
            .cutOutQuality(50)     // 图片裁剪质量,默认无损
            .imageSpanCount(4) // 每行个数
            .isEnableCrop(false)   // 是否裁剪 true or false
            .isCompress(false)// 是否压缩 true or false
            .minimumCompressSize(1024) // 小于 100kb 的图片不压缩
            .forResult(PictureConfig.TYPE_VIDEO)
    }

}