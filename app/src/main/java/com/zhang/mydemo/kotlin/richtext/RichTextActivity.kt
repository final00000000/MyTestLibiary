package com.zhang.mydemo.kotlin.richtext;

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.zhang.mydemo.R
import com.zhang.mydemo.base.BaseActivity
import com.zhang.utilslibiary.utils.GlideEngine
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_rich_text.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

class RichTextActivity : BaseActivity() {


    //编辑图片的pop
    private var popupWindow: CommonPopupWindow? = null

    //图片地址
    private var currentUrl = ""

    //颜色选择dialog
    private var colorSelectDialog: ColorSelectDialog? = null

    // 接收的颜色值
    private var lastColor = 0


    override fun getLayoutId(): Int = R.layout.activity_rich_text

    override fun initView() {
        ivPageBack.singleClick { killMyself() }
        tvPageTitle.text = "富文本"

        // 初始化编辑器
        initRichText()

        // 初始化popupwindow
        initPopup()
    }

    override fun initData() {

    }

    override fun setListener() {
        rich_Editor.setOnDecorationChangeListener { text, types ->
            val flagArr = mutableListOf<String>()
            for (i in types.indices) {
                flagArr.add(types[i].name)
            }

            if (flagArr.contains("BOLD")) {
                iv_bold.setImageResource(R.mipmap.bold_)
            } else {
                iv_bold.setImageResource(R.mipmap.bold)
            }

            if (flagArr.contains("UNDERLINE")) {
                iv_underline.setImageResource(R.mipmap.underline_)
            } else {
                iv_underline.setImageResource(R.mipmap.underline)
            }

            if (flagArr.contains("ITALIC")) {
//                iv_italic.setImageResource(R.drawable.ic_materials_selected)
            } else {
//                iv_italic.setImageResource(R.drawable.ic_materials_unselected)
            }

            if (flagArr.contains("JUSTIFYLEFT")) {
                iv_align_left.setImageResource(R.drawable.ic_align_left)
            } else {
                iv_align_left.setImageResource(R.drawable.ic_align_left_grey)
            }

            if (flagArr.contains("JUSTIFYCENTER")) {
                iv_align_mid.setImageResource(R.drawable.ic_align_mid_black)
            } else {
                iv_align_mid.setImageResource(R.drawable.ic_align_mid_grey)
            }

            if (flagArr.contains("JUSTIFYRIGHT")) {
                iv_align_right.setImageResource(R.drawable.ic_align_right_black)
            } else {
                iv_align_right.setImageResource(R.drawable.ic_align_right_grey)
            }
        }


        iv_image.singleClick {
            //选择图片
            getPhoto()
            KeyBoardUtils.closeKeybord(et_name, this@RichTextActivity)
        }

        iv_video.singleClick {
            //选择视频
            getVideo()
            KeyBoardUtils.closeKeybord(et_name, this@RichTextActivity)
        }

        iv_bold.singleClick {
            //加粗
            againEdit()
            rich_Editor.setBold()
        }

        iv_underline.singleClick {
            //加下划线
            againEdit()
            rich_Editor.setUnderline()
        }

        iv_italic.singleClick {
            //斜体
            againEdit()
            rich_Editor.setItalic()
        }

        iv_align_left.singleClick {
            // 左对齐
            againEdit()
            rich_Editor.setAlignLeft()
        }

        iv_align_mid.singleClick {
            // 居中
            againEdit()
            rich_Editor.setAlignCenter()
        }

        iv_align_right.singleClick {
            // 右对齐
            againEdit()
            rich_Editor.setAlignRight()
        }

        iv_ul.singleClick {
            //加带点的序列号
            againEdit()
            rich_Editor.setBullets()
        }

        iv_ol.singleClick {
            //加带数字的序列号
            againEdit()
            rich_Editor.setNumbers()
        }

        iv_do.setOnClickListener {
            //反撤销
            againEdit()
            rich_Editor.redo()
        }

        iv_undo.setOnClickListener {
            //撤销
            againEdit()
            rich_Editor.undo()
        }

        iv_h1.singleClick {
            // H1
            againEdit()
            rich_Editor.setHeading(1)
        }

        iv_h2.singleClick {
            // H2
            againEdit()
            rich_Editor.setHeading(2)
        }

        iv_h3.singleClick {
            // H3
            againEdit()
            rich_Editor.setHeading(3)
        }

        iv_h4.singleClick {
            // H4
            againEdit()
            rich_Editor.setHeading(4)
        }

        iv_h5.singleClick {
            // H5
            againEdit()
            rich_Editor.setHeading(5)
        }

        iv_h6.singleClick {
            // H6
            againEdit()
            rich_Editor.setHeading(6)
        }

        // 这个字体是根据h5 font标签来设置的 1-7区间
        tv_1.singleClick {
            // 小号字体
            againEdit()
            rich_Editor.setFontSize(2)
        }

        tv_2.singleClick {
            // 标准
            againEdit()
            rich_Editor.setFontSize(3)
        }

        tv_3.singleClick {
            // 大号字体
            againEdit()
            rich_Editor.setFontSize(5)
        }

        tv_4.singleClick {
            // 超大号字体
            againEdit()
            rich_Editor.setFontSize(7)
        }

        iv_color.singleClick {

            if (colorSelectDialog == null) {
                colorSelectDialog = ColorSelectDialog(this)

                colorSelectDialog!!.onColorSelectListener =
                    object : ColorSelectDialog.OnColorSelectListener {
                        override fun onSelectFinish(color: Int) {
                            lastColor = color
                            rich_Editor.setTextColor(lastColor)
                        }
                    }
            }
            colorSelectDialog!!.setLastColor(lastColor)
            colorSelectDialog!!.show()
        }

        // 点击键盘小按钮操作-
        iv_keyboard_down.singleClick {
            val manager: InputMethodManager =
                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            if (manager != null)
                manager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
        }

        //点击图片 操作-
        rich_Editor.setImageClickListener {
            currentUrl = it
            popupWindow!!.showBottom(cl, 0.5f)
        }

    }

