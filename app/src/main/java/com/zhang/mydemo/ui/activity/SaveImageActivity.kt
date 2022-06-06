package com.zhang.mydemo.ui.activity

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.zhang.mydemo.base.activity.BaseActivity
import com.zhang.mydemo.databinding.ActivitySaveImageBinding
import com.zhang.utilslibiary.utils.saveimage.FileSaveUtil
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_save_image.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SaveImageActivity : BaseActivity<ActivitySaveImageBinding>() {
    //权限Code
    var REQUEST_CODE_LAUNCH = 10011
    var permissionsREAD = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

//    var path =
//        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Ffile02.16sucai.com%2Fd%2Ffile%2F2014%2F0829%2F372edfeb74c3119b666237bd4af92be5.jpg&refer=http%3A%2F%2Ffile02.16sucai.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1648708406&t=ca9d3a371ddad53fbc5fa074db2090cc"

    var path =
        "https://www.wallpapermaiden.com/image/2016/06/12/firewatch-forest-night-light-games-934.jpg"


    override fun initView(savedInstanceState: Bundle?) {
        // 判断权限
        val isHave = checkPermissions(this, permissionsREAD, REQUEST_CODE_LAUNCH)
            showView()
    }

    /**
     * 判断权限
     */
    fun onRequestPermissionsResult(
        activity: Activity?, permissions: Array<String?>, grantResults: IntArray
    ): BooleanArray? {
        var result = true
        var isNerverAsk = false
        val length = grantResults.size
        for (i in 0 until length) {
            val permission = permissions[i]
            val grandResult = grantResults[i]
            if (grandResult == PackageManager.PERMISSION_DENIED) {
                result = false
                if (!ActivityCompat.shouldShowRequestPermissionRationale(
                        activity!!,
                        permission!!
                    )
                ) isNerverAsk = true
            }
        }
        return booleanArrayOf(result, isNerverAsk)
    }

    private fun showView() {
        Glide.with(this).load(path).into(ivImage)
    }

    fun savePic(path: String) {

        //携程
        GlobalScope.launch(Dispatchers.IO) {
            val file = Glide.with(this@SaveImageActivity).asFile().load(path).submit().get()

            // 文件夹位置
            val parentPath = FileSaveUtil.getPath(this@SaveImageActivity)
            //文件名
            val fileName = System.currentTimeMillis().toString() + ".png"
            //新文件文件地址
            val filePath = parentPath + fileName
            //复制地址(部分机型 不复制到指定文件夹,相册不更新)
            FileSaveUtil.copyFile(file.path, filePath)
            // 相册刷新
            val isSave = FileSaveUtil.insertMediaPic(this@SaveImageActivity, filePath)

            withContext(Dispatchers.Main) {
                //主线程里更新 UI
                if (isSave) {
                    Toast.makeText(this@SaveImageActivity, "成功了", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@SaveImageActivity, "失败了", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    /**
     * 授权结果回调
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_LAUNCH) {
            val hasPermissions =
                onRequestPermissionsResult(this, permissions, grantResults)
            if (hasPermissions!![0]) {
                showView()
            } else {
                Toast.makeText(this@SaveImageActivity, "没权限", Toast.LENGTH_SHORT).show()

            }
        }
    }

    /**
     * 校验权限
     */
    fun checkPermissions(
        activity: Activity?,
        permissions: Array<String>,
        requestCode: Int
    ): Boolean { //Android6.0以下默认有权限
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return true
        val needList: MutableList<String> = ArrayList()
        var needShowRationale = false
        val length = permissions.size
        for (i in 0 until length) {
            val permisson = permissions[i]
            if (TextUtils.isEmpty(permisson)) continue
            if (ActivityCompat.checkSelfPermission(activity!!, permisson)
                != PackageManager.PERMISSION_GRANTED
            ) {
                needList.add(permisson)
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        activity,
                        permisson
                    )
                ) needShowRationale = true
            }
        }
        return if (needList.size != 0) {
            if (needShowRationale) {
                //
                return false
            }
            ActivityCompat.requestPermissions(activity!!, needList.toTypedArray(), requestCode)
            false
        } else {
            true
        }
    }


    override fun initData() {
    }

    override fun setListener() {
        btSaveImage.singleClick {
            savePic(path)
        }
    }
}