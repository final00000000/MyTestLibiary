package com.zhang.mydemo

import android.Manifest
import com.zhang.utilslibiary.utils.toast.Toasty.error
import android.app.Activity
import android.content.Context
import com.zhang.mydemo.CustomScanActivity
import com.zhang.mydemo.ScanView
import com.alipay.android.phone.scancode.export.adapter.MPScanner
import android.os.Bundle
import android.os.Build
import android.view.WindowManager
import com.alipay.android.phone.scancode.export.adapter.MPRecognizeType
import com.alipay.android.phone.scancode.export.listener.MPScanListener
import com.alipay.android.phone.scancode.export.adapter.MPScanResult
import com.alipay.android.phone.scancode.export.adapter.MPScanError
import com.zhang.utilslibiary.utils.toast.Toasty
import com.alipay.android.phone.scancode.export.listener.MPImageGrayListener
import android.content.Intent
import androidx.core.app.ActivityCompat
import android.text.TextUtils
import android.content.pm.PackageManager
import android.graphics.Bitmap
import com.zhang.mydemo.ScanHelper
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.Rect
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.core.content.PermissionChecker
import com.alipay.mobile.common.logging.api.LoggerFactory
import kotlinx.android.synthetic.main.activity_custom_scan.*
import kotlinx.android.synthetic.main.view_scan.*
import timber.log.Timber
import java.io.InputStream
import java.lang.Exception

class CustomScanActivity : Activity() {

    private var isFirstStart = true

    private var isPermissionGranted = false

    private var isScanning = false

    private var isPaused = false

    private var scanRect: Rect? = null