    private fun initRichText() {
        //输入框显示字体的大小
        rich_Editor.setEditorFontSize(16)
        //输入框显示字体的颜色
        rich_Editor.setEditorFontColor(resources.getColor(R.color.color3D3D3D))
        //输入框背景设置
        rich_Editor.setEditorBackgroundColor(Color.WHITE)
        //输入框文本padding
        rich_Editor.setPadding(10, 10, 10, 10)
        //输入提示文本
        rich_Editor.setPlaceholder("请开始你的创作！~")
    }

    private fun initPopup() {
        val view: View = LayoutInflater.from(this@RichTextActivity)
            .inflate(R.layout.newapp_pop_picture, null)

        view.find<TextView>(R.id.dismiss_photo).setOnClickListener {
            popupWindow!!.dismiss()
        }

        view.find<TextView>(R.id.compile_photo).setOnClickListener {
            toast("编辑图片 功能正在开发中...")
            popupWindow!!.dismiss()
        }

        view.find<TextView>(R.id.delete_photo).setOnClickListener {
            //删除图片
            val removeUrl =
                "<img src=\"$currentUrl\" alt=\"RichText-ImageView\" width=\"100%\"><br>"
            val newUrl: String = rich_Editor.html.replace(removeUrl, "")
            currentUrl = ""
            rich_Editor.setHtml(newUrl)
            if (RichUtils.isEmpty(rich_Editor.getHtml())) {
                rich_Editor.html = ""
            }
            popupWindow!!.dismiss()
        }


        popupWindow = CommonPopupWindow.Builder(this@RichTextActivity)
            .setView(view)
            .setWidthAndHeight(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            .setOutsideTouchable(true) //在外不可用手指取消
            .setAnimationStyle(R.style.pop_animation) //设置popWindow的出场动画
            .create()

        popupWindow!!.setOnDismissListener {
            rich_Editor.setInputEnabled(true)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val selectList = (PictureSelector.obtainMultipleResult(data) as MutableList<LocalMedia>)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                // 照片
                PictureConfig.CHOOSE_REQUEST -> {
                    if (selectList.size > 0) {
                        againEdit()
                        rich_Editor.insertImage(selectList[0].path, "RichText-ImageView")
                        KeyBoardUtils.openKeybord(et_name, this@RichTextActivity)
                    }
                }
                // 视频
                PictureConfig.PREVIEW_VIDEO_CODE -> {
                    if (selectList.size > 0) {
                        // 请求接口 上传到服务器 拿回链接
                        rich_Editor.insertVideo(selectList[0].path, 320)
                        againEdit()
                    }
                }
            }
        }
    }


    private fun againEdit() {
        //如果第一次点击例如加粗，没有焦点时，获取焦点并弹出软键盘
        rich_Editor.focusEditor()
        KeyBoardUtils.openKeybord(et_name, this@RichTextActivity)
        rich_Editor.postDelayed({
            if (rich_Editor != null) {
                rich_Editor.scrollToBottom()
            }
        }, 200)
    }

    fun getVideo() {
        PictureSelector.create(this@RichTextActivity)
            .openGallery(PictureMimeType.ofVideo())
            .queryMaxFileSize(20f)
            .imageEngine(GlideEngine.createGlideEngine())
            .selectionMode(PictureConfig.SINGLE)
            .isPreviewImage(true)
            .imageSpanCount(3)
            .isCamera(true)
            .videoMaxSecond(180)
            .forResult(PictureConfig.PREVIEW_VIDEO_CODE)
    }


    fun getPhoto() {
        PictureSelector.create(this@RichTextActivity)
            .openGallery(PictureMimeType.ofImage())
            .imageEngine(GlideEngine.createGlideEngine())
            .selectionMode(PictureConfig.SINGLE)
            .isPreviewImage(true)
            .imageSpanCount(3)
            .isCamera(true)
            .forResult(PictureConfig.CHOOSE_REQUEST)
    }


}