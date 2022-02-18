package com.zhang.mydemo.kotlin.ui.widgetkt.tag

import java.util.regex.Pattern

/**
 * Created by liuz on 16/06/07.
 */
object WeiboPatterns {
    @JvmField
    val WEB_URL = Pattern
        .compile("(((http|https)://)|((?<!((http|https)://))www\\.))" + ".*?" + "(?=(&nbsp;|[\\u4e00-\\u9fa5]|\\s|　|<br />|$|[<>]))")
    @JvmField
    val TOPIC_URL = Pattern
        .compile("#[\\p{Print}\\p{InCJKUnifiedIdeographs}&&[^#]]+#")
    @JvmField
    val MENTION_URL = Pattern
        .compile("@[\u4e00-\u9fa5a-zA-Z0-9_-·\\.]+[\u200B]")
    const val WEB_SCHEME = "http://"
    const val TOPIC_SCHEME = "com.zheblog.weibo.topic://"
    const val MENTION_SCHEME = "com.zheblog.weibo.at://"
    const val WEB_COMPARE_HTTP = "http"
    const val WEB_COMPARE_HTTPS = "https"
    const val TOPIC_COMPARE_SCHEME = "com.zheblog.weibo.topic"
    const val MENTION_COMPARE_SCHEME = "com.zheblog.weibo.at"
}