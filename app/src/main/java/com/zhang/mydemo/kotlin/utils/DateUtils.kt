package com.zhang.mydemo.kotlin.utils

import java.lang.Exception
import java.text.ParseException
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Author : zhang
 * @Create Time : 2021/11/15 12:17
 * @Class Describe : 描述
 * @Project Name : KotlinDemo
 */
object DateUtil {

    /**
     * 获取当前时间戳
     */
    fun getCurrentMillis(): Long {
        return System.currentTimeMillis()
    }

    /**
     * 获取当前时间 - Calendar方式
     * @return
     */
    fun getCurrentTimeYMDHMS(): String {
        //获取当前时间
        val c = Calendar.getInstance() //可以对每个时间域单独修改
        val year = c[Calendar.YEAR]
        val month = c[Calendar.MONTH]
        val date = c[Calendar.DATE]
        val hour = c[Calendar.HOUR_OF_DAY]
        val minute = c[Calendar.MINUTE]
        val second = c[Calendar.SECOND]
        return year.toString() + "-" + month + 1 + "-" + date + " " + hour + ":" + minute + ":" + second
    }

    /**
     * 获取当前时间 - Calendar方式
     * @return
     */
    fun getCurrentTimeYMD(): String {
        //获取当前时间
        val c = Calendar.getInstance() //可以对每个时间域单独修改
        val year = c[Calendar.YEAR]
        val month = c[Calendar.MONTH]
        val date = c[Calendar.DATE]
        val hour = c[Calendar.HOUR_OF_DAY]
        val minute = c[Calendar.MINUTE]
        val second = c[Calendar.SECOND]
        return year.toString() + "-" + month + 1 + "-" + date
    }

    /*
     * 将时间转换为时间戳
     */
    @Throws(ParseException::class)
    fun dateToStamp(s: String?): String {
        val res: String
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = simpleDateFormat.parse(s)
        val ts = date.time
        res = ts.toString()
        return res
    }

    /*
     * 将时间戳转换为时间
     */
    fun stampToDate(s: String): String {
        val res: String
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val lt: Long = s.toLong()
        val date = Date(lt)
        res = simpleDateFormat.format(date)
        return res
    }

