package com.zhang.mydemo.kotlin.imageorvideo

import android.content.Intent
import android.content.pm.ActivityInfo
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Environment
import android.view.SurfaceHolder
import android.view.View
import android.widget.RelativeLayout
import com.elvishew.xlog.XLog
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.entity.LocalMedia
import com.zhang.mydemo.base.BaseActivity
import com.zhang.utilslibiary.utils.GlideUtils
import com.zhang.utilslibiary.utils.PickerImageOrVideo
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_pick_view.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.toast
import kotlin.math.ceil
import android.media.MediaMetadataRetriever

import android.graphics.Bitmap
import java.io.IOException
import java.lang.IllegalArgumentException


class PickViewActivity : BaseActivity() {

    private var mMediaPlayer: MediaPlayer? = null

    private var mHolder: SurfaceHolder? = null

    private var currentVideoUrl = ""


    override fun getLayoutId(): Int = com.zhang.mydemo.R.layout.activity_pick_view

    override fun initData() {
        ivPageBack.singleClick { killMyself() }
        tvPageTitle.text = "测试PictureSelector"

    }

    override fun initView() {
        mMediaPlayer = MediaPlayer()
        mHolder = video.holder
        mHolder!!.setKeepScreenOn(true)
        mHolder!!.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                //        val url = "http://res.cloudinary.com/liuyuesha/video/upload/v1475978853/广告_bl4dbp.mp4"

                mMediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)

                mMediaPlayer!!.setDataSource(this@PickViewActivity, Uri.parse(currentVideoUrl))

                mMediaPlayer!!.isLooping = true

                // 把视频画面输出到SurfaceView
                mMediaPlayer!!.setDisplay(mHolder)

                // 通过异步的方式装载媒体资源
                mMediaPlayer!!.prepareAsync()

                //装载完毕回调
                mMediaPlayer!!.setOnPreparedListener { }
            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int,
            ) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {}
        })
        mMediaPlayer!!.setOnVideoSizeChangedListener { mp, width, height -> changeVideoSize() }

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
            if (mMediaPlayer!!.isPlaying) {
                mMediaPlayer!!.stop()
                currentVideoUrl = ""
            }
            PickerImageOrVideo.selectVideoCamera(this)
        }
        tv8.singleClick {
            if (mMediaPlayer!!.isPlaying) {
                mMediaPlayer!!.stop()
                currentVideoUrl = ""
            }
            PickerImageOrVideo.selectVideoNoCamera(this)
        }
        play.singleClick {
                if (!mMediaPlayer!!.isPlaying) {
            if (currentVideoUrl != "") {
                    video.visibility = View.VISIBLE
                    videoTV.visibility = View.INVISIBLE
                    mMediaPlayer!!.start()
                } else {
                    toast("暂无视频")
                }
            }
        }

        pause.singleClick {
            if (mMediaPlayer!!.isPlaying) {
                videoTV.visibility = View.INVISIBLE
                video.visibility = View.VISIBLE
                mMediaPlayer!!.pause()
            }
        }

        stop.singleClick {
            if (currentVideoUrl != "") {
                videoTV.visibility = View.VISIBLE
                video.visibility = View.INVISIBLE
                mMediaPlayer!!.stop()
                mMediaPlayer!!.release()
                currentVideoUrl = ""
            } else {
                toast("暂无视频")
            }
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

    /**
     * 获取本地视频的第一帧
     *
     * @param filePath
     * @return
     */
    fun getLocalVideoThumbnail(filePath: String?): Bitmap? {
        var bitmap: Bitmap? = null
        //MediaMetadataRetriever 是android中定义好的一个类，提供了统一
        //的接口，用于从输入的媒体文件中取得帧和元数据；
        val retriever = MediaMetadataRetriever()
        try {
            //根据文件路径获取缩略图
            retriever.setDataSource(filePath)
            //获得第一帧图片
            bitmap = retriever.frameAtTime
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } finally {
            retriever.release()
        }
        return bitmap
    }

    //改变视频的尺寸自适应。
    fun changeVideoSize() {
        var videoWidth = mMediaPlayer!!.videoWidth
        var videoHeight = mMediaPlayer!!.videoHeight
        val surfaceWidth = video!!.width
        val surfaceHeight = video!!.height

        //根据视频尺寸去计算->视频可以在sufaceView中放大的最大倍数。
        val max: Float
        max = if (resources.configuration.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            //竖屏模式下按视频宽度计算放大倍数值
            Math.max(videoWidth.toFloat() / surfaceWidth.toFloat(),
                videoHeight.toFloat() / surfaceHeight.toFloat())
        } else {
            //横屏模式下按视频高度计算放大倍数值
            Math.max(videoWidth.toFloat() / surfaceHeight.toFloat(),
                videoHeight.toFloat() / surfaceWidth.toFloat())
        }

        //视频宽高分别/最大倍数值 计算出放大后的视频尺寸
        videoWidth = ceil((videoWidth.toFloat() / max).toDouble()).toInt()
        videoHeight = ceil((videoHeight.toFloat() / max).toDouble()).toInt()

        //无法直接设置视频尺寸，将计算出的视频尺寸设置到surfaceView 让视频自动填充。
        val params = RelativeLayout.LayoutParams(videoWidth, videoHeight)
        params.addRule(RelativeLayout.CENTER_VERTICAL, video!!.id)
        video!!.layoutParams = params
    }

    override fun onDestroy() {
        if (mMediaPlayer != null) {
            if (mMediaPlayer!!.isPlaying) {
                mMediaPlayer!!.stop()
            }
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
        super.onDestroy()
    }
}