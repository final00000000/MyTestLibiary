package com.zhang.mydemo.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.StyleSpan
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.core.app.ShareCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SizeReadyCallback
import com.zhang.mydemo.R
import com.zhang.mydemo.base.fragment.BaseNetWorkFragment
import com.zhang.mydemo.databinding.FragmentHomeBinding
import com.zhang.mydemo.ui.activity.RecyclerViewDraggableActivity
import com.zhang.mydemo.ui.activity.TabLayoutViewPagerDeleteActivity
import com.zhang.mydemo.ui.activity.WebViewActivity
import com.zhang.mydemo.viewmodel.HomeViewModel
import com.zhang.utilslibiary.utils.*
import com.zhang.utilslibiary.utils.dialog.DialogUtils
import com.zhang.utilslibiary.utils.random.random
import com.zhang.utilslibiary.utils.toast.Toasty
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity
import timber.log.Timber


class HomeFragment : BaseNetWorkFragment<FragmentHomeBinding, HomeViewModel>() {

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        Timber.e("HomeFragment_36行_2022/7/9_11:36：${IpUtils.ipAddress}")
        Timber.e("HomeFragment_37行_2022/7/9_11:36：${IpUtils.outNetIP}")
//        Timber.e("HomeFragment_38行_2022/7/9_11:36：${IpUtils.GetNetIp()}")
        initDialog()
    }


    private fun generate(str: String) {
        val inflate = layoutInflater.inflate(R.layout.item_avatar, null, false)
        val tv = inflate.find<TextView>(R.id.tv)

        tv.text = when (str.length) {
            3 -> {
                str.substring(str.length - 2)
            }
            2 -> {
                str
            }
            1 -> {
                str
            }
            else -> {
                if (str.length > 3) {
                    str.substring(str.length - 2)
                } else {
                    ""
                }
            }
        }


        val viewBitmap = BitmapUtils.createBitmapByView(requireActivity(), inflate)
        Timber.e("HomeFragment_50行_2022/7/29_16:59：${viewBitmap!!.byteCount}")
        Glide.with(this).load(viewBitmap)
            .into(mViewBinding.siv).getSize(object : SizeReadyCallback {
                override fun onSizeReady(width: Int, height: Int) {
                    Timber.e("HomeFragment_57行_2022/7/29_17:10：${width}  $height")
                }

            })
    }

    private fun initDialog() {
        val view = layoutInflater.inflate(R.layout.item_first_dialog, null, false)
        val centerDialog =
            DialogUtils.getCenterDialog(requireActivity(), view, Gravity.CENTER, false)

//        view.find<TextView>(R.id.tv_content).movementMethod = ScrollingMovementMethod.getInstance()
        view.find<TextView>(R.id.tv_content).text = getStrBuilder()
        view.find<TextView>(R.id.tv_content).movementMethod = LinkMovementMethod.getInstance()

        view.find<TextView>(R.id.tv_exit).text = "拒绝使用APP"
        view.find<TextView>(R.id.tv_exit).singleClick {
            centerDialog.dismiss()
        }
        view.find<TextView>(R.id.tv_confirm).singleClick {
            centerDialog.dismiss()
//            initView()
        }
    }

    private fun getStrBuilder(): SpannableStringBuilder {
        val boldText = "感谢您信任并使用洞建APP！我们依据最新的法律政策要求更新了用户协议和隐私政策，特向您推送本提示。\n"
        val str =
            "1. 您在使用我们的产品和服务时，将会提供与具体功能相关的个人信息（可能涉及账号，位置，交易等信息）；\n" +
                    "2. 您可以对上述信息进行访问，更正，删除以及撤销同意等；\n" +
                    "3. 未经您的再次同意，我们不会将上述信息用于您未授权的其他用途和目的；\n" +
                    "4. 您可以查看完整版以及"
        val str1 = "《用户协议》"
        val str2 = ","
        val str3 = "《隐私政策》"
        val str4 = "以及"
        val str5 = "《信息采集和第三方SDK说明》"
        val str6 =
            "以便了解我们收集，使用及存储信息的情况以及我们对信息保护的措施。如果您同意请点击下列按钮以接受我们的服务；我们将尽全力保护您的个人信息安全及合法权益，再次感谢您的信任！"

        val builder = SimpleSpStringBuilder(requireActivity()).create()
            .setText(
                text = boldText,
                styleSpan = StyleSpan(Typeface.BOLD),
                textColorStr = "#222222"
            )
            .setText(text = str, textColorStr = "#222222")
            .setText(
                text = str1,
                textColorStr = "#1F47FF",
                clickable = {
                    startActivity<WebViewActivity>(
                        "url" to "http://82.156.54.119/xy/yhfwxy.html"
                    )
                    Toasty.success("点击事件")
                }
            )
            .setText(text = str2, textColorStr = "#222222")
            .setText(
                text = str3,
                textColorStr = "#1F47FF",
                clickable = {
                    startActivity<WebViewActivity>(
                        "url" to "https://zhijiang-dev.oss-cn-hangzhou.aliyuncs.com/d9b8518cf8391278cf80b1296cb6659b.html"
                    )
                    Toasty.success("点击事件")
                })
            .setText(text = str4, textColorStr = "#222222")
            .setText(
                text = str5,
                textColorStr = "#1F47FF",
                clickable = {
                    startActivity<WebViewActivity>(
                        "url" to "http://h5test.juranzhijiang.com.cn/machineCheck"
                    )
                    Toasty.success("点击事件")
                }
            )
            .setText(text = str6, textColorStr = "#222222")
            .builder()

        return builder
    }

    override fun setListener() {
        mViewBinding.apply {
            tv01.singleClick {
                Toasty.success(R.string.Describe)
                share()
                for (i in 0..10 step 3) {
                    Timber.e("数字==>${i}")
                    Timber.e("setListener: $i")
                }
            }
            tv02.singleClick {
                startActivity<RecyclerViewDraggableActivity>()
            }
            tv03.singleClick {
                startActivity<TabLayoutViewPagerDeleteActivity>()
            }
            val str = "张少鑫·闾丘渣"
            draggBtn.singleClick {
                generate(str)
                mViewModel.mLiveData.value = random.nextInt(0, 10).toString()
                Timber.e("HomeFragment_143行_2022/7/21_17:58：${getRandomAddress()}")
                Timber.e("HomeFragment_144行_2022/7/21_17:58：${getRandomIdCard()}")
                Timber.e("HomeFragment_145行_2022/7/21_17:58：${getRandomPhone()}")
                Timber.e("HomeFragment_146行_2022/7/21_17:58：${getRandomName()}")

            }
        }
    }


    @SuppressLint("StringFormatInvalid")
    private fun share() {
        val intent = ShareCompat.IntentBuilder(requireActivity())
            .setType("text/plain")
            .setChooserTitle(getString(R.string.DescribeWebView))

            //            .setText(getString(R.string.DescribeRichText))
            .intent

        startActivity(Intent.createChooser(intent, ""))
    }

    override fun createObserver() {
        mViewModel.mLiveData.observe(this) {

            Timber.e("HomeFragment_166行_2022/7/27_16:36：${it}")
            Toasty.normal(it)
        }
    }

}