    /**
     * 时间对象转字符串
     */
    fun dateToString(date: Date?): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        return formatter.format(date)
    }

    /**
     * 获取现在时间 - Date方式
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    fun getNowDate(): Date {
        val currentTime = Date()
        val formatter =
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val dateString = formatter.format(currentTime)
        val pos = ParsePosition(8)
        return formatter.parse(dateString, pos)
    }

    /**
     * 获取现在时间
     *
     * @return返回短时间格式 yyyy-MM-dd
     */
    fun getNowDateShort(): Date {
        val currentTime = Date()
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val dateString = formatter.format(currentTime)
        val pos = ParsePosition(8)
        return formatter.parse(dateString, pos)
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    fun getStringDate(): String {
        val currentTime = Date()
        val formatter =
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return formatter.format(currentTime)
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    fun getStringDateShort(): String {
        val currentTime = Date()
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        return formatter.format(currentTime)
    }

    /**
     * 获取时间 小时:分;秒 HH:mm:ss
     *
     * @return
     */
    fun getTimeShort(): String {
        val formatter = SimpleDateFormat("HH:mm:ss")
        val currentTime = Date()
        return formatter.format(currentTime)
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    fun strToDateLong(strDate: String?): Date {
        val formatter =
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val pos = ParsePosition(0)
        return formatter.parse(strDate, pos)
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param dateDate
     * @return
     */
    fun dateToStrLong(dateDate: Date?): String {
        val formatter =
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return formatter.format(dateDate)
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     *
     * @param dateDate
     * @return
     */
    fun dateToStr(dateDate: Date?): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        return formatter.format(dateDate)
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    fun strToDate(strDate: String?): Date {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val pos = ParsePosition(0)
        return formatter.parse(strDate, pos)
    }

    /**
     * 得到现在时间
     *
     * @return
     */
    fun getNow(): Date {
        return Date()
    }

    /**
     * 提取一个月中的最后一天
     *
     * @param day
     * @return
     */
    fun getLastDate(day: Long): Date {
        val date = Date()
        val date_3_hm = date.time - 3600000 * 34 * day
        return Date(date_3_hm)
    }

    /**
     * 得到现在时间
     *
     * @return 字符串 yyyyMMdd HHmmss
     */
    fun getStringToday(): String {
        val currentTime = Date()
        val formatter = SimpleDateFormat("yyyyMMdd HHmmss")
        return formatter.format(currentTime)
    }

    /**
     * 得到现在小时
     */
    fun getHour(): String {
        val currentTime = Date()
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val dateString = formatter.format(currentTime)
        val hour: String
        hour = dateString.substring(11, 13)
        return hour
    }

    /**
     * 得到现在分钟
     *
     * @return
     */
    fun getTime(): String {
        val currentTime = Date()
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val dateString = formatter.format(currentTime)
        val min: String
        min = dateString.substring(14, 16)
        return min
    }

    /**
     * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
     *
     * @param sformat yyyyMMddhhmmss
     * @return
     */
    fun getUserDate(sformat: String?): String {
        val currentTime = Date()
        val formatter = SimpleDateFormat(sformat)
        return formatter.format(currentTime)
    }

    /**
     * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟
     */
/*    fun getTwoHour(st1: String, st2: String): String {
        var kk: Array<String>? = null
        var jj: Array<String>? = null
        kk = st1.split(":").toTypedArray()
        jj = st2.split(":").toTypedArray()
        return if (kk[0].toInt() < jj[0].toInt()) "0" else {
            val y = kk[0].toDouble() + kk[1].toDouble() / 60
            val u = jj[0].toDouble() + jj[1].toDouble() / 60
            if (y - u > 0){
                y - u.toString() + ""
            }else "0"
        }
    }*/

    /**
     * 得到二个日期间的间隔天数
     */
    fun getTwoDay(sj1: String?, sj2: String?): String {
        val myFormatter = SimpleDateFormat("yyyy-MM-dd")
        var day: Long = 0
        day = try {
            val date = myFormatter.parse(sj1)
            val mydate = myFormatter.parse(sj2)
            (date.time - mydate.time) / (24 * 60 * 60 * 1000)
        } catch (e: Exception) {
            return ""
        }
        return day.toString() + ""
    }

    /**
     * 时间前推或后推分钟,其中JJ表示分钟.
     */
    fun getPreTime(sj1: String?, jj: String): String {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        var mydate1 = ""
        try {
            val date1 = format.parse(sj1)
            val Time = date1.time / 1000 + jj.toInt() * 60
            date1.time = Time * 1000
            mydate1 = format.format(date1)
        } catch (e: Exception) {
        }
        return mydate1
    }

    /**
     * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
     */
    fun getNextDay(nowdate: String?, delay: String): String {
        return try {
            val format = SimpleDateFormat("yyyy-MM-dd")
            var mdate = ""
            val d = strToDate(nowdate)
            val myTime = d.time / 1000 + delay.toInt() * 24 * 60 * 60
            d.time = myTime * 1000
            mdate = format.format(d)
            mdate
        } catch (e: Exception) {
            ""
        }
    }

    /**
     * 判断是否润年
     *
     * @param ddate
     * @return
     */
    fun isLeapYear(ddate: String?): Boolean {
        /**
         * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
         * 3.能被4整除同时能被100整除则不是闰年
         */
        val d = strToDate(ddate)
        val gc = Calendar.getInstance() as GregorianCalendar
        gc.time = d
        val year = gc[Calendar.YEAR]
        return if (year % 400 == 0) true else if (year % 4 == 0) {
            if (year % 100 == 0) false else true
        } else false
    }

    /**
     * 返回美国时间格式 26 Apr 2006
     *
     * @param str
     * @return
     */
    fun getEDate(str: String?): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val pos = ParsePosition(0)
        val strtodate = formatter.parse(str, pos)
        val j = strtodate.toString()
        val k = j.split(" ").toTypedArray()
        return k[2] + k[1].toUpperCase() + k[5].substring(2, 4)
    }

    /**
     * 获取一个月的最后一天
     *
     * @param dat
     * @return
     */
    fun getEndDateOfMonth(dat: String): String { // yyyy-MM-dd
        var str = dat.substring(0, 8)
        val month = dat.substring(5, 7)
        val mon = month.toInt()
        str += if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
            "31"
        } else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
            "30"
        } else {
            if (isLeapYear(dat)) {
                "29"
            } else {
                "28"
            }
        }
        return str
    }

    /**
     * 判断二个时间是否在同一个周
     *
     * @param date1
     * @param date2
     * @return
     */
    fun isSameWeekDates(date1: Date?, date2: Date?): Boolean {
        val cal1 = Calendar.getInstance()
        val cal2 = Calendar.getInstance()
        cal1.time = date1
        cal2.time = date2
        val subYear = cal1[Calendar.YEAR] - cal2[Calendar.YEAR]
        if (0 == subYear) {
            if (cal1[Calendar.WEEK_OF_YEAR] == cal2[Calendar.WEEK_OF_YEAR]) return true
        } else if (1 == subYear && 11 == cal2[Calendar.MONTH]) {
            // 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
            if (cal1[Calendar.WEEK_OF_YEAR] == cal2[Calendar.WEEK_OF_YEAR]) return true
        } else if (-1 == subYear && 11 == cal1[Calendar.MONTH]) {
            if (cal1[Calendar.WEEK_OF_YEAR] == cal2[Calendar.WEEK_OF_YEAR]) return true
        }
        return false
    }

    /**
     * 产生周序列,即得到当前时间所在的年度是第几周
     *
     * @return
     */
    fun getSeqWeek(): String {
        val c = Calendar.getInstance(Locale.CHINA)
        var week = Integer.toString(c[Calendar.WEEK_OF_YEAR])
        if (week.length == 1) week = "0$week"
        val year = Integer.toString(c[Calendar.YEAR])
        return year + week
    }

    /**
     * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
     *
     * @param sdate
     * @param num
     * @return
     */
    fun getWeek(sdate: String?, num: String): String {
        // 再转换为时间
        val dd = strToDate(sdate)
        val c = Calendar.getInstance()
        c.time = dd
        if (num == "1") // 返回星期一所在的日期
            c[Calendar.DAY_OF_WEEK] = Calendar.MONDAY else if (num == "2") // 返回星期二所在的日期
            c[Calendar.DAY_OF_WEEK] = Calendar.TUESDAY else if (num == "3") // 返回星期三所在的日期
            c[Calendar.DAY_OF_WEEK] = Calendar.WEDNESDAY else if (num == "4") // 返回星期四所在的日期
            c[Calendar.DAY_OF_WEEK] = Calendar.THURSDAY else if (num == "5") // 返回星期五所在的日期
            c[Calendar.DAY_OF_WEEK] = Calendar.FRIDAY else if (num == "6") // 返回星期六所在的日期
            c[Calendar.DAY_OF_WEEK] = Calendar.SATURDAY else if (num == "0") // 返回星期日所在的日期
            c[Calendar.DAY_OF_WEEK] = Calendar.SUNDAY
        return SimpleDateFormat("yyyy-MM-dd").format(c.time)
    }

    /**
     * 根据一个日期，返回是星期几的字符串
     *
     * @param sdate
     * @return
     */
    fun getWeek(sdate: String?): String {
        // 再转换为时间
        val date = strToDate(sdate)
        val c = Calendar.getInstance()
        c.time = date
        // int hour=c.get(Calendar.DAY_OF_WEEK);
        // hour中存的就是星期几了，其范围 1~7
        // 1=星期日 7=星期六，其他类推
        return SimpleDateFormat("EEEE").format(c.time)
    }

    fun getWeekStr(sdate: String?): String {
        var str = ""
        str = getWeek(sdate)
        if ("1" == str) {
            str = "星期日"
        } else if ("2" == str) {
            str = "星期一"
        } else if ("3" == str) {
            str = "星期二"
        } else if ("4" == str) {
            str = "星期三"
        } else if ("5" == str) {
            str = "星期四"
        } else if ("6" == str) {
            str = "星期五"
        } else if ("7" == str) {
            str = "星期六"
        }
        return str
    }

    /**
     * 两个时间之间的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    fun getDays(date1: String?, date2: String?): Long {
        if (date1 == null || date1 == "") return 0
        if (date2 == null || date2 == "") return 0
        // 转换为标准时间
        val myFormatter = SimpleDateFormat("yyyy-MM-dd")
        var date: Date? = null
        var mydate: Date? = null
        try {
            date = myFormatter.parse(date1)
            mydate = myFormatter.parse(date2)
        } catch (e: Exception) {
        }
        return (date!!.time - mydate!!.time) / (24 * 60 * 60 * 1000)
    }

    /**
     * 形成如下的日历 ， 根据传入的一个时间返回一个结构 星期日 星期一 星期二 星期三 星期四 星期五 星期六 下面是当月的各个时间
     * 此函数返回该日历第一行星期日所在的日期
     *
     * @param sdate
     * @return
     */
    fun getNowMonth(sdate: String): String {
        // 取该时间所在月的一号
        var sdate = sdate
        sdate = sdate.substring(0, 8) + "01"

        // 得到这个月的1号是星期几
        val date = strToDate(sdate)
        val c = Calendar.getInstance()
        c.time = date
        val u = c[Calendar.DAY_OF_WEEK]
        return getNextDay(sdate, (1 - u).toString() + "")
    }

    /**
     * 取得数据库主键 生成格式为yyyymmddhhmmss+k位随机数
     *
     * @param k 表示是取几位随机数，可以自己定
     */
    fun getNo(k: Int): String {
        return getUserDate("yyyyMMddhhmmss") + getRandom(k)
    }

    /**
     * 返回一个随机数
     *
     * @param i
     * @return
     */
    fun getRandom(i: Int): String {
        val jjj = Random()
        // int suiJiShu = jjj.nextInt(9);
        if (i == 0) return ""
        var jj = ""
        for (k in 0 until i) {
            jj = jj + jjj.nextInt(9)
        }
        return jj
    }
}