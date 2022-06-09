package com.zhang.mydemo.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import com.zhang.mydemo.base.activity.BaseActivity
import com.zhang.mydemo.databinding.ActivityDateUtilsBinding
import com.zhang.utilslibiary.utils.DateUtil.getCurrentMillis
import com.zhang.utilslibiary.utils.DateUtil.getCurrentTimeYMD
import com.zhang.utilslibiary.utils.DateUtil.getCurrentTimeYMDHMS
import com.zhang.utilslibiary.utils.DateUtil.getStringDate
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_date_utils.*
import kotlinx.android.synthetic.main.layout_title.*

@SuppressLint("SetTextI18n")
class DateUtilsActivity : BaseActivity<ActivityDateUtilsBinding>() {

    override fun initData() {
        tvPageTitle.text = "时间工具类"
    }

    override fun setListener() {
        mViewBinding.apply {
            tv1.singleClick {
                tv1.text = "当前时间戳：${getCurrentMillis()}"
            }
            tv2.singleClick {
                tv2.text = "当前时间格式为(yy-MM-dd hh-mm-ss)：${getCurrentTimeYMDHMS()}"
            }
            tv3.singleClick {
                tv3.text = "当前时间格式为(yy-MM-dd)：${getCurrentTimeYMD()}"
            }
            tv4.singleClick {
                tv4.text = "获取现在时间：${getStringDate()}"
            }
        }
    }

    override fun initView(savedInstanceState: Bundle?) {

    }
}