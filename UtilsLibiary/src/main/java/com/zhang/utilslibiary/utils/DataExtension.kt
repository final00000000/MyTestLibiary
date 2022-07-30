package com.zhang.utilslibiary.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.text.TextUtils
import com.zhang.utilslibiary.utils.random.RandomUtils
import com.zhang.utilslibiary.utils.random.RandomUtils.genFirstName
import com.zhang.utilslibiary.utils.random.RandomUtils.genMobilePre
import com.zhang.utilslibiary.utils.random.RandomUtils.genRandomLengthChineseChars
import com.zhang.utilslibiary.utils.random.RandomUtils.getVerifyCode
import com.zhang.utilslibiary.utils.random.RandomUtils.randomDate
import com.zhang.utilslibiary.utils.random.random
import org.apache.commons.lang3.StringUtils
import timber.log.Timber
import java.io.File
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern
import kotlin.math.roundToInt

/**
 * 数据扩展类
 **/

/**
 *判断手机号格式 是否中国手机号码
 * @param phone 待校验的手机号码
 * @return `true` yes, `false` no
 */
fun isPhone(phone: String?): Boolean {
    return match(phoneFormat(), phone)
}

/**
 * 通用匹配函数
 * @param regex 正则表达式
 * @param input 待校验的字符串
 * @return `true` yes, `false` no
 */
fun match(regex: String?, input: String?): Boolean {
    if (input!!.isNotEmpty()) {
        try {
            return Pattern.matches(regex, input)
        } catch (e: Exception) {
            Timber.e("DataExtension match== >异常  ")
        }
    }
    return false
}

// 手机号格式
fun phoneFormat(): String {
    val builder = StringBuilder()
    // 中国手机: 130 131 132 133 134 135 136 137 138 139
    // 145 146 147 148 149
    // 150 151 152 153 155 156 157 158 159
    // 162 165 166 167 167
    // 170 171 171 172 173 174 175 176 177 178
    // 180 181 182 183 184 185 186 187 188 189
    // 190 191 192 193 195 196 198 199
    builder.append("^13[0,1,2,3,4,5,6,7,8,9]{1}\\d{8}$") // 13 开头
        .append("|")
        .append("^14[5,6,7,8,9]{1}\\d{8}$") // 14 开头
        .append("|")
        .append("^15[0,1,2,3,5,6,7,8,9]{1}\\d{8}$") // 15 开头
        .append("|")
        .append("^16[2,5,6,7,7]{1}\\d{8}$") // 16 开头
        .append("|")
        .append("^17[0,1,1,2,3,4,5,6,7,8]{1}\\d{8}$") // 17 开头
        .append("|")
        .append("^18[0,1,2,3,4,5,6,7,8,9]{1}\\d{8}$") // 18 开头
        .append("|")
        .append("^19[0,1,2,3,5,6,8,9]{1}\\d{8}$") // 19 开头
    return builder.toString()
}

/**
 * 随机获取地址
 */
fun getRandomAddress(): String {
    val result = StringBuilder(RandomUtils.genProvinceAndCity())
    result.append(RandomUtils.genRandomLengthChineseChars(2, 3) + "路")
    result.append((Random().nextInt(1) + 8000).toString() + "号")
    result.append(RandomUtils.genRandomLengthChineseChars(2, 3) + "小区")
    result.append((Random().nextInt(1) + 20).toString() + "单元")
    result.append((Random().nextInt(101) + 2500).toString() + "室")
    return result.toString()
}

/**
 * 随机获取身份证
 */
fun getRandomIdCard(): String {
    val code = areaCode
    val areaCode = code.keys.toTypedArray()[random
        .nextInt(0, code.size)] + StringUtils.leftPad(
        (random.nextInt(0, 9998) + 1).toString() + "", 4,
        "0"
    )
    val birthday = SimpleDateFormat("yyyyMMdd").format(randomDate())
    val randomCode =
        (1000 + org.apache.commons.lang3.RandomUtils.nextInt(0, 999)).toString().substring(1)
    val pre = areaCode + birthday + randomCode
    val verifyCode = getVerifyCode(pre)
    return pre + verifyCode
}

/**
 *  随机获取手机号
 */
fun getRandomPhone(): String =
    genMobilePre() + StringUtils.leftPad("" + random.nextInt(0, 99999999 + 1), 8, "0")

/**
 * 随机获取姓名
 */
fun getRandomName(): String =
    genFirstName() + genRandomLengthChineseChars(1, 2)






/**
 * 时间转换
 */
fun String?.compareDateTime(): String {
    if (this.isNullOrEmpty()) {
        return ""
    }
    try {
        val time = this.toLong()
        return when (System.currentTimeMillis() - time) {
            in 0..60 * 1000 -> "刚刚"
            in 59 * 60 * 1000..58 * 60 * 1000 -> "59分钟前"
            in 58 * 60 * 1000..57 * 60 * 1000 -> "58钟前"
            in 57 * 60 * 1000..56 * 60 * 1000 -> "57分钟前"
            in 56 * 60 * 1000..55 * 60 * 1000 -> "56分钟前"
            in 55 * 60 * 1000..54 * 60 * 1000 -> "55分钟前"
            in 54 * 60 * 1000..53 * 60 * 1000 -> "54分钟前"
            in 53 * 60 * 1000..52 * 60 * 1000 -> "53分钟前"
            in 52 * 60 * 1000..51 * 60 * 1000 -> "52分钟前"
            in 51 * 60 * 1000..50 * 60 * 1000 -> "51分钟前"
            in 50 * 60 * 1000..49 * 60 * 1000 -> "50分钟前"
            in 49 * 60 * 1000..48 * 60 * 1000 -> "49分钟前"
            in 48 * 60 * 1000..47 * 60 * 1000 -> "48分钟前"
            in 47 * 60 * 1000..46 * 60 * 1000 -> "47分钟前"
            in 46 * 60 * 1000..45 * 60 * 1000 -> "46分钟前"
            in 45 * 60 * 1000..44 * 60 * 1000 -> "45分钟前"
            in 44 * 60 * 1000..43 * 60 * 1000 -> "44分钟前"
            in 43 * 60 * 1000..42 * 60 * 1000 -> "43分钟前"
            in 42 * 60 * 1000..41 * 60 * 1000 -> "42分钟前"
            in 41 * 60 * 1000..40 * 60 * 1000 -> "41分钟前"
            in 40 * 60 * 1000..39 * 60 * 1000 -> "40分钟前"
            in 39 * 60 * 1000..38 * 60 * 1000 -> "39分钟前"
            in 38 * 60 * 1000..37 * 60 * 1000 -> "38分钟前"
            in 37 * 60 * 1000..36 * 60 * 1000 -> "37分钟前"
            in 36 * 60 * 1000..35 * 60 * 1000 -> "36分钟前"
            in 35 * 60 * 1000..34 * 60 * 1000 -> "35分钟前"
            in 34 * 60 * 1000..33 * 60 * 1000 -> "34分钟前"
            in 33 * 60 * 1000..32 * 60 * 1000 -> "33分钟前"
            in 32 * 60 * 1000..31 * 60 * 1000 -> "32分钟前"
            in 31 * 60 * 1000..30 * 60 * 1000 -> "31分钟前"
            in 30 * 60 * 1000..29 * 60 * 1000 -> "30分钟前"
            in 29 * 60 * 1000..28 * 60 * 1000 -> "29分钟前"
            in 28 * 60 * 1000..27 * 60 * 1000 -> "28分钟前"
            in 27 * 60 * 1000..26 * 60 * 1000 -> "27分钟前"
            in 26 * 60 * 1000..25 * 60 * 1000 -> "26分钟前"
            in 25 * 60 * 1000..24 * 60 * 1000 -> "25分钟前"
            in 24 * 60 * 1000..23 * 60 * 1000 -> "24分钟前"
            in 23 * 60 * 1000..22 * 60 * 1000 -> "23分钟前"
            in 22 * 60 * 1000..21 * 60 * 1000 -> "22分钟前"
            in 21 * 60 * 1000..20 * 60 * 1000 -> "21分钟前"
            in 20 * 60 * 1000..19 * 60 * 1000 -> "20分钟前"
            in 19 * 60 * 1000..18 * 60 * 1000 -> "19分钟前"
            in 18 * 60 * 1000..17 * 60 * 1000 -> "18分钟前"
            in 17 * 60 * 1000..16 * 60 * 1000 -> "17分钟前"
            in 16 * 60 * 1000..15 * 60 * 1000 -> "16分钟前"
            in 15 * 60 * 1000..14 * 60 * 1000 -> "15分钟前"
            in 14 * 60 * 1000..13 * 60 * 1000 -> "14分钟前"
            in 13 * 60 * 1000..12 * 60 * 1000 -> "13分钟前"
            in 12 * 60 * 1000..11 * 60 * 1000 -> "12分钟前"
            in 11 * 60 * 1000..10 * 60 * 1000 -> "11分钟前"
            in 10 * 60 * 1000..9 * 60 * 1000 -> "10分钟前"
            in 9 * 60 * 1000..8 * 60 * 1000 -> "9分钟前"
            in 8 * 60 * 1000..7 * 60 * 1000 -> "8分钟前"
            in 7 * 60 * 1000..6 * 60 * 1000 -> "7分钟前"
            in 6 * 60 * 1000..5 * 60 * 1000 -> "6分钟前"
            in 5 * 60 * 1000..4 * 60 * 1000 -> "5分钟前"
            in 4 * 60 * 1000..3 * 60 * 1000 -> "4分钟前"
            in 3 * 60 * 1000..2 * 60 * 1000 -> "3分钟前"
            in 2 * 60 * 1000..1 * 60 * 1000 -> "2分钟前"
            in 1 * 60 * 1000..59 * 1000 -> "1分钟前"
            in 23 * 60 * 60 * 1000..22 * 60 * 60 * 1000 -> "23小时前"
            in 22 * 60 * 60 * 1000..21 * 60 * 60 * 1000 -> "22小时前"
            in 21 * 60 * 60 * 1000..20 * 60 * 60 * 1000 -> "21小时前"
            in 20 * 60 * 60 * 1000..19 * 60 * 60 * 1000 -> "20小时前"
            in 19 * 60 * 60 * 1000..18 * 60 * 60 * 1000 -> "19小时前"
            in 18 * 60 * 60 * 1000..17 * 60 * 60 * 1000 -> "18小时前"
            in 17 * 60 * 60 * 1000..16 * 60 * 60 * 1000 -> "17小时前"
            in 16 * 60 * 60 * 1000..15 * 60 * 60 * 1000 -> "16小时前"
            in 15 * 60 * 60 * 1000..14 * 60 * 60 * 1000 -> "15小时前"
            in 14 * 60 * 60 * 1000..13 * 60 * 60 * 1000 -> "14小时前"
            in 13 * 60 * 60 * 1000..12 * 60 * 60 * 1000 -> "13小时前"
            in 12 * 60 * 60 * 1000..11 * 60 * 60 * 1000 -> "12小时前"
            in 11 * 60 * 60 * 1000..10 * 60 * 60 * 1000 -> "11小时前"
            in 10 * 60 * 60 * 1000..9 * 60 * 60 * 1000 -> "10小时前"
            in 9 * 60 * 60 * 1000..8 * 60 * 60 * 1000 -> "9小时前"
            in 8 * 60 * 60 * 1000..7 * 60 * 60 * 1000 -> "8小时前"
            in 7 * 60 * 60 * 1000..6 * 60 * 60 * 1000 -> "7小时前"
            in 6 * 60 * 60 * 1000..5 * 60 * 60 * 1000 -> "6小时前"
            in 5 * 60 * 60 * 1000..4 * 60 * 60 * 1000 -> "5小时前"
            in 4 * 60 * 60 * 1000..3 * 60 * 60 * 1000 -> "4小时前"
            in 3 * 60 * 60 * 1000..2 * 60 * 60 * 1000 -> "3小时前"
            in 2 * 60 * 60 * 1000..1 * 60 * 60 * 1000 -> "2小时前"
            in 10 * 24 * 60 * 60 * 1000..9 * 24 * 60 * 60 * 1000 -> "10天前"
            in 9 * 24 * 60 * 60 * 1000..8 * 24 * 60 * 60 * 1000 -> "9天前"
            in 8 * 24 * 60 * 60 * 1000..7 * 24 * 60 * 60 * 1000 -> "8天前"
            in 7 * 24 * 60 * 60 * 1000..6 * 24 * 60 * 60 * 1000 -> "7天前"
            in 6 * 24 * 60 * 60 * 1000..5 * 24 * 60 * 60 * 1000 -> "6天前"
            in 5 * 24 * 60 * 60 * 1000..4 * 24 * 60 * 60 * 1000 -> "5天前"
            in 4 * 24 * 60 * 60 * 1000..3 * 24 * 60 * 60 * 1000 -> "4天前"
            in 3 * 24 * 60 * 60 * 1000..2 * 24 * 60 * 60 * 1000 -> "3天前"
            in 2 * 24 * 60 * 60 * 1000..1 * 24 * 60 * 60 * 1000 -> "2天前"
            in 1 * 24 * 60 * 60 * 1000..24 * 60 * 60 * 1000 -> "1天前"
            else -> {
                val formatter = SimpleDateFormat("yyyy-MM-dd")
                return formatter.format(Date())
            }
        }
    } catch (e: NumberFormatException) {
        return ""
    }


}

