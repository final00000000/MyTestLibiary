package com.zhang.mydemo.ui.widget.richeditor

import android.R
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.animation.LinearInterpolator
import android.webkit.*
import com.zhang.mydemo.ui.widget.Utils.Companion.currentTime
import com.zhang.mydemo.ui.widget.Utils.Companion.decodeResource
import com.zhang.mydemo.ui.widget.Utils.Companion.toBase64
import com.zhang.mydemo.ui.widget.Utils.Companion.toBitmap
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.util.*

/**
 * 富文本编辑实现类
 * Created by ZQiong on 2018/3/22.
 */
class RichEditor @SuppressLint("SetJavaScriptEnabled") constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int
) : WebView(context, attrs, defStyleAttr) {
    enum class Type {
        BOLD, ITALIC, SUBSCRIPT, SUPERSCRIPT, STRIKETHROUGH, UNDERLINE, H1, H2, H3, H4, H5, H6, ORDEREDLIST, UNORDEREDLIST, JUSTIFYCENTER, JUSTIFYFULL, JUSTIFYLEFT, JUSTIFYRIGHT
    }

    interface OnTextChangeListener {
        fun onTextChange(text: String?)
    }

    interface OnDecorationStateListener {
        fun onStateChangeListener(text: String?, types: List<Type>?)
    }

    interface AfterInitialLoadListener {
        fun onAfterInitialLoad(isReady: Boolean)
    }

    private var isReady = false
    private var mContents: String? = null
    private var mTextChangeListener: OnTextChangeListener? = null
    private var mDecorationStateListener: OnDecorationStateListener? = null
    private var mLoadListener: AfterInitialLoadListener? = null

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null) : this(
        context,
        attrs,
        R.attr.webViewStyle
    ) {
    }

    protected fun createWebviewClient(): EditorWebViewClient {
        return EditorWebViewClient()
    }

    fun setOnTextChangeListener(listener: OnTextChangeListener?) {
        mTextChangeListener = listener
    }

    fun setOnDecorationChangeListener(listener: OnDecorationStateListener?) {
        mDecorationStateListener = listener
    }

    fun setOnInitialLoadListener(listener: AfterInitialLoadListener?) {
        mLoadListener = listener
    }

    private fun callback(text: String) {
        mContents = text.replaceFirst(CALLBACK_SCHEME.toRegex(), "")
        if (mTextChangeListener != null) {
            mTextChangeListener!!.onTextChange(mContents)
        }
    }

    private fun stateCheck(text: String) {
        val state = text.replaceFirst(STATE_SCHEME.toRegex(), "").toUpperCase(Locale.ENGLISH)
        val types: MutableList<Type> = ArrayList()
        for (type in Type.values()) {
            if (TextUtils.indexOf(state, type.name) != -1) {
                types.add(type)
            }
        }
        if (mDecorationStateListener != null) {
            mDecorationStateListener!!.onStateChangeListener(state, types)
        }
    }

    private fun applyAttributes(context: Context, attrs: AttributeSet?) {
        val attrsArray = intArrayOf(
            R.attr.gravity
        )
        val ta = context.obtainStyledAttributes(attrs, attrsArray)
        val gravity = ta.getInt(0, NO_ID)
        when (gravity) {
            Gravity.LEFT -> exec("javascript:RE.setTextAlign(\"left\")")
            Gravity.RIGHT -> exec("javascript:RE.setTextAlign(\"right\")")
            Gravity.TOP -> exec("javascript:RE.setVerticalAlign(\"top\")")
            Gravity.BOTTOM -> exec("javascript:RE.setVerticalAlign(\"bottom\")")
            Gravity.CENTER_VERTICAL -> exec("javascript:RE.setVerticalAlign(\"middle\")")
            Gravity.CENTER_HORIZONTAL -> exec("javascript:RE.setTextAlign(\"center\")")
            Gravity.CENTER -> {
                exec("javascript:RE.setVerticalAlign(\"middle\")")
                exec("javascript:RE.setTextAlign(\"center\")")
            }
        }
        ta.recycle()
    }

    // No handling
    var html: String?
        get() = mContents
        set(contents) {
            var contents = contents
            if (contents == null) {
                contents = ""
            }
            try {
                exec("javascript:RE.setHtml('" + URLEncoder.encode(contents, "UTF-8") + "');")
            } catch (e: UnsupportedEncodingException) {
                // No handling
            }
            mContents = contents
        }

    fun setEditorFontColor(color: Int) {
        val hex = convertHexColorString(color)
        exec("javascript:RE.setBaseTextColor('$hex');")
    }

    fun setEditorFontSize(px: Int) {
        exec("javascript:RE.setBaseFontSize('" + px + "px');")
    }

    override fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
        super.setPadding(left, top, right, bottom)
        exec(
            "javascript:RE.setPadding('" + left + "px', '" + top + "px', '" + right + "px', '" + bottom
                    + "px');"
        )
    }

    override fun setPaddingRelative(start: Int, top: Int, end: Int, bottom: Int) {
        // still not support RTL.
        setPadding(start, top, end, bottom)
    }

    fun setEditorBackgroundColor(color: Int) {
        setBackgroundColor(color)
    }

    override fun setBackgroundColor(color: Int) {
        super.setBackgroundColor(color)
    }

    override fun setBackgroundResource(resid: Int) {
        val bitmap = decodeResource(context, resid)
        val base64 = toBase64(bitmap)
        bitmap.recycle()
        exec("javascript:RE.setBackgroundImage('url(data:image/png;base64,$base64)');")
    }

    override fun setBackground(background: Drawable) {
        val bitmap = toBitmap(background)
        val base64 = toBase64(bitmap)
        bitmap.recycle()
        exec("javascript:RE.setBackgroundImage('url(data:image/png;base64,$base64)');")
    }

    fun setBackground(url: String) {
        exec("javascript:RE.setBackgroundImage('url($url)');")
    }

    fun setEditorWidth(px: Int) {
        exec("javascript:RE.setWidth('" + px + "px');")
    }

    fun setEditorHeight(px: Int) {
        exec("javascript:RE.setHeight('" + px + "px');")
    }

    fun setPlaceholder(placeholder: String) {
        exec("javascript:RE.setPlaceholder('$placeholder');")
    }

    fun setInputEnabled(inputEnabled: Boolean) {
        exec("javascript:RE.setInputEnabled($inputEnabled)")
    }

    fun loadCSS(cssFile: String) {
        val jsCSSImport = "(function() {" +
                "    var head  = document.getElementsByTagName(\"head\")[0];" +
                "    var link  = document.createElement(\"link\");" +
                "    link.rel  = \"stylesheet\";" +
                "    link.type = \"text/css\";" +
                "    link.href = \"" + cssFile + "\";" +
                "    link.media = \"all\";" +
                "    head.appendChild(link);" +
                "}) ();"
        exec("javascript:$jsCSSImport")
    }

    fun undo() {
        exec("javascript:RE.undo();")
    }

    fun redo() {
        exec("javascript:RE.redo();")
    }

    fun setBold() {
        exec("javascript:RE.setBold();")
    }

    fun setItalic() {
        exec("javascript:RE.setItalic();")
    }

    fun setSubscript() {
        exec("javascript:RE.setSubscript();")
    }

    fun setSuperscript() {
        exec("javascript:RE.setSuperscript();")
    }

    fun setStrikeThrough() {
        exec("javascript:RE.setStrikeThrough();")
    }

    fun setUnderline() {
        exec("javascript:RE.setUnderline();")
    }

    fun setTextColor(color: Int) {
        exec("javascript:RE.prepareInsert();")
        val hex = convertHexColorString(color)
        exec("javascript:RE.setTextColor('$hex');")
    }

    fun setTextBackgroundColor(color: Int) {
        exec("javascript:RE.prepareInsert();")
        val hex = convertHexColorString(color)
        exec("javascript:RE.setTextBackgroundColor('$hex');")
    }

    fun setFontSize(fontSize: Int) {
        if (fontSize > 7 || fontSize < 1) {
            Log.e("RichEditor", "Font size should have a value between 1-7")
        }
        exec("javascript:RE.setFontSize('$fontSize');")
    }

    fun removeFormat() {
        exec("javascript:RE.removeFormat();")
    }

    fun setHeading(heading: Int) {
        exec("javascript:RE.setHeading('$heading');")
    }

    fun setIndent() {
        exec("javascript:RE.setIndent();")
    }

    fun setOutdent() {
        exec("javascript:RE.setOutdent();")
    }

    fun setAlignLeft() {
        exec("javascript:RE.setJustifyLeft();")
    }

    fun setAlignCenter() {
        exec("javascript:RE.setJustifyCenter();")
    }

    fun setAlignRight() {
        exec("javascript:RE.setJustifyRight();")
    }

    fun setBlockquote() {
        exec("javascript:RE.setBlockquote();")
    }

    fun setBullets() {
        exec("javascript:RE.setBullets();")
    }

    fun setNumbers() {
        exec("javascript:RE.setNumbers();")
    }

    //    public void insertImage(String url, String alt) {
    //        exec("javascript:RE.prepareInsert();");
    //        exec("javascript:RE.insertImage('" + url + "', '" + alt + "');");
    //    }
    fun insertImage(url: String, alt: String?) {
        exec("javascript:RE.prepareInsert();")
        //        exec("javascript:RE.insertImage('" + url + "', '" + alt + "');");
//        String testStr = "<img src=\"http://www.1honeywan.com/siba/img/20100120/ai5.jpg\" alt=\"dachshund\"  width=\"100%\">";
        val testStr = "<img src=\"$url\" alt=\"dachshund\"  width=\"100%\"><br><br>"
        exec("javascript:RE.insertHTML('$testStr');")
    }

    /**
     * the image according to the specific width of the image automatically
     *
     * @param url
     * @param alt
     * @param width
     */
    fun insertImage(url: String, alt: String, width: Int) {
        exec("javascript:RE.prepareInsert();")
        exec("javascript:RE.insertImageW('$url', '$alt','$width');")
    }

    /**
     * [RichEditor.insertImage] will show the original size of the image.
     * So this method can manually process the image by adjusting specific width and height to fit into different mobile screens.
     *
     * @param url
     * @param alt
     * @param width
     * @param height
     */
    fun insertImage(url: String, alt: String, width: Int, height: Int) {
        exec("javascript:RE.prepareInsert();")
        exec("javascript:RE.insertImageWH('$url', '$alt','$width', '$height');")
    }

    fun insertVideo(url: String) {
        exec("javascript:RE.prepareInsert();")
        exec("javascript:RE.insertVideo('$url');")
    }

    fun insertVideo(url: String, width: Int) {
        exec("javascript:RE.prepareInsert();")
        exec("javascript:RE.insertVideoW('$url', '$width');")
    }

    fun insertVideo(url: String, width: Int, height: Int) {
        exec("javascript:RE.prepareInsert();")
        exec("javascript:RE.insertVideoWH('$url', '$width', '$height');")
    }

    fun insertAudio(url: String) {
        exec("javascript:RE.prepareInsert();")
        exec("javascript:RE.insertAudio('$url');")
    }

    fun insertYoutubeVideo(url: String) {
        exec("javascript:RE.prepareInsert();")
        exec("javascript:RE.insertYoutubeVideo('$url');")
    }

    fun insertYoutubeVideo(url: String, width: Int) {
        exec("javascript:RE.prepareInsert();")
        exec("javascript:RE.insertYoutubeVideoW('$url', '$width');")
    }

    fun insertYoutubeVideo(url: String, width: Int, height: Int) {
        exec("javascript:RE.prepareInsert();")
        exec("javascript:RE.insertYoutubeVideoWH('$url', '$width', '$height');")
    }

    fun insertLink(href: String, title: String) {
        exec("javascript:RE.prepareInsert();")
        exec("javascript:RE.insertLink('$href', '$title');")
    }

    fun insertTodo() {
        exec("javascript:RE.prepareInsert();")
        exec("javascript:RE.setTodo('" + currentTime + "');")
    }

    fun focusEditor() {
        requestFocus()
        exec("javascript:RE.focus();")
    }

    fun clearFocusEditor() {
        exec("javascript:RE.blurFocus();")
    }

    private fun convertHexColorString(color: Int): String {
        return String.format("#%06X", 0xFFFFFF and color)
    }

    protected fun exec(trigger: String) {
        if (isReady) {
            load(trigger)
        } else {
            postDelayed({ exec(trigger) }, 100)
        }
    }

    private fun load(trigger: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            evaluateJavascript(trigger, null)
        } else {
            loadUrl(trigger)
        }
    }

    protected inner class EditorWebViewClient : WebViewClient() {
        override fun onPageFinished(view: WebView, url: String) {
            isReady = url.equals(SETUP_HTML, ignoreCase = true)
            if (mLoadListener != null) {
                mLoadListener!!.onAfterInitialLoad(isReady)
            }
        }

        override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
            handler.proceed()
        }

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            val decode = Uri.decode(url)
            if (TextUtils.indexOf(url, CALLBACK_SCHEME) == 0) {
                callback(decode)
                return true
            } else if (TextUtils.indexOf(url, STATE_SCHEME) == 0) {
                stateCheck(decode)
                return true
            }
            return super.shouldOverrideUrlLoading(view, url)
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            val url = request.url.toString()
            val decode = Uri.decode(url)
            if (TextUtils.indexOf(url, CALLBACK_SCHEME) == 0) {
                callback(decode)
                return true
            } else if (TextUtils.indexOf(url, STATE_SCHEME) == 0) {
                stateCheck(decode)
                return true
            }
            return super.shouldOverrideUrlLoading(view, request)
        }
    }

    var height1 = 0

    //实例化WebViwe后，调用此方法可滚动到底部
    fun scrollToBottom() {
        val temp = computeVerticalScrollRange()
        val valueAnimator = ValueAnimator.ofInt(height1, temp)
        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.duration = 200
        valueAnimator.addUpdateListener { animation ->
            val nowHeight = animation.animatedValue as Int
            height1 = nowHeight
            scrollTo(0, height1)
            if (height1 == temp) {
                //再调用一次，解决不能滑倒底部
                scrollTo(0, computeVerticalScrollRange())
            }
        }
        valueAnimator.start()
    }

    interface ImageClickListener {
        fun onImageClick(imageUrl: String?)
    }

    private var imageClickListener: ImageClickListener? = null
    fun setImageClickListener(imageClickListener: ImageClickListener?) {
        this.imageClickListener = imageClickListener
        if (this.imageClickListener != null) {
            setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        DownX = event.x
                        DownY = event.y
                        moveX = 0f
                        moveY = 0f
                        currentMS = System.currentTimeMillis() //long currentMS     获取系统时间
                    }
                    MotionEvent.ACTION_MOVE -> {
                        moveX += Math.abs(event.x - DownX) //X轴距离
                        moveY += Math.abs(event.y - DownY) //y轴距离
                        DownX = event.x
                        DownY = event.y
                    }
                    MotionEvent.ACTION_UP -> {
                        val moveTime = System.currentTimeMillis() - currentMS //移动时间
                        //判断是否继续传递信号
                        if (moveTime < 400 && moveX < 25 && moveY < 25) {
                            //这里是点击
                            val mResult = hitTestResult
                            if (mResult != null) {
                                val type = mResult.type
                                if (type == HitTestResult.IMAGE_TYPE) { //|| type == WebView.HitTestResult.IMAGE_ANCHOR_TYPE || type == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE
                                    //如果是点击图片
                                    val imageUrl = mResult.extra
                                    setInputEnabled(false)
                                    postDelayed({
                                        if (imageClickListener != null) {
                                            if (imageUrl!!.contains("file://")) {
                                                //说明是本地文件去除
                                                val newImageUrl = imageUrl.replace("file://", "")
                                                imageClickListener.onImageClick(newImageUrl)
                                            } else {
                                                imageClickListener.onImageClick(imageUrl)
                                            }
                                        }
                                    }, 200)
                                } else {
                                    //不是点击的图片
                                }
                            }
                            //                            return true;
                        }
                    }
                }
                false
            }
        }
    }

    companion object {
        private const val SETUP_HTML = "file:///android_asset/editor.html"
        private const val CALLBACK_SCHEME = "re-callback://"
        private const val STATE_SCHEME = "re-state://"
        private var DownX = 0f
        private var DownY = 0f
        private var moveX = 0f
        private var moveY = 0f
        private var currentMS: Long = 0
    }

    init {
        isVerticalScrollBarEnabled = false
        isHorizontalScrollBarEnabled = false
        settings.javaScriptEnabled = true
        webChromeClient = WebChromeClient()
        webViewClient = createWebviewClient()
        loadUrl(SETUP_HTML)
        applyAttributes(context, attrs)
    }
}