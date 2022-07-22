package com.zhang.utilslibiary.utils.saveimage

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import com.bumptech.glide.Glide
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileNotFoundException

/**
 * @Author : zhang
 * @Create Time : 2022/7/8
 * @Class Describe : 描述
 * @Project Name : demo
 */
object SavePhoto {

    /**
     * 保存图片到相册 string格式
     */
    fun saveToGallery(context: Context, path: String, success: (it: Boolean) -> Unit) {
        //携程
        GlobalScope.launch(Dispatchers.IO) {
            val file = Glide.with(context).asFile().load(path).submit().get()

            // 文件夹位置
            val parentPath = FileSaveUtil.getPath(context)
            //文件名
            val fileName = System.currentTimeMillis().toString() + path.substring(path.lastIndexOf(".")) ?: ".jpg"
            //新文件文件地址
            val filePath = parentPath + fileName
            //复制地址(部分机型 不复制到指定文件夹,相册不更新)
            FileSaveUtil.copyFile(file.path, filePath)
            // 相册刷新
            val isSave = FileSaveUtil.insertMediaPic(context, filePath)

            withContext(Dispatchers.Main) {
                //主线程里更新 UI
                if (isSave) {
                    success(true)
                } else {
                    success(false)
                }
            }

        }
    }

}