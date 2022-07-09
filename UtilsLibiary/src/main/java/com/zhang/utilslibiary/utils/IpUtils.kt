package com.zhang.utilslibiary.utils

import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.*


/**
 * @Author : zhang
 * @Create Time : 2022/7/9
 * @Class Describe :  Android开发获取内网IP地址和外网IP地址的工具类
 * @Project Name : MyDemo
 */
object IpUtils {
    /**
     * 获取内网IP地址的方法
     *
     * @return
     */
    val ipAddress: String
        get() {
            try {
                val enNetI = NetworkInterface.getNetworkInterfaces()
                while (enNetI
                        .hasMoreElements()
                ) {
                    val netI = enNetI.nextElement()
                    val enumIpAddr = netI.inetAddresses
                    while (enumIpAddr.hasMoreElements()) {
                        val inetAddress = enumIpAddr.nextElement()
                        if (inetAddress is Inet4Address && !inetAddress.isLoopbackAddress()) {
                            val aa = inetAddress.getHostAddress()
                            //                        System.out.println("当前内网IP测试："+aa);
                            return inetAddress.getHostAddress()
                        }
                    }
                }
            } catch (e: SocketException) {
                e.printStackTrace()
            }
            return ""
        }// 将流转化为字符串
    //                System.out.println("当前外网IP测试："+ipAddress);
//单独只有IP外网地址的API
    //            String address = "https://ifconfig.co/json"; //json格式信息的API，使用这个自己搞代码
//            String address = "http://pv.sohu.com/cityjson?ie=utf-8"; //json格式信息的API，下面有一个使用案例。
    //设置浏览器ua 保证不出现503

    /**
     * 获取外网ip地址的方法1--网页信息格式
     * @return
     */
    val outNetIP: String
        get() {
            var ipAddress = ""
            try {
                val address = "http://www.3322.org/dyndns/getip" //单独只有IP外网地址的API
                //            String address = "https://ifconfig.co/json"; //json格式信息的API，使用这个自己搞代码
                //            String address = "http://pv.sohu.com/cityjson?ie=utf-8"; //json格式信息的API，下面有一个使用案例。
                val url = URL(address)
                val connection = url.openConnection() as HttpURLConnection
                connection.useCaches = false
                connection.requestMethod = "GET"
                connection.setRequestProperty(
                    "user-agent",
                    "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.7 Safari/537.36"
                ) //设置浏览器ua 保证不出现503
                if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                    val `in` = connection.inputStream
                    // 将流转化为字符串
                    val reader = BufferedReader(InputStreamReader(`in`))
                    var tmpString: String
                    val retJSON = StringBuilder()
                    while (reader.readLine().also { tmpString = it } != null) {
                        retJSON.append(
                            """
                                 $tmpString
                                 
                                 """.trimIndent()
                        )
                    }
                    ipAddress = retJSON.toString()
                    //                System.out.println("当前外网IP测试："+ipAddress);
                } else {
                    println("网络连接异常，无法获取IP地址！")
                }
            } catch (e: Exception) {
            }
            return ipAddress
        }

    /**
     * 获取外网ip地址的方法2--Json格式
     * @return
     */
    fun GetNetIp(): String {
        var infoUrl: URL? = null
        var inStream: InputStream? = null
        var line = ""
        try {
            infoUrl = URL("http://pv.sohu.com/cityjson?ie=utf-8") //json格式信息的API，使用案例。
            val connection: URLConnection = infoUrl.openConnection()
            val httpConnection = connection as HttpURLConnection
            val responseCode = httpConnection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inStream = httpConnection.inputStream
                val reader = BufferedReader(InputStreamReader(inStream, "utf-8"))
                val strber = StringBuilder()
                while (reader.readLine().also { line = it } != null) strber.append(
                    """
                        $line
                        
                        """.trimIndent()
                )
                inStream.close()
                // 从反馈的结果中提取出IP地址
                val start = strber.indexOf("{")
                val end = strber.indexOf("}")
                val json = strber.substring(start, end + 1)
                if (json != null) {
                    try {
                        val jsonObject = JSONObject(json)
                        line = jsonObject.optString("cip")
                        //                        System.out.println("IP："+line);
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
                return line
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return line
    }
}
