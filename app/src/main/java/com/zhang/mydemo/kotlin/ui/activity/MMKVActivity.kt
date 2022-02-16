package com.zhang.mydemo.kotlin.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tencent.mmkv.MMKV
import com.zhang.mydemo.R
import com.zhang.mydemo.base.BaseActivity
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_mmkvactivity.*
import kotlinx.android.synthetic.main.layout_title.*

class MMKVActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_mmkvactivity


    override fun initView() {
        tvPageTitle.text = "MMKV"
    }

    override fun initData() {
    }

    override fun setListener() {
        tv_add.singleClick {
            defaultMMKV.encode("1", "1")
            tv_show.text = "添加成功"
        }
        tv_get.singleClick {
            tv_show.text = " 查询成功  ${select()}"
        }
        tv_remove.singleClick {
            tv_show.text = "移除成功"
            if (select().contains("1")) defaultMMKV.removeValueForKey("1")
        }
    }

    fun select(): String {
        if (defaultMMKV.containsKey("1"))
            return defaultMMKV.decodeString("1")!!
        else return "已经被移除"
    }
}