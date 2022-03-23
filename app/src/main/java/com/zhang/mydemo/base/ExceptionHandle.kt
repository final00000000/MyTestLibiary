package com.zhang.mydemo.base

import android.net.ParseException
import com.google.gson.JsonParseException
import com.google.gson.stream.MalformedJsonException
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException

/**
 * 描述　: 根据异常返回相关的错误信息工具类
 */
object ExceptionHandle {

    fun handleException(e: Throwable?): AppException {
        val ex: AppException
        e?.let {
            when (it) {
                is HttpException -> {
                    ex = AppException(ErrorMessage.NETWORK_ERROR, e)
                    return ex
                }
                is JsonParseException, is JSONException, is ParseException, is MalformedJsonException -> {
                    ex = AppException(ErrorMessage.PARSE_ERROR, e)
                    return ex
                }
                is ConnectException -> {
                    ex = AppException(ErrorMessage.NETWORK_ERROR, e)
                    return ex
                }
                is javax.net.ssl.SSLException -> {
                    ex = AppException(ErrorMessage.SSL_ERROR, e)
                    return ex
                }
                is ConnectTimeoutException -> {
                    ex = AppException(ErrorMessage.TIMEOUT_ERROR, e)
                    return ex
                }
                is java.net.SocketTimeoutException -> {
                    ex = AppException(ErrorMessage.TIMEOUT_ERROR, e)
                    return ex
                }
                is java.net.UnknownHostException -> {
                    ex = AppException(ErrorMessage.TIMEOUT_ERROR, e)
                    return ex
                }
                is AppException -> return it

                else -> {
                    ex = AppException(ErrorMessage.UNKNOWN, e)
                    return ex
                }
            }
        }
        ex = AppException(ErrorMessage.UNKNOWN, e)
        return ex
    }
}