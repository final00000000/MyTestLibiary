package com.zhang.mydemo.java.ui.activity;

import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.zhang.mydemo.R;
import com.example.baselibiary.base.BaseActivity;
import com.zhang.mydemo.databinding.ActivityTestSurfaceViewBinding;

public class TestSurfaceViewActivity extends BaseActivity<ActivityTestSurfaceViewBinding> implements View.OnClickListener {


    private SurfaceView mSurfaceView;
    private MediaPlayer mMediaPlayer;
    private SurfaceHolder mHolder;
    private Button mBtnPlay;
    private RelativeLayout mParent;


    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mBtnPlay = findViewById(R.id.test_btn_play);
        mSurfaceView = findViewById(R.id.test_surfaceView);
        mParent = findViewById(R.id.test_parent_play);

        mBtnPlay.setOnClickListener(this);

        mMediaPlayer = new MediaPlayer();
        mHolder = mSurfaceView.getHolder();
        mHolder.setKeepScreenOn(true);

        mHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                //开始播放
                readyPlay();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });

        mMediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
            @Override
            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                changeVideoSize();
            }
        });
    }

    @Override
    protected void setListener() {

    }

    //改变视频的尺寸自适应。
    public void changeVideoSize() {
        int videoWidth = mMediaPlayer.getVideoWidth();
        int videoHeight = mMediaPlayer.getVideoHeight();

        int surfaceWidth = mSurfaceView.getWidth();
        int surfaceHeight = mSurfaceView.getHeight();

        //根据视频尺寸去计算->视频可以在sufaceView中放大的最大倍数。
        float max;
        if (getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            //竖屏模式下按视频宽度计算放大倍数值
            max = Math.max((float) videoWidth / (float) surfaceWidth, (float) videoHeight / (float) surfaceHeight);
        } else {
            //横屏模式下按视频高度计算放大倍数值
            max = Math.max(((float) videoWidth / (float) surfaceHeight), (float) videoHeight / (float) surfaceWidth);
        }

        //视频宽高分别/最大倍数值 计算出放大后的视频尺寸
        videoWidth = (int) Math.ceil((float) videoWidth / max);
        videoHeight = (int) Math.ceil((float) videoHeight / max);

        //无法直接设置视频尺寸，将计算出的视频尺寸设置到surfaceView 让视频自动填充。
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(videoWidth, videoHeight);
        params.addRule(RelativeLayout.CENTER_VERTICAL, mParent.getId());
        mSurfaceView.setLayoutParams(params);
    }

    //准好播放了
    public void readyPlay() {
        String url = "http://res.cloudinary.com/liuyuesha/video/upload/v1475978853/广告_bl4dbp.mp4";
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mMediaPlayer.setDataSource(this, Uri.parse(url));
            mMediaPlayer.setLooping(true);
            // 把视频画面输出到SurfaceView
            mMediaPlayer.setDisplay(mHolder);
            // 通过异步的方式装载媒体资源
            mMediaPlayer.prepareAsync();

            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    //装载完毕回调
                    play();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 播放或者暂停
     */
    private void play() {
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.pause();
                mBtnPlay.setText("播放");
            } else {
                mMediaPlayer.start();
                mBtnPlay.setText("暂停");
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.test_btn_play) {
            play();
        }
    }

    @Override
    protected void onDestroy() {
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        super.onDestroy();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}