/**
 * String  -> Float
 */
fun String?.strToFloat2(): BigDecimal {
    return BigDecimal(this ?: "0").setScale(2, BigDecimal.ROUND_DOWN)
}


/**
 * 获取非空值的内容
 */
fun String?.toStringNoNull(default: String = ""): String {
    return if ("null" == this) {
        default
    } else {
        this ?: default
    }
}


/**
 *  String -> 内容 为 null 或者 “null”
 */
fun String?.strToString(): String {
    return if (this == null || this == "null") {
        ""
    } else {
        this
    }
}

fun Long.timeParse(): String {
    var time = ""
    if (this > 1000) {
        time = timeParseMinute()
    } else {
        val minute = this / 60000
        val seconds = this % 60000
        val second = (seconds.toFloat() / 1000).roundToInt().toLong()
        if (minute < 10) {
            time += "0"
        }
        time += "$minute:"
        if (second < 10) {
            time += "0"
        }
        time += second
    }
    return time
}

@SuppressLint("SimpleDateFormat")
private fun Long.timeParseMinute(): String {
    return try {
        SimpleDateFormat("mm:ss").format(this)
    } catch (e: Exception) {
        e.printStackTrace()
        "0:00"
    }
}

/**
 * 获取文件的ContentType
 */
fun File.getContentType(): String {
    var contentType: String? = null
    mapOf(
        Pair(".load", "text/html"),
        Pair(".123", "application/vnd.lotus-1-2-3"),
        Pair(".3ds", "image/x-3ds"),
        Pair(".3g2", "video/3gpp"),
        Pair(".3ga", "video/3gpp"),
        Pair(".3gp", "video/3gpp"),
        Pair(".3gpp", "video/3gpp"),
        Pair(".602", "application/x-t602"),
        Pair(".669", "audio/x-mod"),
        Pair(".7z", "application/x-7z-compressed"),
        Pair(".a", "application/x-archive"),
        Pair(".aac", "audio/mp4"),
        Pair(".abw", "application/x-abiword"),
        Pair(".abw.crashed", "application/x-abiword"),
        Pair(".abw.gz", "application/x-abiword"),
        Pair(".ac3", "audio/ac3"),
        Pair(".ace", "application/x-ace"),
        Pair(".adb", "text/x-adasrc"),
        Pair(".ads", "text/x-adasrc"),
        Pair(".afm", "application/x-font-afm"),
        Pair(".ag", "image/x-applix-graphics"),
        Pair(".ai", "application/illustrator"),
        Pair(".aif", "audio/x-aiff"),
        Pair(".aifc", "audio/x-aiff"),
        Pair(".aiff", "audio/x-aiff"),
        Pair(".al", "application/x-perl"),
        Pair(".alz", "application/x-alz"),
        Pair(".amr", "audio/amr"),
        Pair(".ani", "application/x-navi-animation"),
        Pair(".anim[1-9j]", "video/x-anim"),
        Pair(".anx", "application/annodex"),
        Pair(".ape", "audio/x-ape"),
        Pair(".arj", "application/x-arj"),
        Pair(".arw", "image/x-sony-arw"),
        Pair(".as", "application/x-applix-spreadsheet"),
        Pair(".asc", "text/plain"),
        Pair(".asf", "video/x-ms-asf"),
        Pair(".asp", "application/x-asp"),
        Pair(".ass", "text/x-ssa"),
        Pair(".asx", "audio/x-ms-asx"),
        Pair(".atom", "application/atom+xml"),
        Pair(".au", "audio/basic"),
        Pair(".avi", "video/x-msvideo"),
        Pair(".aw", "application/x-applix-word"),
        Pair(".awb", "audio/amr-wb"),
        Pair(".awk", "application/x-awk"),
        Pair(".axa", "audio/annodex"),
        Pair(".axv", "video/annodex"),
        Pair(".bak", "application/x-trash"),
        Pair(".bcpio", "application/x-bcpio"),
        Pair(".bdf", "application/x-font-bdf"),
        Pair(".bib", "text/x-bibtex"),
        Pair(".bin", "application/octet-stream"),
        Pair(".blend", "application/x-blender"),
        Pair(".blender", "application/x-blender"),
        Pair(".bmp", "image/bmp"),
        Pair(".bz", "application/x-bzip"),
        Pair(".bz2", "application/x-bzip"),
        Pair(".c", "text/x-csrc"),
        Pair(".c++", "text/x-c++src"),
        Pair(".cab", "application/vnd.ms-cab-compressed"),
        Pair(".cb7", "application/x-cb7"),
        Pair(".cbr", "application/x-cbr"),
        Pair(".cbt", "application/x-cbt"),
        Pair(".cbz", "application/x-cbz"),
        Pair(".cc", "text/x-c++src"),
        Pair(".cdf", "application/x-netcdf"),
        Pair(".cdr", "application/vnd.corel-draw"),
        Pair(".cer", "application/x-x509-ca-cert"),
        Pair(".cert", "application/x-x509-ca-cert"),
        Pair(".cgm", "image/cgm"),
        Pair(".chm", "application/x-chm"),
        Pair(".chrt", "application/x-kchart"),
        Pair(".class", "application/x-java"),
        Pair(".cls", "text/x-tex"),
        Pair(".cmake", "text/x-cmake"),
        Pair(".cpio", "application/x-cpio"),
        Pair(".cpio.gz", "application/x-cpio-compressed"),
        Pair(".cpp", "text/x-c++src"),
        Pair(".cr2", "image/x-canon-cr2"),
        Pair(".crt", "application/x-x509-ca-cert"),
        Pair(".crw", "image/x-canon-crw"),
        Pair(".cs", "text/x-csharp"),
        Pair(".csh", "application/x-csh"),
        Pair(".css", "text/css"),
        Pair(".cssl", "text/css"),
        Pair(".csv", "text/csv"),
        Pair(".cue", "application/x-cue"),
        Pair(".cur", "image/x-win-bitmap"),
        Pair(".cxx", "text/x-c++src"),
        Pair(".d", "text/x-dsrc"),
        Pair(".dar", "application/x-dar"),
        Pair(".dbf", "application/x-dbf"),
        Pair(".dc", "application/x-dc-rom"),
        Pair(".dcl", "text/x-dcl"),
        Pair(".dcm", "application/dicom"),
        Pair(".dcr", "image/x-kodak-dcr"),
        Pair(".dds", "image/x-dds"),
        Pair(".deb", "application/x-deb"),
        Pair(".der", "application/x-x509-ca-cert"),
        Pair(".desktop", "application/x-desktop"),
        Pair(".dia", "application/x-dia-diagram"),
        Pair(".diff", "text/x-patch"),
        Pair(".divx", "video/x-msvideo"),
        Pair(".djv", "image/vnd.djvu"),
        Pair(".djvu", "image/vnd.djvu"),
        Pair(".dng", "image/x-adobe-dng"),
        Pair(".doc", "application/msword"),
        Pair(".docbook", "application/docbook+xml"),
        Pair(".docm", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
        Pair(".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
        Pair(".dot", "text/vnd.graphviz"),
        Pair(".dsl", "text/x-dsl"),
        Pair(".dtd", "application/xml-dtd"),
        Pair(".dtx", "text/x-tex"),
        Pair(".dv", "video/dv"),
        Pair(".dvi", "application/x-dvi"),
        Pair(".dvi.bz2", "application/x-bzdvi"),
        Pair(".dvi.gz", "application/x-gzdvi"),
        Pair(".dwg", "image/vnd.dwg"),
        Pair(".dxf", "image/vnd.dxf"),
        Pair(".e", "text/x-eiffel"),
        Pair(".egon", "application/x-egon"),
        Pair(".eif", "text/x-eiffel"),
        Pair(".el", "text/x-emacs-lisp"),
        Pair(".emf", "image/x-emf"),
        Pair(".emp", "application/vnd.emusic-emusic_package"),
        Pair(".ent", "application/xml-external-parsed-entity"),
        Pair(".eps", "image/x-eps"),
        Pair(".eps.bz2", "image/x-bzeps"),
        Pair(".eps.gz", "image/x-gzeps"),
        Pair(".epsf", "image/x-eps"),
        Pair(".epsf.bz2", "image/x-bzeps"),
        Pair(".epsf.gz", "image/x-gzeps"),
        Pair(".epsi", "image/x-eps"),
        Pair(".epsi.bz2", "image/x-bzeps"),
        Pair(".epsi.gz", "image/x-gzeps"),
        Pair(".epub", "application/epub+zip"),
        Pair(".erl", "text/x-erlang"),
        Pair(".es", "application/ecmascript"),
        Pair(".etheme", "application/x-e-theme"),
        Pair(".etx", "text/x-setext"),
        Pair(".exe", "application/x-ms-dos-executable"),
        Pair(".exr", "image/x-exr"),
        Pair(".ez", "application/andrew-inset"),
        Pair(".f", "text/x-fortran"),
        Pair(".f90", "text/x-fortran"),
        Pair(".f95", "text/x-fortran"),
        Pair(".fb2", "application/x-fictionbook+xml"),
        Pair(".fig", "image/x-xfig"),
        Pair(".fits", "image/fits"),
        Pair(".fl", "application/x-fluid"),
        Pair(".flac", "audio/x-flac"),
        Pair(".flc", "video/x-flic"),
        Pair(".fli", "video/x-flic"),
        Pair(".flv", "video/x-flv"),
        Pair(".flw", "application/x-kivio"),
        Pair(".fo", "text/x-xslfo"),
        Pair(".for", "text/x-fortran"),
        Pair(".g3", "image/fax-g3"),
        Pair(".gb", "application/x-gameboy-rom"),
        Pair(".gba", "application/x-gba-rom"),
        Pair(".gcrd", "text/directory"),
        Pair(".ged", "application/x-gedcom"),
        Pair(".gedcom", "application/x-gedcom"),
        Pair(".gen", "application/x-genesis-rom"),
        Pair(".gf", "application/x-tex-gf"),
        Pair(".gg", "application/x-sms-rom"),
        Pair(".gif", "image/gif"),
        Pair(".glade", "application/x-glade"),
        Pair(".gmo", "application/x-gettext-translation"),
        Pair(".gnc", "application/x-gnucash"),
        Pair(".gnd", "application/gnunet-directory"),
        Pair(".gnucash", "application/x-gnucash"),
        Pair(".gnumeric", "application/x-gnumeric"),
        Pair(".gnuplot", "application/x-gnuplot"),
        Pair(".gp", "application/x-gnuplot"),
        Pair(".gpg", "application/pgp-encrypted"),
        Pair(".gplt", "application/x-gnuplot"),
        Pair(".gra", "application/x-graphite"),
        Pair(".gsf", "application/x-font-type1"),
        Pair(".gsm", "audio/x-gsm"),
        Pair(".gtar", "application/x-tar"),
        Pair(".gv", "text/vnd.graphviz"),
        Pair(".gvp", "text/x-google-video-pointer"),
        Pair(".gz", "application/x-gzip"),
        Pair(".h", "text/x-chdr"),
        Pair(".h++", "text/x-c++hdr"),
        Pair(".hdf", "application/x-hdf"),
        Pair(".hh", "text/x-c++hdr"),
        Pair(".hp", "text/x-c++hdr"),
        Pair(".hpgl", "application/vnd.hp-hpgl"),
        Pair(".hpp", "text/x-c++hdr"),
        Pair(".hs", "text/x-haskell"),
        Pair(".htm", "text/html"),
        Pair(".html", "text/html"),
        Pair(".hwp", "application/x-hwp"),
        Pair(".hwt", "application/x-hwt"),
        Pair(".hxx", "text/x-c++hdr"),
        Pair(".ica", "application/x-ica"),
        Pair(".icb", "image/x-tga"),
        Pair(".icns", "image/x-icns"),
        Pair(".ico", "image/vnd.microsoft.icon"),
        Pair(".ics", "text/calendar"),
        Pair(".idl", "text/x-idl"),
        Pair(".ief", "image/ief"),
        Pair(".iff", "image/x-iff"),
        Pair(".ilbm", "image/x-ilbm"),
        Pair(".ime", "text/x-imelody"),
        Pair(".imy", "text/x-imelody"),
        Pair(".ins", "text/x-tex"),
        Pair(".iptables", "text/x-iptables"),
        Pair(".iso", "application/x-cd-image"),
        Pair(".iso9660", "application/x-cd-image"),
        Pair(".it", "audio/x-it"),
        Pair(".j2k", "image/jp2"),
        Pair(".jad", "text/vnd.sun.j2me.app-descriptor"),
        Pair(".jar", "application/x-java-archive"),
        Pair(".java", "text/x-java"),
        Pair(".jng", "image/x-jng"),
        Pair(".jnlp", "application/x-java-jnlp-file"),
        Pair(".jp2", "image/jp2"),
        Pair(".jpc", "image/jp2"),
        Pair(".jpe", "image/jpeg"),
        Pair(".jpeg", "image/jpeg"),
        Pair(".jpf", "image/jp2"),
        Pair(".jpg", "image/jpeg"),
        Pair(".jpr", "application/x-jbuilder-project"),
        Pair(".jpx", "image/jp2"),
        Pair(".js", "application/javascript"),
        Pair(".json", "application/json"),
        Pair(".jsonp", "application/jsonp"),
        Pair(".k25", "image/x-kodak-k25"),
        Pair(".kar", "audio/midi"),
        Pair(".karbon", "application/x-karbon"),
        Pair(".kdc", "image/x-kodak-kdc"),
        Pair(".kdelnk", "application/x-desktop"),
        Pair(".kexi", "application/x-kexiproject-sqlite3"),
        Pair(".kexic", "application/x-kexi-connectiondata"),
        Pair(".kexis", "application/x-kexiproject-shortcut"),
        Pair(".kfo", "application/x-kformula"),
        Pair(".kil", "application/x-killustrator"),
        Pair(".kino", "application/smil"),
        Pair(".kml", "application/vnd.google-earth.kml+xml"),
        Pair(".kmz", "application/vnd.google-earth.kmz"),
        Pair(".kon", "application/x-kontour"),
        Pair(".kpm", "application/x-kpovmodeler"),
        Pair(".kpr", "application/x-kpresenter"),
        Pair(".kpt", "application/x-kpresenter"),
        Pair(".kra", "application/x-krita"),
        Pair(".ksp", "application/x-kspread"),
        Pair(".kud", "application/x-kugar"),
        Pair(".kwd", "application/x-kword"),
        Pair(".kwt", "application/x-kword"),
        Pair(".la", "application/x-shared-library-la"),
        Pair(".latex", "text/x-tex"),
        Pair(".ldif", "text/x-ldif"),
        Pair(".lha", "application/x-lha"),
        Pair(".lhs", "text/x-literate-haskell"),
        Pair(".lhz", "application/x-lhz"),
        Pair(".log", "text/x-log"),
        Pair(".ltx", "text/x-tex"),
        Pair(".lua", "text/x-lua"),
        Pair(".lwo", "image/x-lwo"),
        Pair(".lwob", "image/x-lwo"),
        Pair(".lws", "image/x-lws"),
        Pair(".ly", "text/x-lilypond"),
        Pair(".lyx", "application/x-lyx"),
        Pair(".lz", "application/x-lzip"),
        Pair(".lzh", "application/x-lha"),
        Pair(".lzma", "application/x-lzma"),
        Pair(".lzo", "application/x-lzop"),
        Pair(".m", "text/x-matlab"),
        Pair(".m15", "audio/x-mod"),
        Pair(".m2t", "video/mpeg"),
        Pair(".m3u", "audio/x-mpegurl"),
        Pair(".m3u8", "audio/x-mpegurl"),
        Pair(".m4", "application/x-m4"),
        Pair(".m4a", "audio/mp4"),
        Pair(".m4b", "audio/x-m4b"),
        Pair(".m4v", "video/mp4"),
        Pair(".mab", "application/x-markaby"),
        Pair(".man", "application/x-troff-man"),
        Pair(".mbox", "application/mbox"),
        Pair(".md", "application/x-genesis-rom"),
        Pair(".mdb", "application/vnd.ms-access"),
        Pair(".mdi", "image/vnd.ms-modi"),
        Pair(".me", "text/x-troff-me"),
        Pair(".med", "audio/x-mod"),
        Pair(".metalink", "application/metalink+xml"),
        Pair(".mgp", "application/x-magicpoint"),
        Pair(".mid", "audio/midi"),
        Pair(".midi", "audio/midi"),
        Pair(".mif", "application/x-mif"),
        Pair(".minipsf", "audio/x-minipsf"),
        Pair(".mka", "audio/x-matroska"),
        Pair(".mkv", "video/x-matroska"),
        Pair(".ml", "text/x-ocaml"),
        Pair(".mli", "text/x-ocaml"),
        Pair(".mm", "text/x-troff-mm"),
        Pair(".mmf", "application/x-smaf"),
        Pair(".mml", "text/mathml"),
        Pair(".mng", "video/x-mng"),
        Pair(".mo", "application/x-gettext-translation"),
        Pair(".mo3", "audio/x-mo3"),
        Pair(".moc", "text/x-moc"),
        Pair(".mod", "audio/x-mod"),
        Pair(".mof", "text/x-mof"),
        Pair(".moov", "video/quicktime"),
        Pair(".mov", "video/quicktime"),
        Pair(".movie", "video/x-sgi-movie"),
        Pair(".mp+", "audio/x-musepack"),
        Pair(".mp2", "video/mpeg"),
        Pair(".mp3", "audio/mpeg"),
        Pair(".mp4", "video/mp4"),
        Pair(".mpc", "audio/x-musepack"),
        Pair(".mpe", "video/mpeg"),
        Pair(".mpeg", "video/mpeg"),
        Pair(".mpg", "video/mpeg"),
        Pair(".mpga", "audio/mpeg"),
        Pair(".mpp", "audio/x-musepack"),
        Pair(".mrl", "text/x-mrml"),
        Pair(".mrml", "text/x-mrml"),
        Pair(".mrw", "image/x-minolta-mrw"),
        Pair(".ms", "text/x-troff-ms"),
        Pair(".msi", "application/x-msi"),
        Pair(".msod", "image/x-msod"),
        Pair(".msx", "application/x-msx-rom"),
        Pair(".mtm", "audio/x-mod"),
        Pair(".mup", "text/x-mup"),
        Pair(".mxf", "application/mxf"),
        Pair(".n64", "application/x-n64-rom"),
        Pair(".nb", "application/mathematica"),
        Pair(".nc", "application/x-netcdf"),
        Pair(".nds", "application/x-nintendo-ds-rom"),
        Pair(".nef", "image/x-nikon-nef"),
        Pair(".nes", "application/x-nes-rom"),
        Pair(".nfo", "text/x-nfo"),
        Pair(".not", "text/x-mup"),
        Pair(".nsc", "application/x-netshow-channel"),
        Pair(".nsv", "video/x-nsv"),
        Pair(".o", "application/x-object"),
        Pair(".obj", "application/x-tgif"),
        Pair(".ocl", "text/x-ocl"),
        Pair(".oda", "application/oda"),
        Pair(".odb", "application/vnd.oasis.opendocument.database"),
        Pair(".odc", "application/vnd.oasis.opendocument.chart"),
        Pair(".odf", "application/vnd.oasis.opendocument.formula"),
        Pair(".odg", "application/vnd.oasis.opendocument.graphics"),
        Pair(".odi", "application/vnd.oasis.opendocument.image"),
        Pair(".odm", "application/vnd.oasis.opendocument.text-master"),
        Pair(".odp", "application/vnd.oasis.opendocument.presentation"),
        Pair(".ods", "application/vnd.oasis.opendocument.spreadsheet"),
        Pair(".odt", "application/vnd.oasis.opendocument.text"),
        Pair(".oga", "audio/ogg"),
        Pair(".ogg", "video/x-theora+ogg"),
        Pair(".ogm", "video/x-ogm+ogg"),
        Pair(".ogv", "video/ogg"),
        Pair(".ogx", "application/ogg"),
        Pair(".old", "application/x-trash"),
        Pair(".oleo", "application/x-oleo"),
        Pair(".opml", "text/x-opml+xml"),
        Pair(".ora", "image/openraster"),
        Pair(".orf", "image/x-olympus-orf"),
        Pair(".otc", "application/vnd.oasis.opendocument.chart-template"),
        Pair(".otf", "application/x-font-otf"),
        Pair(".otg", "application/vnd.oasis.opendocument.graphics-template"),
        Pair(".oth", "application/vnd.oasis.opendocument.text-web"),
        Pair(".otp", "application/vnd.oasis.opendocument.presentation-template"),
        Pair(".ots", "application/vnd.oasis.opendocument.spreadsheet-template"),
        Pair(".ott", "application/vnd.oasis.opendocument.text-template"),
        Pair(".owl", "application/rdf+xml"),
        Pair(".oxt", "application/vnd.openofficeorg.extension"),
        Pair(".p", "text/x-pascal"),
        Pair(".p10", "application/pkcs10"),
        Pair(".p12", "application/x-pkcs12"),
        Pair(".p7b", "application/x-pkcs7-certificates"),
        Pair(".p7s", "application/pkcs7-signature"),
        Pair(".pack", "application/x-java-pack200"),
        Pair(".pak", "application/x-pak"),
        Pair(".par2", "application/x-par2"),
        Pair(".pas", "text/x-pascal"),
        Pair(".patch", "text/x-patch"),
        Pair(".pbm", "image/x-portable-bitmap"),
        Pair(".pcd", "image/x-photo-cd"),
        Pair(".pcf", "application/x-cisco-vpn-settings"),
        Pair(".pcf.gz", "application/x-font-pcf"),
        Pair(".pcf.z", "application/x-font-pcf"),
        Pair(".pcl", "application/vnd.hp-pcl"),
        Pair(".pcx", "image/x-pcx"),
        Pair(".pdb", "chemical/x-pdb"),
        Pair(".pdc", "application/x-aportisdoc"),
        Pair(".pdf", "application/pdf"),
        Pair(".pdf.bz2", "application/x-bzpdf"),
        Pair(".pdf.gz", "application/x-gzpdf"),
        Pair(".pef", "image/x-pentax-pef"),
        Pair(".pem", "application/x-x509-ca-cert"),
        Pair(".perl", "application/x-perl"),
        Pair(".pfa", "application/x-font-type1"),
        Pair(".pfb", "application/x-font-type1"),
        Pair(".pfx", "application/x-pkcs12"),
        Pair(".pgm", "image/x-portable-graymap"),
        Pair(".pgn", "application/x-chess-pgn"),
        Pair(".pgp", "application/pgp-encrypted"),
        Pair(".php", "application/x-php"),
        Pair(".php3", "application/x-php"),
        Pair(".php4", "application/x-php"),
        Pair(".pict", "image/x-pict"),
        Pair(".pict1", "image/x-pict"),
        Pair(".pict2", "image/x-pict"),
        Pair(".pickle", "application/python-pickle"),
        Pair(".pk", "application/x-tex-pk"),
        Pair(".pkipath", "application/pkix-pkipath"),
        Pair(".pkr", "application/pgp-keys"),
        Pair(".pl", "application/x-perl"),
        Pair(".pla", "audio/x-iriver-pla"),
        Pair(".pln", "application/x-planperfect"),
        Pair(".pls", "audio/x-scpls"),
        Pair(".pm", "application/x-perl"),
        Pair(".png", "image/png"),
        Pair(".pnm", "image/x-portable-anymap"),
        Pair(".pntg", "image/x-macpaint"),
        Pair(".po", "text/x-gettext-translation"),
        Pair(".por", "application/x-spss-por"),
        Pair(".pot", "text/x-gettext-translation-template"),
        Pair(".ppm", "image/x-portable-pixmap"),
        Pair(".pps", "application/vnd.ms-powerpoint"),
        Pair(".ppt", "application/vnd.ms-powerpoint"),
        Pair(".pptm", "application/vnd.openxmlformats-officedocument.presentationml.presentation"),
        Pair(".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"),
        Pair(".ppz", "application/vnd.ms-powerpoint"),
        Pair(".prc", "application/x-palm-database"),
        Pair(".ps", "application/postscript"),
        Pair(".ps.bz2", "application/x-bzpostscript"),
        Pair(".ps.gz", "application/x-gzpostscript"),
        Pair(".psd", "image/vnd.adobe.photoshop"),
        Pair(".psf", "audio/x-psf"),
        Pair(".psf.gz", "application/x-gz-font-linux-psf"),
        Pair(".psflib", "audio/x-psflib"),
        Pair(".psid", "audio/prs.sid"),
        Pair(".psw", "application/x-pocket-word"),
        Pair(".pw", "application/x-pw"),
        Pair(".py", "text/x-python"),
        Pair(".pyc", "application/x-python-bytecode"),
        Pair(".pyo", "application/x-python-bytecode"),
        Pair(".qif", "image/x-quicktime"),
        Pair(".qt", "video/quicktime"),
        Pair(".qtif", "image/x-quicktime"),
        Pair(".qtl", "application/x-quicktime-media-link"),
        Pair(".qtvr", "video/quicktime"),
        Pair(".ra", "audio/vnd.rn-realaudio"),
        Pair(".raf", "image/x-fuji-raf"),
        Pair(".ram", "application/ram"),
        Pair(".rar", "application/x-rar"),
        Pair(".ras", "image/x-cmu-raster"),
        Pair(".raw", "image/x-panasonic-raw"),
        Pair(".rax", "audio/vnd.rn-realaudio"),
        Pair(".rb", "application/x-ruby"),
        Pair(".rdf", "application/rdf+xml"),
        Pair(".rdfs", "application/rdf+xml"),
        Pair(".reg", "text/x-ms-regedit"),
        Pair(".rej", "application/x-reject"),
        Pair(".rgb", "image/x-rgb"),
        Pair(".rle", "image/rle"),
        Pair(".rm", "application/vnd.rn-realmedia"),
        Pair(".rmj", "application/vnd.rn-realmedia"),
        Pair(".rmm", "application/vnd.rn-realmedia"),
        Pair(".rms", "application/vnd.rn-realmedia"),
        Pair(".rmvb", "application/vnd.rn-realmedia"),
        Pair(".rmx", "application/vnd.rn-realmedia"),
        Pair(".roff", "text/troff"),
        Pair(".rp", "image/vnd.rn-realpix"),
        Pair(".rpm", "application/x-rpm"),
        Pair(".rss", "application/rss+xml"),
        Pair(".rt", "text/vnd.rn-realtext"),
        Pair(".rtf", "application/rtf"),
        Pair(".rtx", "text/richtext"),
        Pair(".rv", "video/vnd.rn-realvideo"),
        Pair(".rvx", "video/vnd.rn-realvideo"),
        Pair(".s3m", "audio/x-s3m"),
        Pair(".sam", "application/x-amipro"),
        Pair(".sami", "application/x-sami"),
        Pair(".sav", "application/x-spss-sav"),
        Pair(".scm", "text/x-scheme"),
        Pair(".sda", "application/vnd.stardivision.draw"),
        Pair(".sdc", "application/vnd.stardivision.calc"),
        Pair(".sdd", "application/vnd.stardivision.impress"),
        Pair(".sdp", "application/sdp"),
        Pair(".sds", "application/vnd.stardivision.chart"),
        Pair(".sdw", "application/vnd.stardivision.writer"),
        Pair(".sgf", "application/x-go-sgf"),
        Pair(".sgi", "image/x-sgi"),
        Pair(".sgl", "application/vnd.stardivision.writer"),
        Pair(".sgm", "text/sgml"),
        Pair(".sgml", "text/sgml"),
        Pair(".sh", "application/x-shellscript"),
        Pair(".shar", "application/x-shar"),
        Pair(".shn", "application/x-shorten"),
        Pair(".siag", "application/x-siag"),
        Pair(".sid", "audio/prs.sid"),
        Pair(".sik", "application/x-trash"),
        Pair(".sis", "application/vnd.symbian.install"),
        Pair(".sisx", "x-epoc/x-sisx-app"),
        Pair(".sit", "application/x-stuffit"),
        Pair(".siv", "application/sieve"),
        Pair(".sk", "image/x-skencil"),
        Pair(".sk1", "image/x-skencil"),
        Pair(".skr", "application/pgp-keys"),
        Pair(".slk", "text/spreadsheet"),
        Pair(".smaf", "application/x-smaf"),
        Pair(".smc", "application/x-snes-rom"),
        Pair(".smd", "application/vnd.stardivision.mail"),
        Pair(".smf", "application/vnd.stardivision.math"),
        Pair(".smi", "application/x-sami"),
        Pair(".smil", "application/smil"),
        Pair(".sml", "application/smil"),
        Pair(".sms", "application/x-sms-rom"),
        Pair(".snd", "audio/basic"),
        Pair(".so", "application/x-sharedlib"),
        Pair(".spc", "application/x-pkcs7-certificates"),
        Pair(".spd", "application/x-font-speedo"),
        Pair(".spec", "text/x-rpm-spec"),
        Pair(".spl", "application/x-shockwave-flash"),
        Pair(".spx", "audio/x-speex"),
        Pair(".sql", "text/x-sql"),
        Pair(".sr2", "image/x-sony-sr2"),
        Pair(".src", "application/x-wais-source"),
        Pair(".srf", "image/x-sony-srf"),
        Pair(".srt", "application/x-subrip"),
        Pair(".ssa", "text/x-ssa"),
        Pair(".stc", "application/vnd.sun.xml.calc.template"),
        Pair(".std", "application/vnd.sun.xml.draw.template"),
        Pair(".sti", "application/vnd.sun.xml.impress.template"),
        Pair(".stm", "audio/x-stm"),
        Pair(".stw", "application/vnd.sun.xml.writer.template"),
        Pair(".sty", "text/x-tex"),
        Pair(".sub", "text/x-subviewer"),
        Pair(".sun", "image/x-sun-raster"),
        Pair(".sv4cpio", "application/x-sv4cpio"),
        Pair(".sv4crc", "application/x-sv4crc"),
        Pair(".svg", "image/svg+xml"),
        Pair(".svgz", "image/svg+xml-compressed"),
        Pair(".swf", "application/x-shockwave-flash"),
        Pair(".sxc", "application/vnd.sun.xml.calc"),
        Pair(".sxd", "application/vnd.sun.xml.draw"),
        Pair(".sxg", "application/vnd.sun.xml.writer.global"),
        Pair(".sxi", "application/vnd.sun.xml.impress"),
        Pair(".sxm", "application/vnd.sun.xml.math"),
        Pair(".sxw", "application/vnd.sun.xml.writer"),
        Pair(".sylk", "text/spreadsheet"),
        Pair(".t", "text/troff"),
        Pair(".t2t", "text/x-txt2tags"),
        Pair(".tar", "application/x-tar"),
        Pair(".tar.bz", "application/x-bzip-compressed-tar"),
        Pair(".tar.bz2", "application/x-bzip-compressed-tar"),
        Pair(".tar.gz", "application/x-compressed-tar"),
        Pair(".tar.lzma", "application/x-lzma-compressed-tar"),
        Pair(".tar.lzo", "application/x-tzo"),
        Pair(".tar.xz", "application/x-xz-compressed-tar"),
        Pair(".tar.z", "application/x-tarz"),
        Pair(".tbz", "application/x-bzip-compressed-tar"),
        Pair(".tbz2", "application/x-bzip-compressed-tar"),
        Pair(".tcl", "text/x-tcl"),
        Pair(".tex", "text/x-tex"),
        Pair(".texi", "text/x-texinfo"),
        Pair(".texinfo", "text/x-texinfo"),
        Pair(".tga", "image/x-tga"),
        Pair(".tgz", "application/x-compressed-tar"),
        Pair(".theme", "application/x-theme"),
        Pair(".themepack", "application/x-windows-themepack"),
        Pair(".tif", "image/tiff"),
        Pair(".tiff", "image/tiff"),
        Pair(".tk", "text/x-tcl"),
        Pair(".tlz", "application/x-lzma-compressed-tar"),
        Pair(".tnef", "application/vnd.ms-tnef"),
        Pair(".tnf", "application/vnd.ms-tnef"),
        Pair(".toc", "application/x-cdrdao-toc"),
        Pair(".torrent", "application/x-bittorrent"),
        Pair(".tpic", "image/x-tga"),
        Pair(".tr", "text/troff"),
        Pair(".ts", "application/x-linguist"),
        Pair(".tsv", "text/tab-separated-values"),
        Pair(".tta", "audio/x-tta"),
        Pair(".ttc", "application/x-font-ttf"),
        Pair(".ttf", "application/x-font-ttf"),
        Pair(".ttx", "application/x-font-ttx"),
        Pair(".txt", "text/plain"),
        Pair(".txz", "application/x-xz-compressed-tar"),
        Pair(".tzo", "application/x-tzo"),
        Pair(".ufraw", "application/x-ufraw"),
        Pair(".ui", "application/x-designer"),
        Pair(".uil", "text/x-uil"),
        Pair(".ult", "audio/x-mod"),
        Pair(".uni", "audio/x-mod"),
        Pair(".uri", "text/x-uri"),
        Pair(".url", "text/x-uri"),
        Pair(".ustar", "application/x-ustar"),
        Pair(".vala", "text/x-vala"),
        Pair(".vapi", "text/x-vala"),
        Pair(".vcf", "text/directory"),
        Pair(".vcs", "text/calendar"),
        Pair(".vct", "text/directory"),
        Pair(".vda", "image/x-tga"),
        Pair(".vhd", "text/x-vhdl"),
        Pair(".vhdl", "text/x-vhdl"),
        Pair(".viv", "video/vivo"),
        Pair(".vivo", "video/vivo"),
        Pair(".vlc", "audio/x-mpegurl"),
        Pair(".vob", "video/mpeg"),
        Pair(".voc", "audio/x-voc"),
        Pair(".vor", "application/vnd.stardivision.writer"),
        Pair(".vst", "image/x-tga"),
        Pair(".wav", "audio/x-wav"),
        Pair(".wax", "audio/x-ms-asx"),
        Pair(".wb1", "application/x-quattropro"),
        Pair(".wb2", "application/x-quattropro"),
        Pair(".wb3", "application/x-quattropro"),
        Pair(".wbmp", "image/vnd.wap.wbmp"),
        Pair(".wcm", "application/vnd.ms-works"),
        Pair(".wdb", "application/vnd.ms-works"),
        Pair(".webm", "video/webm"),
        Pair(".wk1", "application/vnd.lotus-1-2-3"),
        Pair(".wk3", "application/vnd.lotus-1-2-3"),
        Pair(".wk4", "application/vnd.lotus-1-2-3"),
        Pair(".wks", "application/vnd.ms-works"),
        Pair(".wma", "audio/x-ms-wma"),
        Pair(".wmf", "image/x-wmf"),
        Pair(".wml", "text/vnd.wap.wml"),
        Pair(".wmls", "text/vnd.wap.wmlscript"),
        Pair(".wmv", "video/x-ms-wmv"),
        Pair(".wmx", "audio/x-ms-asx"),
        Pair(".wp", "application/vnd.wordperfect"),
        Pair(".wp4", "application/vnd.wordperfect"),
        Pair(".wp5", "application/vnd.wordperfect"),
        Pair(".wp6", "application/vnd.wordperfect"),
        Pair(".wpd", "application/vnd.wordperfect"),
        Pair(".wpg", "application/x-wpg"),
        Pair(".wpl", "application/vnd.ms-wpl"),
        Pair(".wpp", "application/vnd.wordperfect"),
        Pair(".wps", "application/vnd.ms-works"),
        Pair(".wri", "application/x-mswrite"),
        Pair(".wrl", "model/vrml"),
        Pair(".wv", "audio/x-wavpack"),
        Pair(".wvc", "audio/x-wavpack-correction"),
        Pair(".wvp", "audio/x-wavpack"),
        Pair(".wvx", "audio/x-ms-asx"),
        Pair(".x3f", "image/x-sigma-x3f"),
        Pair(".xac", "application/x-gnucash"),
        Pair(".xbel", "application/x-xbel"),
        Pair(".xbl", "application/xml"),
        Pair(".xbm", "image/x-xbitmap"),
        Pair(".xcf", "image/x-xcf"),
        Pair(".xcf.bz2", "image/x-compressed-xcf"),
        Pair(".xcf.gz", "image/x-compressed-xcf"),
        Pair(".xhtml", "application/xhtml+xml"),
        Pair(".xi", "audio/x-xi"),
        Pair(".xla", "application/vnd.ms-excel"),
        Pair(".xlc", "application/vnd.ms-excel"),
        Pair(".xld", "application/vnd.ms-excel"),
        Pair(".xlf", "application/x-xliff"),
        Pair(".xliff", "application/x-xliff"),
        Pair(".xll", "application/vnd.ms-excel"),
        Pair(".xlm", "application/vnd.ms-excel"),
        Pair(".xls", "application/vnd.ms-excel"),
        Pair(".xlsm", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
        Pair(".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
        Pair(".xlt", "application/vnd.ms-excel"),
        Pair(".xlw", "application/vnd.ms-excel"),
        Pair(".xm", "audio/x-xm"),
        Pair(".xmf", "audio/x-xmf"),
        Pair(".xmi", "text/x-xmi"),
        Pair(".xml", "application/xml"),
        Pair(".xpm", "image/x-xpixmap"),
        Pair(".xps", "application/vnd.ms-xpsdocument"),
        Pair(".xsl", "application/xml"),
        Pair(".xslfo", "text/x-xslfo"),
        Pair(".xslt", "application/xml"),
        Pair(".xspf", "application/xspf+xml"),
        Pair(".xul", "application/vnd.mozilla.xul+xml"),
        Pair(".xwd", "image/x-xwindowdump"),
        Pair(".xyz", "chemical/x-pdb"),
        Pair(".xz", "application/x-xz"),
        Pair(".w2p", "application/w2p"),
        Pair(".z", "application/x-compress"),
        Pair(".zabw", "application/x-abiword"),
        Pair(".zip", "application/zip"),
        Pair(".zoo", "application/x-zoo")
    ).forEach { entry ->
        if (this.name.endsWith(entry.key)) {
            contentType = entry.value
            return@forEach
        }
    }
    return contentType ?: "application/octet-stream"
}

/**
 * 获取网络连接中的文件名称
 */
fun String.getUrlFileName(): String {
    val start = lastIndexOf("/")
    return if (start != -1)
        substring(start + 1)
    else
        ""
}

// 地址
val areaCode: Map<String, String>
    get() {
        val map: MutableMap<String, String> = mutableMapOf()
        map["11"] = "北京"
        map["12"] = "天津"
        map["13"] = "河北"
        map["14"] = "山西"
        map["15"] = "内蒙古"
        map["21"] = "辽宁"
        map["22"] = "吉林"
        map["23"] = "黑龙江"
        map["31"] = "上海"
        map["32"] = "江苏"
        map["33"] = "浙江"
        map["34"] = "安徽"
        map["35"] = "福建"
        map["36"] = "江西"
        map["37"] = "山东"
        map["41"] = "河南"
        map["42"] = "湖北"
        map["43"] = "湖南"
        map["44"] = "广东"
        map["45"] = "广西"
        map["46"] = "海南"
        map["50"] = "重庆"
        map["51"] = "四川"
        map["52"] = "贵州"
        map["53"] = "云南"
        map["54"] = "西藏"
        map["61"] = "陕西"
        map["62"] = "甘肃"
        map["63"] = "青海"
        map["64"] = "宁夏"
        map["65"] = "新疆"
        map["71"] = "台湾"
        map["81"] = "香港"
        map["82"] = "澳门"
        map["91"] = "国外"
        return map
    }

/**
 * 从Manifest中获取meta-data值
 * @param context
 * @param key
 * @return
 */
fun getMetaData(context: Context, i: Int, key: String): String {
    var value: String? = null
    try {
        val appInfo = context.packageManager.getApplicationInfo(
            context.packageName,
            PackageManager.GET_META_DATA
        )
        value = appInfo.metaData.getString(key)
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    if (!TextUtils.isEmpty(value)) {
        if (i == 1) {
            return when (value) {
                "JCT001" -> "360"
                "JCT002" -> "百度应"
                "JCT003" -> "阿里系"
                "JCT004" -> "应用宝"
                "JCT005" -> "应用汇"
                "JCT006" -> "小米"
                "JCT007" -> "华为"
                "JCT008" -> "邮箱"
                "JCT009" -> "OPPO"
                "JCT010" -> "三星"
                "JCT012" -> "vivo"
                else -> "魅族"
            }
        } else {
            return value!!
        }
    } else {
        return ""
    }
}

/**
 * 返回加上万 亿的字符串
 *
 * @param longNum
 * @return
 */
fun formatBigNum(longNum: Long): String {
    val mY = 100000000 //亿

    val mW = 10000 //万

    val result: String = when {
        longNum >= mY -> {
            (longNum / mY).toString() + "." + longNum % mY / (mY / 10) + "亿"
        }
        longNum >= mW -> {
            (longNum / mW).toString() + "." + longNum % mW / (mW / 10) + "万"
        }
        else -> {
            longNum.toString()
        }
    }
    return result
}

var ODD_CHINESE_CHARS = ("厷厸厹厺厼厽厾叀叁参叄叅叆叇亝収叏叐叒叓叕叚叜叝叞"
        + "叠叧叨叭叱叴叵叺叻叼叽叾卟叿吀吁吂吅吆吇吋吒吔吖吘吙吚吜吡吢吣吤吥吧吩吪吭吮吰吱吲呐吷吺吽呁呃呄呅呇呉"
        + "呋呋呌呍呎呏呐呒呓呔呕呗呙呚呛呜呝呞呟呠呡呢呣呤呥呦呧周呩呪呫呬呭呮呯呰呱呲呴呶呵呷呸呹呺呻呾呿咀咁咂"
        + "咃咄咅咇咈咉咊咋咍咎咐咑咓咔咕咖咗咘咙咚咛咜咝咞咟咠咡咢咣咤咥咦咧咨咩咪咫咬咭咮咯咰咲咳咴咵咶啕咹咺咻"
        + "呙咽咾咿哂哃哅哆哇哈哊哋哌哎哏哐哑哒哓哔哕哖哗哘哙哚哛哜哝哞哟哠咔哣哤哦哧哩哪哫哬哯哰唝哵哶哷哸哹哻哼"
        + "哽哾哿唀唁唂唃呗唅唆唈唉唊唋唌唍唎唏唑唒唓唔唣唖唗唘唙吣唛唜唝唞唟唠唡唢唣唤唥唦唧唨唩唪唫唬唭唯唰唲唳"
        + "唴唵唶唷念唹唺唻唼唽唾唿啀啁啃啄啅啇啈啉啋啌啍啎问啐啑啒启啔啕啖啖啘啙啚啛啜啝哑启啠啡唡衔啥啦啧啨啩啪"
        + "啫啬啭啮啯啰啱啲啳啴啵啶啷啹啺啻啼啽啾啿喀喁喂喃善喅喆喇喈喉喊喋喌喍喎喏喐喑喒喓喔喕喖喗喙喛喞喟喠喡喢"
        + "喣喤喥喦喨喩喯喭喯喰喱哟喳喴喵営喷喸喹喺喼喽喾喿嗀嗁嗂嗃嗄嗅呛啬嗈嗉唝嗋嗌嗍吗嗏嗐嗑嗒嗓嗕嗖嗗嗘嗙呜嗛"
        + "嗜嗝嗞嗟嗠嗡嗢嗧嗨唢嗪嗫嗬嗭嗮嗰嗱嗲嗳嗴嗵哔嗷嗸嗹嗺嗻嗼嗽嗾嗿嘀嘁嘂嘃嘄嘅嘅嘇嘈嘉嘊嘋嘌喽嘎嘏嘐嘑嘒嘓"
        + "呕嘕啧嘘嘙嘚嘛唛嘝嘞嘞嘟嘠嘡嘢嘣嘤嘥嘦嘧嘨哗嘪嘫嘬嘭唠啸囍嘴哓嘶嘷呒嘹嘺嘻嘼啴嘾嘿噀噂噃噄咴噆噇噈噉噊"
        + "噋噌噍噎噏噐噑噒嘘噔噕噖噗噘噙噚噛噜咝噞噟哒噡噢噣噤哝哕噧噩噪噫噬噭噮嗳噰噱哙噳喷噵噶噷吨噺噻噼噽噾噿"
        + "咛嚁嚂嚃嚄嚅嚆吓嚈嚉嚊嚋哜嚍嚎嚏尝嚑嚒嚓嚔噜嚖嚗嚘啮嚚嚛嚜嚝嚞嚟嚠嚡嚢嚣嚤呖嚧咙嚩咙嚧嚪嚫嚬嚭嚯嚰嚱亸"
        + "喾嚵嘤嚷嚸嚹嚺嚻嚼嚽嚾嚿啭嗫嚣囃囄冁囆囇呓囊囋囍囎囏囐嘱囒啮囔囕囖囘囙囜囝回囟囡団囤囥囦囧囨囩囱囫囬囮"
        + "囯困囱囲図囵囶囷囸囹囻囼图囿圀圁圂圂圃圄圅圆囵圈圉圊国圌圎圏圎圐圑园圆圔圕图圗团圙圚圛圜圝圞凹凸圠圡圢"
        + "圤圥圦圧圩圪圫圬圮圯地圱圲圳圴圵圶圷圸圹圻圼埢鴪址坁坂坃坄坅坆坈坉坊坋坌坍坒坓坔坕坖坘坙坜坞坢坣坥坧坨"
        + "坩坪坫坬坭坮坯垧坱坲坳坴坶坸坹坺坻坼坽坾坿垀垁垃垅垆垇垈垉垊垌垍垎垏垐垑垓垔垕垖垗垘垙垚垛垜垝垞垟垠垡"
        + "垤垥垧垨垩垪垫垬垭垮垯垰垱垲垲垳垴埯垶垷垸垹垺垺垻垼垽垾垽垿埀埁埂埃埄埅埆埇埈埉埊埋埌埍城埏埐埑埒埓埔"
        + "埕埖埗埘埙埚埛野埝埞域埠垭埢埣埤埥埦埧埨埩埪埫埬埭埮埯埰埱埲埳埴埵埶执埸培基埻崎埽埾埿堀堁堃堄坚堆堇堈"
        + "堉垩堋堌堍堎堏堐堑堒堓堔堕垴堗堘堙堚堛堜埚堞堟堠堢堣堥堦堧堨堩堫堬堭堮尧堰报堲堳场堶堷堸堹堺堻堼堽堾堼"
        + "堾碱塀塁塂塃塄塅塇塆塈塉块茔塌塍塎垲塐塑埘塓塕塖涂塘塙冢塛塜塝塟塠墘塣墘塥塦塧塨塩塪填塬塭塮塯塰塱塲塳"
        + "塴尘塶塷塸堑塺塻塼塽塾塿墀墁墂墄墅墆墇墈墉垫墋墌墍墎墏墐墒墒墓墔墕墖墘墖墚墛坠墝增墠墡墢墣墤墥墦墧墨墩"
        + "墪樽墬墭堕墯墰墱墲坟墴墵垯墷墸墹墺墙墼墽垦墿壀壁壂壃壄壅壆坛壈壉壊垱壌壍埙壏壐壑壒压壔壕壖壗垒圹垆壛壜"
        + "壝垄壠壡坜壣壤壥壦壧壨坝塆圭壭壱売壳壴壵壶壷壸壶壻壸壾壿夀夁夃夅夆夈変夊夌夎夐夑夒夓夔夗夘夛夝夞夡夣夤"
        + "夥夦夨夨夬夯夰夲夳夵夶夹夻夼夽夹夿奀奁奃奂奄奃奅奆奊奌奍奏奂奒奓奘奙奚奛奜奝奞奟奡奣奤奦奨奁奫妸奯奰奱"
        + "奲奵奺奻奼奾奿妀妁妅妉妊妋妌妍妎妏妐妑妔妕妗妘妚妛妜妟妠妡妢妤妦妧妩妫妭妮妯妰妱妲妴妵妶妷妸妺妼妽妿姀"
        + "姁姂姃姄姅姆姇姈姉姊姌姗姎姏姒姕姖姘姙姛姝姞姟姠姡姢姣姤姥奸姧姨姩姫姬姭姮姯姰姱姲姳姴姵姶姷姸姹姺姻姼"
        + "姽姾娀威娂娅娆娈娉娊娋娌娍娎娏娐娑娒娓娔娕娖娗娙娚娱娜娝娞娟娠娡娢娣娤娥娦娧娨娩娪娫娬娭娮娯娰娱娲娳娴"
        + "娵娷娸娹娺娻娽娾娿婀娄婂婃婄婅婇婈婋婌婍婎婏婐婑婒婓婔婕婖婗婘婙婛婜婝婞婟婠婡婢婣婤婥妇婧婨婩婪婫娅婮"
        + "婯婰婱婲婳婵婷婸婹婺婻婼婽婾婿媀媁媂媄媃媅媪媈媉媊媋媌媍媎媏媐媑媒媓媔媕媖媗媘媙媚媛媜媝媜媞媟媠媡媢媣"
        + "媤媥媦媨媩媪媫媬媭妫媰媱媲媳媴媵媶媷媸媹媺媻媪媾嫀嫃嫄嫅嫆嫇嫈嫉嫊袅嫌嫍嫎嫏嫐嫑嫒嫓嫔嫕嫖妪嫘嫙嫚嫛嫜"
        + "嫝嫞嫟嫠嫡嫢嫣嫤嫥嫦嫧嫨嫧嫩嫪嫫嫬嫭嫮嫯嫰嫱嫲嫳嫴嫳妩嫶嫷嫸嫹嫺娴嫼嫽嫾婳妫嬁嬂嬃嬄嬅嬆嬇娆嬉嬊娇嬍嬎"
        + "嬏嬐嬑嬒嬓嬔嬕嬖嬗嬘嫱嬚嬛嬜嬞嬟嬠嫒嬢嬣嬥嬦嬧嬨嬩嫔嬫嬬奶嬬嬮嬯婴嬱嬲嬳嬴嬵嬶嬷婶嬹嬺嬻嬼嬽嬾嬿孀孁孂"
        + "娘孄孅孆孇孆孈孉孊娈孋孊孍孎孏嫫婿媚孑孒孓孖孚孛孜孞孠孡孢孥学孧孨孪孙孬孭孮孯孰孱孲孳孴孵孶孷孹孻孼孽"
        + "孾宄宆宊宍宎宐宑宒宓宔宖実宥宧宨宩宬宭宯宱宲宷宸宺宻宼寀寁寃寈寉寊寋寍寎寏寔寕寖寗寘寙寚寜寝寠寡寣寥寪"
        + "寭寮寯寰寱寲寳寴寷寽対尀専尃尅尌尐尒尕尗尛尜尞尟尠尣尢尥尦尨尩尪尫尬尭尮尯尰尲尳尴尵尶尾屃届屇屈屎屐屑"
        + "屒屓屔屖屗屘屙屚屛屉扉屟屡屣履屦屧屦屩屪屫属敳屮屰屲屳屴屵屶屷屸屹屺屻屼屽屾屿岃岄岅岆岇岈岉岊岋岌岍岎"
        + "岏岐岑岒岓岔岕岖岘岙岚岜岝岞岟岠岗岢岣岤岥岦岧岨岪岫岬岮岯岰岲岴岵岶岷岹岺岻岼岽岾岿峀峁峂峃峄峅峆峇峈"
        + "峉峊峋峌峍峎峏峐峑峒峓崓峖峗峘峚峙峛峜峝峞峟峠峢峣峤峥峦峧峨峩峪峬峫峭峮峯峱峲峳岘峵峷峸峹峺峼峾峿崀崁"
        + "崂崃崄崅崆崇崈崉崊崋崌崃崎崏崐崒崓崔崕崖崘崚崛崜崝崞崟岽崡峥崣崤崥崦崧崨崩崪崫崬崭崮崯崰崱崲嵛崴崵崶崷"
        + "崸崹崺崻崼崽崾崿嵀嵁嵂嵃嵄嵅嵆嵇嵈嵉嵊嵋嵌嵍嵎嵏岚嵑岩嵓嵔嵕嵖嵗嵘嵙嵚嵛嵜嵝嵞嵟嵠嵡嵢嵣嵤嵥嵦嵧嵨嵩嵪"
        + "嵫嵬嵭嵮嵯嵰嵱嵲嵳嵴嵵嵶嵷嵸嵹嵺嵻嵼嵽嵾嵿嶀嵝嶂嶃崭嶅嶆岖嶈嶉嶊嶋嶌嶍嶎嶏嶐嶑嶒嶓嵚嶕嶖嶘嶙嶚嶛嶜嶝嶞"
        + "嶟峤嶡峣嶣嶤嶥嶦峄峃嶩嶪嶫嶬嶭崄嶯嶰嶱嶲嶳岙嶵嶶嶷嵘嶹岭嶻屿岳帋巀巁巂巃巄巅巆巇巈巉巊岿巌巍巎巏巐巑峦"
        + "巓巅巕岩巗巘巙巚巛巜巠巡巢巣巤匘巪巬巭巯巵巶巸卺巺巼巽巿帀币帄帇帉帊帋帍帎帏帑帒帓帔帗帙帚帞帟帠帡帢帣"
        + "帤帨帩帪帬帯帰帱帲帴帵帷帹帺帻帼帽帾帿帧幁幂帏幄幅幆幇幈幉幊幋幌幍幎幏幐幑幒幓幖幙幚幛幜幝幞帜幠幡幢幤"
        + "幥幦幧幨幩幪幭幮幯幰幱幷幺吆玄幼兹滋庀庁仄広庅庇庈庉庋庌庍庎庑庖庘庛庝庞庠庡庢庣庤庥庨庩庪库庬庮庯庰庱"
        + "庲庳庴庵庹庺庻庼庽庿廀厕廃厩廅廆廇廋廌廍庼廏廐廑廒廔廕廖廗廘廙廛廜廞庑廤廥廦廧廨廭廮廯廰痈廲廵廸廹廻廼"
        + "廽廿弁弅弆弇弉弋弌弍弎弐弑弖弙弚弜弝弞弡弢弣弤弨弩弪弫弬弭弮弰弲弪弴弶弸弻弼弽弿彀彁彂彃彄彅彇彉彋弥彍"
        + "彏彑彔彖彗彘彚彛彜彝彞彟彡彣彧彨彭彮彯彲澎彳彴彵彶彷彸役彺彻彽彾佛徂徃徆徇徉后徍徎徏径徒従徔徕徖徙徚徛"
        + "徜徝从徟徕御徢徣徤徥徦徧徨复循徫旁徭微徯徰徱徲徳徴徵徶德徸彻徺徻徼徽徾徿忀忁忂忄惔愔忇忈忉忊忋忎忏忐忑"
        + "忒忓忔忕忖忚忛応忝忞忟忡忢忣忥忦忨忩忪忬忭忮忯忰忱忲忳忴念忶汹忸忹忺忻忼忾忿怂怃怄怅怆怇怈怉怊怋怌怍怏"
        + "怐怑怓怔怗怘怙怚怛怞怟怡怢怣怤怦怩怫怬怭怮怯怰怲怳怴怵怶怷怸怹怺怼悙怿恀恁恂恃恄恅恒恇恈恉恊恌恍恎恏恑"
        + "恒恓恔恖恗恘恙恚恛恜恝恞恠恡恦恧恫恬恮恰恱恲恴恷恹恺恻恽恾恿悀悁悂悃悆悇悈悊悋悌悍悎悏悐悑悒悓悕悖悗悘"
        + "悙悚悛悜悝悞悟悡悢悤悥悧悩悪悫悭悮悰悱悳悴悷悹悺悻悼悾悿惀惁惂惃惄惆惈惉惊惋惌惍惎惏惐惑惒惓惔惕惖惗惘"
        + "惙惚惛惜惝惞惠恶惢惣惤惥惦惧惨惩惪惫惬惮恼恽惴惵惶惸惺惼惽惾惿愀愂愃愄愅愆愇愉愊愋愌愍愎愐愑愒愓愕愖愗"
        + "愘愙愝愞愠愡愢愣愥愦愧愩愪愫愬愭愮愯愰愱愲愳怆愵愶恺愸愹愺愻愼愽忾愿慀慁慂慃栗慅慆慈慉慊态慏慐慑慒慓慔"
        + "慖慗惨慙惭慛慜慝慞恸慠慡慢慥慦慧慨慩怄怂慬悯慯慰慲悭慴慵慷慸慹慺慻慽慿憀憁忧憃憄憅憆憇憈憉惫憋憌憍憎憏"
        + "怜憓憔憕憖憗憘憙憛憜憝憞憟憠憡憢憣愤憥憦憧憨憩憪憬憭怃憯憰憱憳憴憵忆憷憸憹憺憻憼憽憾憿懀懁懂懄懅懆恳懈"
        + "懊懋怿懔懎懏懐懑懓懔懕懖懗懘懙懚懛懜懝怼懠懡懢懑懤懥懦懧恹懩懪懫懬懭懮懯懰懱惩懳懴懵懒怀悬懹忏懻惧懽慑"
        + "懿恋戁戂戃戄戅戆懯戉戊戋戌戍戎戓戋戕彧或戗戙戛戜戝戞戟戠戡戢戣戤戥戦戗戨戬截戫戭戮戱戳戴戵戈戚残牋戸戹"
        + "戺戻戼戽戾扂扃扄扅扆扈扊扏扐払扖扗扙扚扜扝扞扟扠扦扢扣扤扥扦扨扪扭扮扰扲扴扵扷扸抵扻扽抁挸抆抇抈抉抋抌"
        + "抍抎抏抐抔抖抙抝択抟抠抡抣护抦抧抨抩抪抬抮抰抲抳抵抶抷抸抹抺押抻抽抾抿拀拁拃拄拇拈拊拌拎拏拑拓拕拗拘拙"
        + "拚拝拞拠拡拢拣拤拧择拪拫括拭拮拯拰拱拲拳拴拵拶拷拸拹拺拻拼拽拾拿挀挂挃挄挅挆挈挊挋挌挍挎挏挐挒挓挔挕挗"
        + "挘挙挚挛挜挝挞挟挠挡挢挣挦挧挨挩挪挫挬挭挮挰挱挲挳挴挵挷挸挹挺挻挼挽挿捀捁捂捃捄捅捆捇捈捊捋捌捍捎捏捐"
        + "捑捒捓捔捕捖捗捘捙捚捛捜捝捞损捠捡换捣捤捥捦捧舍捩捪扪捬捭据捯捰捱捳捴捵捶捷捸捹捺捻捼捽捾捿掀掁掂扫抡"
        + "掅掆掇授掉掊掋掍掎掐掑排掓掔掕挜掖掘挣掚挂掜掝掞掟掠采探掣掤掦措掫掬掭掮掯掰掱掲掳掴掵掶掸掹掺掻掼掽掾"
        + "掿拣揁揂揃揅揄揆揇揈揉揊揋揌揍揎揑揓揔揕揖揗揘揙揜揝揞揟揠揢揤揥揦揧揨揫捂揰揱揲揳援揵揶揷揸揻揼揾揿搀"
        + "搁搂搃搄搅搇搈搉搊搋搌搎搏搐搑搒搓搔搕搘搙搚搛搝擀搠搡搢搣搤捶搦搧搨搩搪搫搬搮搰搱搲搳搴搵搷搸搹搻搼搽"
        + "榨搿摂摅摈摉摋摌摍摎摏摐掴摒摓摔摕摖摗摙摚摛掼摝摞摠摡摢揸摤摥摦摧摨摪摫摬摭摮挚摰摱摲抠摴摵抟摷摹摺掺"
        + "摼摽摾摿撀撁撂撃撄撅撉撊撋撌撍撎挦挠撒挠撔撖撗撘撙捻撛撜撝挢撠撡掸掸撧撨撩撪撬撮撯撱揿撴撵撶撷撸撹撺挞"
        + "撼撽挝擀擃掳擅擆擈擉擌擎擏擐擑擓擕擖擗擘擙擛擜擝擞擟擡擢擤擥擧擨擩擪擫擭擮摈擳擵擶撷擸擹擽擿攁攂攃摅攅"
        + "撵攇攈攉攊攋攌攍攎拢攐攑攒攓攕撄攗攘搀攚撺攞攟攠攡攒挛攥攦攧攨攩搅攫攭攮攰攱攲攳攴攵攸攺攼攽敀敁敂敃敄"
        + "敆敇敉敊敋敍敐敒敓敔敕敖敚敜敟敠敡敤敥敧敨敩敪敫敭敮敯敱敳敶敹敺敻敼敽敾敿斀斁敛斄斅斆敦斈斉斊斍斎斏斒"
        + "斓斔斓斖斑斘斚斛斝斞斟斠斡斢斣斦斨斪斫斩斮斱斲斳斴斵斶斸斺斻於斾斿旀旃旄旆旇旈旊旍旎旐旑旒旓旔旕旖旘旙"
        + "旚旜旝旞旟旡旣旤兂旪旫旮旯旰旱旲旳旴旵旸旹旻旼旽旾旿昀昁昃昄昅昈昉昊昋昍昐昑昒昕昖昗昘昙昚昛昜昝晻昢昣"
        + "昤春昦昧昩昪昫昬昮昰昱昲昳昴昵昶昷昸昹昺昻昼昽昿晀晁时晃晄晅晆晇晈晋晊晌晍晎晏晐晑晒晓晔晕晖晗晘晙晛晜"
        + "晞晟晠晡晰晣晤晥晦晧晪晫晬晭晰晱晲晳晴晵晷晸晹晻晼晽晾晿暀暁暂暃暄暅暆暇晕晖暊暋暌暍暎暏暐暑暒暓暔暕暖"
        + "暗旸暙暚暛暜暝暞暟暠暡暣暤暥暦暧暨暩暪暬暭暮暯暰昵暲暳暴暵暶暷暸暹暺暻暼暽暾暿曀曁曂曃晔曅曈曊曋曌曍曎"
        + "曏曐曑曒曓曔曕曗曘曙曚曛曜曝曞曟旷曡曢曣曤曥曦曧昽曩曪曫晒曭曮曯曰曱曵曶曷曹曺曻曽朁朂朄朅朆朇最羯肜朊"
        + "朌朎朏朐朑朒朓朕朖朘朙朚朜朞朠朡朣朤朥朦胧朩术朰朲朳枛朸朹朻朼朾朿杁杄杅杆圬杈杉杊杋杍杒杓杔杕杗杘杙杚"
        + "杛杝杞杢杣杤杦杧杩杪杫杬杮柿杰东杲杳杴杵杶杷杸杹杺杻杼杽枀枂枃枅枆枇枈枊枋枌枍枎枏析枑枒枓枔枖枘枙枛枞"
        + "枟枠枡枤枥枦枧枨枩枬枭枮枰枱枲枳枵枷枸枹枺枻枼枽枾枿柀柁柂柃柄柅柆柇柈柉柊柋柌柍柎柒柕柖柗柘柙查楂呆柙"
        + "柚柛柜柝柞柟柠柡柢柣柤柦柧柨柩柪柬柭柮柯柰柲柳栅柶柷柸柹拐査柼柽柾栀栁栂栃栄栆栈栉栊栋栌栍栎栐旬栔栕栗"
        + "栘栙栚栛栜栝栞栟栠栢栣栤栥栦栧栨栩株栫栬栭栮栯栰栱栲栳栴栵栶核栺栻栽栾栿桀桁桂桄桅桇桉桊桋桍桎桏桒桕桖"
        + "桗桘桙桚桛桜桝桞桟桠桡桢档桤桦桧桨桩桪桫桬桭杯桯桰桱桲桳桴桵桶桷桸桹桺桻桼桽桾杆梀梁梂梃梄梅梆梇梈梉枣"
        + "梌梍梎梏梐梑梒梓梕梖梗枧梙梚梛梜梞梠梡梢梣梤梥梧梩梪梫梬梭梮梯械梲梴梵梶梷梸梹梺梻梼梽梾梿检棁棂棃棅棆"
        + "棇棈棉棊棋棌棍棎棏棐棒棓棔棕枨枣棘棙棚棛棜棝棞栋棠棡棢棣棤棥棦棨棩棪棫桊棭棯棰棱栖棳棴棵梾棷棸棹棺棻棼"
        + "棽棾棿椀椁椂椃椄椆椇椈椉椊椋椌椎桠椐椒椓椔椕椖椗椘椙椚椛検椝椞椟椠椡椢椣椤椥椦椧椨椩椪椫椬椭椮椯椰椱椲"
        + "椳椴椵椶椷椸椹椺椻椼椽椾椿楀楁楂楃楅楆楇楈楉杨楋楌楍楎楏楐楑楒楔楕楖楗楘楛楜楝楞楟楠楡楢楣楤楥楦楧桢楩"
        + "楪楫楬楮椑楯楰楱楲楳楴极楶榉榊榋榌楷楸楹楺楻楽楾楿榀榁榃榄榅榆榇榈榉榊榋榌榍槝搌榑榒榓榔榕榖榗榘榙榚榛"
        + "榜榝榞榟榠榡榢榣榤榥榧榨榩杩榫榬榭榯榰榱榲榳榴榵榶榷榸榹榺榻榼榽榾桤槀槁槂盘槄槅槆槇槈槉槊构槌枪槎槏槐"
        + "槑槒杠槔槕槖槗様槙槚槛槜槝槞槟槠槡槢槣槥槦椠椁槩槪槫槬槭槮槯槰槱槲桨槴槵槶槷槸槹槺槻槼槽槾槿樀桩樃樄枞"
        + "樆樇樈樉樊樋樌樍樎樏樐樒樔樕樖樗樘樚樛樜樝樟樠樢样樤樥樦樧樨権横樫樬樭樮樯樰樱樲樳樴樵樶樷朴树桦樻樼樽"
        + "樾樿橀橁橂橃橄橅橆橇桡橉橊桥橌橍橎橏橐橑橒橓橔橕橖橗橘橙橚橛橜橝橞橠橡椭橣橤橥橧橨橩橪橬橭橮橯橰橱橲橳"
        + "橴橵橶橷橸橹橺橻橼柜橿檀檩檂檃檄檅檆檇檈柽檊檋檌檍檎檏檐檑檒檓档檕檖檗檘檙檚檛桧檝檞槚檠檡检樯檤檥檦檧"
        + "檨檩檪檫檬檭梼檰檱檲槟檴檵檶栎柠檹檺槛檼檽桐檿櫀櫁棹柜櫄櫅櫆櫇櫈櫉櫊櫋櫌櫍櫎櫏累櫑櫒櫔櫕櫖櫗櫘櫙榈栉櫜"
        + "椟橼櫠櫡櫢櫣櫤橱櫦槠栌櫩枥橥榇櫭櫮櫯櫰櫱櫲栊櫴櫵櫶櫷榉櫹櫼櫽櫾櫿欀欁欂欃栏欅欆欇欈欉权欋欌欍欎椤欐欑栾"
        + "欓欔欕榄欗欘欙欚欛欜欝棂欟欤欥欦欨欩欪欫欬欭欮欯欰欱欳欴欵欶欷唉欹欻欼钦款欿歀歁歂歃歄歅歆歇歈歉歊歋歍"
        + "欧歑歒歓歔殓歗歘歙歚歛歜歝歞欤歠欢钦歧歨歩歫歬歭歮歯歰歱歳歴歵歶歾殁殁殂殃殄殅殆殇殈殉殌殍殎殏殐殑殒殓"
        + "殔殕殖殗残殙殚殛殜殝殒殟殠殡殢殣殇殥殦殧殨殩殪殚殬殰殱歼殶殸殹殾殿毂毃毄毅殴毇毈毉毊母毋毎毐毑毓坶拇毖"
        + "毗毘坒陛屁芘楷砒玭昆吡纰妣锴鈚秕庇沘毜毝毞毟毠毡毢毣毤毥毦毧毨毩毪毫毬毭毮毯毰毱毲毳毴毵毶毷毸毹毺毻毼"
        + "毽毾毵氀氁氂氃氋氄氅氆氇毡氉氊氍氎氒氐抵坻坁胝阍痻泜汦茋芪柢砥奃睧眡蚳蚔呧軧軝崏弤婚怟惛忯岻貾氕氖気氘"
        + "氙氚氜氝氞氟氠氡氢氤氥氦氧氨氩氪氭氮氯氰氱氲氶氷凼氺氻氼氽氾氿汀汃汄汅氽汈汊汋汌泛汏汐汑汒汓汔汕汖汘污"
        + "汚汛汜汞汢汣汥汦汧汨汩汫汬汭汮汯汰汱汲汳汴汵汶汷汸汹汻汼汾汿沀沂沃沄沅沆沇沊沋沌冱沎沏洓沓沔沕沗沘沚沛"
        + "沜沝沞沠沢沣沤沥沦沨沩沪沫沬沭沮沯沰沱沲沴沵沶沷沸沺沽泀泂泃泅泆泇泈泋泌泍泎泏泐泑泒泓泔泖泗泘泙泚泜溯"
        + "泞泟泠泤泦泧泩泫泬泭泮泯泱泲泴泵泶泷泸泹泺泾泿洀洂洃洄洅洆洇洈洉洊洌洍洎洏洐洑洒洓洔洕洖洘洙洚洜洝洠洡"
        + "洢洣洤洦洧洨洫洬洭洮洯洰洱洳洴洵洷洸洹洺洼洽洿浀浂浃浄浈浉浊浌浍浏浐浒浔浕浖浗浘浚浛浜浝浞浟浠浡浢浣浤"
        + "浥浦浧浨浫浭浯浰浱浲浳浵浶浃浺浻浼浽浾浿涀涁涂涃涄涅涆泾涊涋涍涎涐涑涒涓涔涖涗涘涙涚涜涝涞涟涠涡涢涣涤"
        + "涥涧涪涫涬涭涰涱涳涴涶涷涸涹涺涻凉涽涾涿淁淂淃淄淅淆淇淈淉淊淌淍淎淏淐淓淔淕淖淗淙淛淜淞淟淠淢淣淤渌淦"
        + "淧沦淬淭淯淰淲淳淴涞滍淾淿渀渁渂渃渄渆渇済渋渌渍渎渏渑渒渓渕渖渘渚渜渝渞渟沨渥渧渨渪渫渮渰渱渲渳渵渶渷"
        + "渹渻渼渽渿湀湁湂湄湅湆湇湈湉湋湌湍湎湏湐湑湒湓湔湕湗湙湚湜湝浈湟湠湡湢湤湥湦湨湩湪湫湬湭湮湰湱湲湳湴湵"
        + "湶湷湸湹湺湻湼湽満溁溂溄溆溇沩溉溊溋溌溍溎溏溑溒溓溔溕溗溘溙溚溛溞溟溠溡溣溤溥溦溧溨溩溬溭溯溰溱溲涢溴"
        + "溵溶溷溸溹溻溽溾溿滀滁滂滃沧滆滇滈滉滊涤滍荥滏滐滒滓滖滗滘滙滛滜滝滞滟滠滢滣滦滧滪滫沪滭滮滰滱渗滳滵滶"
        + "滹滺浐滼滽漀漃漄漅漈漉溇漋漌漍漎漐漑澙熹漗漘漙沤漛漜漝漞漟漡漤漥漦漧漨漪渍漭漮漯漰漱漳漴溆漶漷漹漺漻漼"
        + "漽漾浆潀颍潂潃潄潅潆潇潈潉潊潋潌潍潎潏潐潒潓洁潕潖潗潘沩潚潜潝潞潟潠潡潢潣润潥潦潧潨潩潪潫潬潭浔溃潱潲"
        + "潳潴潵潶滗潸潹潺潻潼潽潾涠澁澄澃澅浇涝澈澉澊澋澌澍澎澏湃澐澑澒澓澔澕澖涧澘澙澚澛澜澝澞澟渑澢澣泽澥滪澧"
        + "澨澪澫澬澭浍澯澰淀澲澳澴澵澶澷澸澹澺澻澼澽澾澿濂濄濅濆濇濈濉濊濋濌濍濎濏濐濑濒濓沵濖濗泞濙濚濛浕濝濞济"
        + "濠濡濢濣涛濥濦濧濨濩濪滥浚濭濮濯潍滨濲濳濴濵濶濷濸濹溅濻泺濽滤濿瀀漾瀂瀃灋渎瀇瀈泻瀊沈瀌瀍瀎浏瀐瀒瀓瀔"
        + "濒瀖瀗泸瀙瀚瀛瀜瀞潇潆瀡瀢瀣瀤瀥潴泷濑瀩瀪瀫瀬瀭瀮瀯弥瀱潋瀳瀴瀵瀶瀷瀸瀹瀺瀻瀼瀽澜瀿灀灁瀺灂沣滠灅灆灇"
        + "灈灉灊灋灌灍灎灏灐洒灒灓漓灖灗滩灙灚灛灜灏灞灟灠灡灢湾滦灥灦灧灨灪灮灱灲灳灴灷灸灹灺灻灼炀炁炂炃炄炅炆"
        + "炇炈炋炌炍炏炐炑炓炔炕炖炗炘炙炚炛炜炝炞炟炠炡炢炣炤炥炦炧炨炩炪炫炯炰炱炲炳炴炵炶炷炻炽炾炿烀烁烃烄烅"
        + "烆烇烉烊烋烌烍烎烐烑烒烓烔烕烖烗烙烚烜烝烞烠烡烢烣烥烩烪烯烰烱烲烳烃烵烶烷烸烹烺烻烼烾烿焀焁焂焃焄焇焈"
        + "焉焋焌焍焎焏焐焑焒焓焔焕焖焗焘焙焛焜焝焞焟焠焢焣焤焥焧焨焩焪焫焬焭焮焯焱焲焳焴焵焷焸焹焺焻焼焽焾焿煀煁"
        + "煂煃煄煅煇煈炼煊煋煌煍煎煏煐煑炜煓煔暖煗煘煚煛煜煝煞煟煠煡茕煣焕煦煨煪煫炀煭煯煰煱煲煳煴煵煶煷煸煹煺煻"
        + "煼煽煾煿熀熁熂熃熄熅熆熇熈熉熋熌熍熎熏熐熑荧熓熔熕熖炝熘熚熛熜熝熞熠熡熢熣熤熥熦熧熨熩熪熫熬熭熮熯熰颎"
        + "熳熴熵熶熷熸熹熺熻熼熽炽熿燀烨燂燅燆燇炖燊燋燌燍燎燏燐燑燓燔燖燗燘燚燛燝燞燠燡燢燣燤燥灿燧燨燩燪燫燮燯"
        + "燰燱燲燳烩燵燵燸燹燺薰燽焘燿爀爁爂爃爄爅爇爈爉爊爋爌烁爎爏爑爒爓爔爕爖爗爘爙爚烂爜爝爞爟爠爡爢爣爤爥爦"
        + "爧爨爩爮爯爰爳爴舀爷爻爼爽牀牁牂牃牄牅牊牉牋牍牎牏牐牑牒牓牔牕牖牗牚芽牜牝牞牟牣牤牥牦牨牪牫牬牭牮牯牰"
        + "牱牳牴牶牷牸牻牼牾牿犂犃犄犅犆犇犈犉犊犋犌犍犎犏犐犑犒犓犔犕荦犗犘犙犚牦犜犝犞犟犠犡犣犤犥犦牺犨犩犪犫"
        + "犭犰犱犲犳犴犵犷犸犹犺犻犼犽犾犿狘狁狃狄狅狆狇狉狊狋狌狍狎狏狑狒狓狔狕狖狙狚狛狜狝狞狟狡猯狢狣狤狥狦狧"
        + "狨狩狪狫狭狮狯狰狱狲狳狴狵狶狷狭狺狻狾狿猀猁猂猃猄猅猆猇猈猉猊猋猌猍猑猒猓猔猕猗猘狰猚猝猞猟猠猡猢猣猤"
        + "猥猦猧猨猬猭猰猱猲猳猵犹猷猸猹猺狲猼猽猾獀犸獂獆獇獈獉獊獋獌獍獏獐獑獒獓獔獕獖獗獘獙獚獛獜獝獞獟獠獡獢"
        + "獣獤獥獦獧獩狯猃獬獭狝獯狞獱獳獴獶獹獽獾獿猡玁玂玃")