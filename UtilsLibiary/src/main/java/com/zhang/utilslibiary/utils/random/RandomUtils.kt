package com.zhang.utilslibiary.utils.random

import java.util.*

val random = Random()

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

    //=========================

}