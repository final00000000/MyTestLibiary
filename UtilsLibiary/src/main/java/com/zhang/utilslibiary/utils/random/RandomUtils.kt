package com.zhang.utilslibiary.utils.random

import org.apache.commons.lang3.RandomUtils
import java.util.*
import kotlin.random.Random

val random = Random

// 存放一些随机公共类
// 函数在DataExtension 里面
object RandomUtils {

    // 地址
    fun genProvinceAndCity(): String {
        return ChineseAreaList.provinceCityList[random.nextInt(ChineseAreaList.provinceCityList.size)]
    }

    // 地址
    fun genRandomLengthChineseChars(start: Int, end: Int): String {
        var str = ""
        val length: Int = random.nextInt(end + 1)
        if (length < start) {
            str = genRandomLengthChineseChars(start, end)
        } else {
            for (i in 0 until length) {
                str += ChineseChars.genOneChineseChars()
            }
        }
        return str
    }

    //===========身份证==============

    fun randomDate(): Date {
        val calendar = Calendar.getInstance()
        calendar[1970, 1] = 1
        val earlierDate = calendar.time.time
        calendar[2000, 1] = 1
        val laterDate = calendar.time.time
        val rand = Random.nextLong(earlierDate, laterDate)
        return Date(rand)
    }

    fun getVerifyCode(cardId: String): String {
        val ValCodeArr = arrayOf(
            "1", "0", "X", "9", "8", "7", "6", "5", "4",
            "3", "2"
        )
        val Wi = arrayOf(
            "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
            "9", "10", "5", "8", "4", "2"
        )
        var tmp = 0
        for (i in Wi.indices) {
            tmp += cardId[i].toString().toInt() * Wi[i].toInt()
        }
        val modValue = tmp % 11
        return ValCodeArr[modValue]
    }


    //===========手机号==============

    private val MOBILE_PREFIX = intArrayOf(
        133, 153, 177, 180,
        181, 189, 134, 135, 136, 137, 138, 139, 150, 151, 152, 157, 158, 159,
        178, 182, 183, 184, 187, 188, 130, 131, 132, 155, 156, 176, 185, 186,
        145, 147, 170
    )

    fun genMobilePre(): String {
        return "" + MOBILE_PREFIX[RandomUtils.nextInt(0, MOBILE_PREFIX.size)]
    }

    //===========姓名==============
    val FIRST_NAMES = arrayOf(
        "李", "王", "张",
        "刘", "陈", "杨", "黄", "赵", "周", "吴", "徐", "孙", "朱", "马", "胡", "郭", "林",
        "何", "高", "梁", "郑", "罗", "宋", "谢", "唐", "韩", "曹", "许", "邓", "萧", "冯",
        "曾", "程", "蔡", "彭", "潘", "袁", "於", "董", "余", "苏", "叶", "吕", "魏", "蒋",
        "田", "杜", "丁", "沈", "姜", "范", "江", "傅", "钟", "卢", "汪", "戴", "崔", "任",
        "陆", "廖", "姚", "方", "金", "邱", "夏", "谭", "韦", "贾", "邹", "石", "熊", "孟",
        "秦", "阎", "薛", "侯", "雷", "白", "龙", "段", "郝", "孔", "邵", "史", "毛", "常",
        "万", "顾", "赖", "武", "康", "贺", "严", "尹", "钱", "施", "牛", "洪", "龚", "东方",
        "夏侯", "诸葛", "尉迟", "皇甫", "宇文", "鲜于", "西门", "司马", "独孤", "公孙", "慕容", "轩辕",
        "左丘", "欧阳", "皇甫", "上官", "闾丘", "令狐"
    )

    fun genFirstName(): String {
        return FIRST_NAMES[random.nextInt(FIRST_NAMES.size)]
    }
}