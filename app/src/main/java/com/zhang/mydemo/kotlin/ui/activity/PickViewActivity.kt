package com.zhang.mydemo.kotlin.ui.activity

import android.content.Intent
import com.example.baselibiary.base.BaseActivity
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType.TYPE_IMAGE
import com.luck.picture.lib.entity.LocalMedia
import com.zhang.mydemo.databinding.ActivityPickViewBinding
import com.zhang.utilslibiary.utils.GlideUtils
import com.zhang.utilslibiary.utils.PickerImageOrVideo
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_pick_view.*
import kotlinx.android.synthetic.main.layout_title.*


class PickViewActivity : BaseActivity<ActivityPickViewBinding>() {

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
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            val selectList = PictureSelector.obtainSelectorList(data) as ArrayList<LocalMedia>
            if (selectList.size > 0) {
                when (requestCode) {
                    TYPE_IMAGE -> {
                        GlideUtils.loadImage(this, selectList[0].path, image)
                    }
                }
            }
        }
    }
}