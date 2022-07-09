package com.zhang.mydemo.ui.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.alipay.android.phone.scancode.export.ScanCallback
import com.alipay.android.phone.scancode.export.ScanRequest
import com.alipay.android.phone.scancode.export.adapter.MPScan
import com.alipay.android.phone.scancode.export.adapter.MPScanCallbackAdapter
import com.alipay.android.phone.scancode.export.adapter.MPScanResult
import com.alipay.android.phone.scancode.export.adapter.MPScanStarter
import com.zhang.mydemo.BaseDialog
import com.zhang.mydemo.ScanHelper
import com.zhang.mydemo.SelectDialog
import com.zhang.mydemo.base.fragment.BaseNetWorkFragment
import com.zhang.mydemo.databinding.FragmentMineBinding
import com.zhang.mydemo.ui.activity.WebViewActivity
import com.zhang.mydemo.viewmodel.MainViewModel
import com.zhang.utilslibiary.utils.singleClick
import com.zhang.utilslibiary.utils.toast.Toasty
import kotlinx.android.synthetic.main.fragment_mine.*
import org.jetbrains.anko.support.v4.runOnUiThread
import org.jetbrains.anko.support.v4.startActivity

class MineFragment : BaseNetWorkFragment<FragmentMineBinding, MainViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MineFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }

    override fun setListener() {

        val scanRequest = ScanRequest()
        scanRequest.setScanType(ScanRequest.ScanType.QRCODE)

        scanTv.singleClick {
            SelectDialog.Builder(requireActivity()).setTitle("请选择扫码样式").setList("全屏", "半屏", "自定义UI")
                .setSingleSelect().setSelect(0)
                .setListener(object : SelectDialog.OnListener<String> {
                    override fun onSelected(dialog: BaseDialog?, data: HashMap<Int, String>) {

                        for ((key, value) in data.entries) {
                            when (key) {
                                0 -> fullScreenQrCode(scanRequest)
                                1 -> scanQrCode(scanRequest)
                                2 -> customQrCode()
                            }
                        }

                    }

                })
                .show()
        }
    }

    //    scanRequest.setNotSupportAlbum(true) 设置是否显示相册
    private fun customQrCode() {
//        startActivity<CustomScanActivity>()

        ScanHelper.getInstance().scan(requireActivity(), object : ScanHelper.ScanCallback {
            override fun onScanResult(isProcessed: Boolean, result: Intent?) {
                if (!isProcessed) {
                    // 扫码界面点击物理返回键或左上角返回键
                    return
                }

                if (result == null || result.data == null) {
                    Toasty.error("扫码失败,请重试")
                    return
                }
                if (result.data.toString().contains("www.")) {

                    val parse = Uri.parse(result.data.toString())
                    val intent = Intent(Intent.ACTION_VIEW,parse)
                    startActivity(intent)

                    Toasty.success(result.data.toString())
                }
            }

        })
    }

    private fun fullScreenQrCode(scanRequest: ScanRequest) {
        MPScan.startMPaasScanFullScreenActivity(requireActivity(), scanRequest,
            object : MPScanCallbackAdapter() {
                override fun onScanFinish(
                    context: Context,
                    mpScanResult: MPScanResult,
                    mpScanStarter: MPScanStarter
                ): Boolean {

                    if (mpScanResult == null) {
                        Toasty.error("没有识别到码")
                        return false
                    }
                    startActivity<WebViewActivity>(
                        "url" to "www.baidu.com"
                    )
                    Toasty.success(mpScanResult.text)
                    return true
                }
            })
    }

    private fun scanQrCode(scanRequest: ScanRequest) {
        MPScan.startMPaasScanActivity(requireActivity(), scanRequest, object : ScanCallback {
            override fun onScanResult(p0: Boolean, p1: Intent?) {
                if (!p0) return
                runOnUiThread {
                    // 注意：本回调是在子线程中执行
                    if (p1 == null || p1.data == null) {
                        Toasty.error("扫码失败,请重试")
                        return@runOnUiThread
                    }
                    Toasty.success(p1.data.toString())

                }
            }

        })
    }

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun createObserver() {
    }
}