package com.zhang.mydemo.ui.activity

import android.content.pm.ActivityInfo
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.View
import android.widget.RelativeLayout
import com.zhang.mydemo.R
import com.zhang.mydemo.base.activity.BaseActivity
import com.zhang.mydemo.databinding.ActivityTestSurfaceViewBinding
import kotlinx.android.synthetic.main.activity_test_surface_view.*

class TestSurfaceViewActivity : BaseActivity<ActivityTestSurfaceViewBinding>(),
    View.OnClickListener {

    private var mMediaPlayer: MediaPlayer? = null

    override fun initData() {
        mMediaPlayer = MediaPlayer()
        test_surfaceView.holder.setKeepScreenOn(true)
        test_surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                //开始播放
                readyPlay()
            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {}
        })
        mMediaPlayer!!.setOnVideoSizeChangedListener { mp, width, height -> changeVideoSize() }
    }

    override fun setListener() {}

    //改变视频的尺寸自适应。
    fun changeVideoSize() {
        var videoWidth = mMediaPlayer!!.videoWidth
        var videoHeight = mMediaPlayer!!.videoHeight
        val surfaceWidth = test_surfaceView!!.width
        val surfaceHeight = test_surfaceView!!.height

        //根据视频尺寸去计算->视频可以在sufaceView中放大的最大倍数。
        val max: Float
        max = if (resources.configuration.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            //竖屏模式下按视频宽度计算放大倍数值
            Math.max(
                videoWidth.toFloat() / surfaceWidth.toFloat(),
                videoHeight.toFloat() / surfaceHeight.toFloat()
            )
        } else {
            //横屏模式下按视频高度计算放大倍数值
            Math.max(
                videoWidth.toFloat() / surfaceHeight.toFloat(),
                videoHeight.toFloat() / surfaceWidth.toFloat()
            )
        }

        //视频宽高分别/最大倍数值 计算出放大后的视频尺寸
        videoWidth = Math.ceil((videoWidth.toFloat() / max).toDouble()).toInt()
        videoHeight = Math.ceil((videoHeight.toFloat() / max).toDouble()).toInt()

        //无法直接设置视频尺寸，将计算出的视频尺寸设置到surfaceView 让视频自动填充。
        val params = RelativeLayout.LayoutParams(videoWidth, videoHeight)
        params.addRule(RelativeLayout.CENTER_VERTICAL, test_parent_play!!.id)
        test_surfaceView!!.layoutParams = params
    }

    //准好播放了
    fun readyPlay() {
        val url = "http://res.cloudinary.com/liuyuesha/video/upload/v1475978853/广告_bl4dbp.mp4"
        mMediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
        try {
            mMediaPlayer!!.setDataSource(this, Uri.parse(url))
            mMediaPlayer!!.isLooping = true
            // 把视频画面输出到SurfaceView
            mMediaPlayer!!.setDisplay(test_surfaceView.holder)
            // 通过异步的方式装载媒体资源
            mMediaPlayer!!.prepareAsync()
            mMediaPlayer!!.setOnPreparedListener { //装载完毕回调
                play()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 播放或者暂停
     */
    private fun play() {
        if (mMediaPlayer != null) {
            if (mMediaPlayer!!.isPlaying) {
                mMediaPlayer!!.pause()
                test_btn_play!!.text = "播放"
            } else {
                mMediaPlayer!!.start()
                test_btn_play!!.text = "暂停"
            }
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.test_btn_play) {
            play()
        }
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

    override fun onPointerCaptureChanged(hasCapture: Boolean) {}

    override fun initView(savedInstanceState: Bundle?) {

    }
}