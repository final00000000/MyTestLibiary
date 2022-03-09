package com.zhang.mydemo.kotlin.ui.activity

import android.os.CountDownTimer
import androidx.core.view.isVisible
import com.elvishew.xlog.XLog
import com.example.baselibiary.base.BaseActivity
import com.zhang.mydemo.databinding.ActivitySayWhatBinding
import com.zhang.mydemo.kotlin.ui.adapter.SayWhatAdapter1
import com.zhang.mydemo.kotlin.ui.adapter.SayWhatAdapter2
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_say_what.*
import kotlinx.android.synthetic.main.layout_title.*
import kotlin.random.Random

class SayWhatActivity : BaseActivity<ActivitySayWhatBinding>() {

    lateinit var adapter1: SayWhatAdapter1
    lateinit var adapter2: SayWhatAdapter2

    var foodList1 = mutableListOf<String>()
    var foodList2 = mutableListOf<String>()

    private var counter1: CountDownTimer? = null
    private var counter2: CountDownTimer? = null

    @Volatile
    var mPosition1 = 0

    @Volatile
    var mPosition2 = 0

    override fun initView() {
        tvPageTitle.text = ""
    }

    override fun initData() {
        addData()
        adapter1 = SayWhatAdapter1(mutableListOf())
        rvFood.adapter = adapter1
        adapter1.setNewInstance(foodList1)

        adapter2 = SayWhatAdapter2(mutableListOf())
        rvFood1.adapter = adapter2
        adapter2.setNewInstance(foodList2)

        start.singleClick {
            counter1?.start()
            counter2?.start()
            start.isEnabled = false
        }

        val rand = Random.nextLong(4000, 6000)
        counter1 = object : CountDownTimer(rand, 150) {
            override fun onTick(p0: Long) {
                if (mPosition1 == 0) {
                    adapter1.notifyItemChanged(0, true)
                    adapter1.notifyItemChanged(mPosition1 - 1, false)
                } else {
                    adapter1.notifyItemChanged(mPosition1, true)
                    adapter1.notifyItemChanged(mPosition1 - 1, false)
                }
                if (mPosition1 == foodList1.size) {
                    mPosition1 = 0
                } else {
                    mPosition1++
                }
            }

            override fun onFinish() {
                start.isEnabled = true
                XLog.e("SayWhatActivity_51行_2022/3/1_17:37：${mPosition1} $mPosition2")
                val plate1 = adapter1.data[mPosition1 - 1]
                tv.isVisible = true
                tv.text = "主食:${plate1}"
            }
        }
        counter2 = object : CountDownTimer(rand, 150) {
            override fun onTick(p0: Long) {
                if (mPosition2 == 0) {
                    adapter2.notifyItemChanged(0, true)
                    adapter2.notifyItemChanged(mPosition2 - 1, false)
                } else {
                    adapter2.notifyItemChanged(mPosition2, true)
                    adapter2.notifyItemChanged(mPosition2 - 1, false)
                }
                if (mPosition2 == foodList2.size) {
                    mPosition2 = 0
                } else {
                    mPosition2++
                }
            }

            override fun onFinish() {
                start.isEnabled = true
                XLog.e("SayWhatActivity_51行_2022/3/1_17:37：${mPosition1} $mPosition2")
                val plate2 = adapter2.data[mPosition2 - 1]
                tv.isVisible = true
                tv.text = "菜系:${plate2}"
            }
        }
    }

    fun addData() {
        foodList1.add("拌面")
        foodList1.add("汤面")
        foodList1.add("炒饼")
        foodList1.add("米饭")
        foodList1.add("馒头")
        foodList1.add("玉米")
        foodList1.add("烙饼")
        foodList1.add("花卷")
        foodList1.add("油条")
        foodList1.add("方便面")
        foodList1.add("炒饭")
        foodList1.add("凉皮")

        foodList2.add("锅包肉")
        foodList2.add("酸菜鱼")
        foodList2.add("火锅")
        foodList2.add("鱼香肉丝")
        foodList2.add("东北拉皮")
        foodList2.add("宫保鸡丁")
        foodList2.add("葱爆羊肉")
        foodList2.add("番茄牛腩")
        foodList2.add("肉末茄子")
        foodList2.add("农家小炒")
        foodList2.add("红烧排骨")
    }

    override fun setListener() {
    }
}