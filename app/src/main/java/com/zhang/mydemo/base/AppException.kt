package com.zhang.mydemo.base

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/17
 * 描述　:自定义错误信息异常
 */
class AppException : Exception {

    var errorMsg: String //错误消息
    var errCode: Int = 0 //错误码
    var errorLog: String? //错误日志
    var throwable: Throwable? = null

    constructor(
        errCode: Int? = 0,
        error: String?,
        errorLog: String? = "",
        throwable: Throwable? = null
    ) : super(error) {
        this.errorMsg = error ?: "请求失败，请稍后再试"
        this.errCode = errCode!!
        this.errorLog = errorLog ?: this.errorMsg
        this.throwable = throwable
    }

    constructor(errorMessage: ErrorMessage, e: Throwable?) {
        errCode = errorMessage.getKey()
        errorMsg = errorMessage.getValue()
        errorLog = e?.message
        throwable = e
    }
}