    private var mpScanner: MPScanner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_scan)

        // 设置沉浸模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            )
        }
        findViewById<View>(R.id.gallery).setOnClickListener { pickImageFromGallery() }
        torch.setOnClickListener { switchTorch() }

        findViewById<View>(R.id.back).setOnClickListener { onBackPressed() }
        initMPScanner()
        checkCameraPermission()
    }

    private fun initMPScanner() {
        mpScanner = MPScanner(this)
        mpScanner!!.setRecognizeType(
            MPRecognizeType.QR_CODE,
            MPRecognizeType.BAR_CODE,
            MPRecognizeType.DM_CODE,
            MPRecognizeType.PDF417_CODE
        )
        mpScanner!!.mpScanListener = object : MPScanListener {
            override fun onConfiguration() {
                mpScanner!!.setDisplayView(surface_view)
            }

            override fun onStart() {
                if (!isPaused) {
                    runOnUiThread {
                        if (!isFinishing) {
                            initScanRect()
                            scan_view!!.onStartScan()
                        }
                    }
                }
            }

            override fun onSuccess(mpScanResult: MPScanResult) {
                mpScanner!!.beep()
                onScanSuccess(mpScanResult)
            }

            override fun onError(mpScanError: MPScanError) {
                if (!isPaused) {
                    runOnUiThread { error(getString(R.string.camera_open_error)) }
                }
            }
        }
        var isToasty = true
        mpScanner!!.mpImageGrayListener = MPImageGrayListener { gray ->
            // 注意：该回调在昏暗环境下可能会连续多次执行
            if (gray < MPImageGrayListener.LOW_IMAGE_GRAY) {
                runOnUiThread {
                    if (isToasty) {
                        error("光线太暗，请打开手电筒")
                        isToasty = false
                    }
                }
            }
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE_PHOTO)
    }

    private fun switchTorch() {
        val torchOn = mpScanner!!.switchTorch()
        torch.isSelected = torchOn
    }

    public override fun onPause() {
        super.onPause()
        isPaused = true
        if (isScanning) {
            stopScan()
        }
    }

    public override fun onResume() {
        super.onResume()
        isPaused = false
        if (!isFirstStart && isPermissionGranted) {
            startScan()
        }
    }

    public override fun onDestroy() {
        super.onDestroy()
        mpScanner!!.release()
    }

    private fun checkCameraPermission() {
        if (PermissionChecker.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PermissionChecker.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CODE_PERMISSION
            )
        } else {
            onPermissionGranted()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSION) {
            val length = Math.min(permissions.size, grantResults.size)
            for (i in 0 until length) {
                if (TextUtils.equals(permissions[i], Manifest.permission.CAMERA)) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        error(getString(R.string.camera_no_permission))
                    } else {
                        onPermissionGranted()
                    }
                    break
                }
            }
        }
    }

    private fun onPermissionGranted() {
        isPermissionGranted = true
        startScan()
    }

    private fun scanFromUri(uri: Uri?) {
        val bitmap = uri2Bitmap(this, uri)
        if (bitmap == null) {
            notifyScanResult(true, null)
            finish()
        } else {
            Thread({
                val mpScanResult = mpScanner!!.scanFromBitmap(bitmap)
                mpScanner!!.beep()
                onScanSuccess(mpScanResult)
            }, "scanFromUri").start()
        }
    }

    private fun onScanSuccess(result: MPScanResult?) {
        runOnUiThread {
            if (result == null) {
                notifyScanResult(true, null)
            } else {
                val intent = Intent()
                intent.data = Uri.parse(result.text)
                notifyScanResult(true, intent)
            }
            finish()
        }
    }

    private fun startScan() {
        try {
            mpScanner!!.openCameraAndStartScan()
            isScanning = true
        } catch (e: Exception) {
            isScanning = false
            Timber.e("CustomScanActivity_216行_2022/4/25_11:26：${e.message}")
        }
    }

    private fun stopScan() {
        mpScanner!!.closeCameraAndStopScan()
        scan_view!!.onStopScan()
        isScanning = false
        if (isFirstStart) {
            isFirstStart = false
        }
    }

    private fun initScanRect() {
        if (scanRect == null) {
            scanRect = scan_view!!.getScanRect(
                mpScanner!!.camera, surface_view!!.width, surface_view!!.height
            )
            val cropWidth = scan_view!!.cropWidth
            Timber.e("CustomScanActivity_235行_2022/4/25_11:27：${cropWidth}")
            if (cropWidth > 0) {
                // 预览放大 ＝ 屏幕宽 ／ 裁剪框宽
                val wm = getSystemService(WINDOW_SERVICE) as WindowManager
                val screenWith = wm.defaultDisplay.width.toFloat()
                val screenHeight = wm.defaultDisplay.height.toFloat()
                var previewScale = screenWith / cropWidth
                if (previewScale < 1.0f) {
                    previewScale = 1.0f
                }
                if (previewScale > 1.5f) {
                    previewScale = 1.5f
                }
                Timber.e("CustomScanActivity_248行_2022/4/25_11:27：${previewScale}")
                val transform = Matrix()
                transform.setScale(previewScale, previewScale, screenWith / 2, screenHeight / 2)
                surface_view!!.setTransform(transform)
            }
        }
        mpScanner!!.setScanRegion(scanRect)
    }

    private fun notifyScanResult(isProcessed: Boolean, resultData: Intent?) {
        ScanHelper.getInstance().notifyScanResult(isProcessed, resultData)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        notifyScanResult(false, null)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null) {
            return
        }
        if (requestCode == REQUEST_CODE_PHOTO) {
            scanFromUri(data.data)
        }
    }

    companion object {
        private const val REQUEST_CODE_PERMISSION = 1
        private const val REQUEST_CODE_PHOTO = 2
        fun uri2Bitmap(context: Context, uri: Uri?): Bitmap? {
            var bitmap: Bitmap? = null
            val `in`: InputStream?
            try {
                `in` = context.contentResolver.openInputStream(uri!!)
                if (`in` != null) {
                    bitmap = BitmapFactory.decodeStream(`in`)
                    `in`.close()
                }
            } catch (e: Exception) {
                Timber.e("CustomScanActivity_250行_2022/4/25_11:01：\${}" + e.message)
            }
            return bitmap
        }
    }
}