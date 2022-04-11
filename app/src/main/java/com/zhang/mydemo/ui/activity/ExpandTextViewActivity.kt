package com.zhang.mydemo.ui.activity

import android.os.Bundle
import com.zhang.mydemo.base.activity.BaseActivity
import com.zhang.mydemo.databinding.ActivityExpandBinding
import com.zhang.mydemo.ui.adapter.ExpandAdapter
import kotlinx.android.synthetic.main.activity_expand.*
import kotlinx.android.synthetic.main.layout_title.*

class ExpandTextViewActivity : BaseActivity<ActivityExpandBinding>() {

    override fun initData() {
        rvExpand.adapter = ExpandAdapter(
            mutableListOf(
                "满减啦！现在充值越野极限会员包只要￥0.，即可获得官方越野车牌三对），2021年全国拉力赛总冠军亲笔签名三对），2021年全国拉力赛总冠军亲笔签名三对），2021年全国拉力赛总冠军亲笔签名三对），2021年全国拉力赛总冠军亲笔签名贴纸礼盒（含：镭射贴纸与普通贴纸各三对），2021年全国拉力赛总冠军亲笔签名旗…",
                "们再具低还省来他标经传些调原农例具不者满南按外来引务感具气圆将受文起土任果维王满当任九住无团今义又热影。们再具低还省来他标经传些调原农例具不者满南按外来引务感具气圆将受文起土任果维王满当任九住无团今义又热影。们再具低还省来他标经传些调原农例具不者满南按外来引务感具气圆将受文起土任果维王满当任九住无团今义又热影。们再具低还省来他标经传些调原农例具不者满南按外来引务感具气圆将受文起土任果维王满当任九住无团今义又热影。们再具低还省来他标经传些调原农例具不者满南按外来引务感具气圆将受文起土任果维王满当任九住无团今义又热影。们再具低还省来他标经传些调原农例具不者满南按外来引务感具气圆将受文起土任果维王满当任九住无团今义又热影。"
            )
        )
    }

    override fun setListener() {
    }

    override fun initView(savedInstanceState: Bundle?) {
        tvPageTitle.text = "收缩和展开"
    }
}