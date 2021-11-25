package com.zhang.mydemo.kotlin.imageorvideo

import android.content.Intent
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.entity.LocalMedia
import com.zhang.mydemo.base.BaseActivity
import com.zhang.utilslibiary.utils.GlideUtils
import com.zhang.utilslibiary.utils.PickerImageOrVideo
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_pick_view.*
import kotlinx.android.synthetic.main.layout_title.*


class PickViewActivity : BaseActivity() {

    private var currentVideoUrl = ""

    override fun getLayoutId(): Int = com.zhang.mydemo.R.layout.activity_pick_view

    override fun initData() {
        ivPageBack.singleClick { killMyself() }
        tvPageTitle.text = "测试PictureSelector"

    }

    override fun initView() {

    }

    override fun setListener() {
        tv1.singleClick {
            PickerImageOrVideo.selectImageSingleCamera(this)
        }
        tv2.singleClick {
            PickerImageOrVideo.selectImageSingleNoCamera(this)
        }
        tv3.singleClick {
            PickerImageOrVideo.selectImageMultipleCamera(this)
        }
        tv4.singleClick {
            PickerImageOrVideo.selectImageMultipleNoCameraMax(this, 6)
        }
        tv5.singleClick {
            PickerImageOrVideo.selectImageMultipleNoCamera(this)
        }
        tv6.singleClick {
            PickerImageOrVideo.selectImageMultipleCameraMax(this, 6)
        }

        tv7.singleClick {
            PickerImageOrVideo.selectVideoCamera(this)
        }
        tv8.singleClick {
            PickerImageOrVideo.selectVideoNoCamera(this)
        }
        play.singleClick {
            if(currentVideoUrl!=""){
                video.setUp(currentVideoUrl,"这是一个标题")
            }
        }

        pause.singleClick {
        }

        stop.singleClick {
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            val selectList = PictureSelector.obtainMultipleResult(data) as ArrayList<LocalMedia>
            if (selectList.size > 0) {
                when (requestCode) {
                    PictureConfig.TYPE_IMAGE -> {
                        GlideUtils.loadImage(this, selectList[0].path, image)
                    }
                    PictureConfig.TYPE_VIDEO -> {
                        currentVideoUrl = selectList[0].path
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}