package com.zhang.mydemo.kotlin.ui.activity

import android.annotation.SuppressLint
import com.example.baselibiary.base.BaseActivity
import com.zhang.mydemo.databinding.ActivityGrammarBinding
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_grammar.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class GrammarActivity : BaseActivity<ActivityGrammarBinding>() {

    var mutableList = mutableListOf(1, 2, 3, 4, 5)

    override fun initView() {
    }

    override fun initData() {
    }

    @SuppressLint("SetTextI18n")
    override fun setListener() {
        ivPageBack.singleClick { killMyself() }
        tvPageTitle.text = "Kotlin语法糖"
        apply.singleClick {
            mutableList.apply {
                tv_show.text = "apply返回值是对象本身  $this \n 适用场景:\n " +
                        "1、适用于run函数的任何场景，一般用于初始化一个对象实例的时候，操作对象属性，并最终返回这个对象。\n" +
                        "2、动态inflate出一个XML的View的时候需要给View绑定数据也会用到.\n" +
                        "3、一般可用于多个扩展函数链式调用\n" +
                        "4、数据model多层级包裹判空处理的问题"
            }
        }
        let.singleClick {
            mutableList.let {
                tv_show.text =
                    "let返回当前对象(数据.let,it则是数据 \n 控件.let,it则是控件能进行赋值之类..等一系列操作) \n $it \n 适用场景:适用于处理不为null的操作场景  "
            }
        }
        also.singleClick {
            mutableList.also {
                tv_show.text = "和let相似 $it  适用场景:适用于let函数的任何场景，一般可用于多个扩展函数链式调用"
            }
        }
        with.singleClick {
            startActivity<WebViewActivity>(
                "url" to "https://blog.csdn.net/u013064109/article/details/78786646"
            )
            toast("请看百度解释")
        }

        run.singleClick {
            mutableList.run {
                tv_show.text = "和let相似  $this  适用场景:适用于let,with函数任何场景。"
            }
        }
        joinToString.singleClick {
            tv_show.text =
                "将集合转成String joinToString(里面填什么符号,就以什么符号分隔) ${mutableList.joinToString(",")}"
        }
        filter.singleClick {
            tv_show.text = "过滤符合条件的值  ${
                mutableList.filter {
                    it == 1
                }.joinToString()
            } \n mutableList.filter {it == 1}.joinToString()"
        }

        noFilter.singleClick {
            tv_show.text = "过滤不符合条件的值   ${
                mutableList.filterNot {
                    it == 1
                }
            }   \n mutableList.filterNot {it == 1}.joinToString()"
        }
        tv_collections.singleClick {
            startActivity<WebViewActivity>(
                "url" to "https://blog.csdn.net/hjkcghjmguyy/article/details/73879795"
            )
        }
        tv_operator.singleClick {
            startActivity<WebViewActivity>(
                "url" to "https://blog.csdn.net/android_app_2012/article/details/83338557"
            )
        }
        map.singleClick {
            tv_show.text = "将map变成集合传给适配器  mutableList.map{} ${mutableList.map { it }}"
        }
        sumof.singleClick {
            tv_show.text =
                "mutableListOf(1,2, 3, 4, 5) 集合进行求和  mutableList.sumOf { it }  \n  和: ${mutableList.sumOf { it }}"
        }
        average.singleClick {
            tv_show.text =
                "mutableListOf(1,2, 3, 4, 5) 集合进行求平均值 mutableList.windowed(mutableList.size).map(List<Int>::average) \n 平均值: ${
                    mutableList.windowed(mutableList.size).map(List<Int>::average)
                }"
        }
    }
}