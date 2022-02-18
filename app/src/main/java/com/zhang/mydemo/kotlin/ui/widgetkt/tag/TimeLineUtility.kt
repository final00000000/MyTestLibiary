package com.zhang.mydemo.kotlin.ui.widgetkt.tag

import android.app.Activity
import android.text.SpannableString
import android.text.Spanned
import android.text.style.URLSpan
import android.text.util.Linkify

/**
 * Created by liuz on 16/6/7.
 */
object TimeLineUtility {
    private var mColor = "#EF8200"

    //    public static void addLinks(TextView view) {
    //        CharSequence content = view.getText();
    //        view.setText(convertNormalStringToSpannableString(content.toString()));
    //        if (view.getLinksClickable()) {
    //            view.setMovementMethod(LongClickableLinkMovementMethod.getInstance());
    //        }
    //    }
    fun convertNormalStringToSpannableString(
        txt: String,
        status: TimeLineStatus?
    ): SpannableString {
        //hack to fix android imagespan bug,see http://stackoverflow.com/questions/3253148/imagespan-is-cut-off-incorrectly-aligned
        //if string only contains emotion tags,add a empty char to the end
        val hackTxt: String = if (txt.startsWith("[") && txt.endsWith("]")) {
            "$txt "
        } else {
            txt
        }
        val value: SpannableString = SpannableString.valueOf(hackTxt)
        when (status) {
            TimeLineStatus.LINK -> {
                Linkify.addLinks(value, WeiboPatterns.WEB_URL, WeiboPatterns.WEB_SCHEME)
            }
            TimeLineStatus.FEED -> {
                Linkify.addLinks(value, WeiboPatterns.WEB_URL, WeiboPatterns.WEB_SCHEME)
                Linkify.addLinks(value, WeiboPatterns.TOPIC_URL, WeiboPatterns.TOPIC_SCHEME)
                Linkify.addLinks(value, WeiboPatterns.MENTION_URL, WeiboPatterns.MENTION_SCHEME)
            }
            else -> {

            }
        }
        val urlSpans: Array<URLSpan> = value.getSpans(0, value.length, URLSpan::class.java)
        var weiboSpan: MyURLSpan?
        for (urlSpan in urlSpans) {
            if (urlSpan.url.startsWith(WeiboPatterns.TOPIC_SCHEME)) {
                val topic: String = urlSpan.url
                    .substring(WeiboPatterns.TOPIC_SCHEME.length, urlSpan.url.length)
                //不识别空格话题和大于30字话题
                val group = topic.substring(1, topic.length - 1).trim { it <= ' ' }
                if (group.isEmpty() || group.length > 30) {
                    value.removeSpan(urlSpan)
                    continue
                }
            }
            weiboSpan = MyURLSpan(urlSpan.url, mColor)
            val start: Int = value.getSpanStart(urlSpan)
            val end: Int = value.getSpanEnd(urlSpan)
            value.removeSpan(urlSpan)
            value.setSpan(weiboSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return value
    }

    enum class TimeLineStatus {
        LINK, FEED
    }
}