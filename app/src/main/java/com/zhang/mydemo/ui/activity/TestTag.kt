package com.zhang.mydemo.ui.activity

import android.widget.Toast
import com.zhang.mydemo.base.activity.BaseActivity
import com.zhang.mydemo.databinding.TextTagBinding
import com.zhang.mydemo.ui.widget.TagTextUtil
import com.zhang.mydemo.ui.widget.tag.LinkClickable
import com.zhang.mydemo.ui.widget.tag.TimeLineUtility
import kotlinx.android.synthetic.main.layout_title.*
import kotlinx.android.synthetic.main.text_tag.*


/**
 * @Author : zhang
 * @Create Time : 2022/1/23
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
class TestTag : BaseActivity<TextTagBinding>() {

    private val contentString = "@王安石 古人之观于天地、^#65&&山川#^、草木、虫鱼、鸟兽，往往有得，以其求思之深而无不在也。" +
            "夫夷以近，则游者众；险以远，则至者少。而世之奇伟、瑰怪，^$1&&非常之观$^，常在于险远，而人之所罕至焉，故非有志者不能至也。有志矣，不随以止也，然力不足者，^#2&&亦不能至也。有志与力，而又不随以怠，至于幽暗昏惑而无物以相之，亦不能至也。#^" +
            "然力足以至焉，于人为可讥，而在己为有悔；尽吾志也而不能至者，可以无悔矣，其孰能讥之乎？^!74&&此余之所得也。!^ --- ^@5232&&王安石@^"

    private val content = "#我的越野世界# #这是个标签#"

    override fun initView() {
        tvPageTitle.text = "纯文本标签"
    }

    override fun initData() {
        tv_tag.text = TagTextUtil().getTagContent(
            contentString,
            this@TestTag,
            tv_tag,
            object : TagTextUtil.ClickListener {
                override fun click(section: TagTextUtil.Section?) {
                    Toast.makeText(
                        this@TestTag,
                        section!!.name + "   index ==  " + section.index,
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
        tv_tag1.text = TimeLineUtility.convertNormalStringToSpannableString(
            content,
            TimeLineUtility.TimeLineStatus.FEED
        )
        tv_tag1.setOnTouchListener(LinkClickable())
    }

    override fun setListener() {
    }
}