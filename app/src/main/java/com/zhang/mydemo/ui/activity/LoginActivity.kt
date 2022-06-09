package com.zhang.mydemo.ui.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.zhang.mydemo.R
import com.zhang.mydemo.base.activity.BaseNetWorkActivity
import com.zhang.mydemo.databinding.ActivityLoginBinding
import com.zhang.mydemo.viewmodel.LoginViewModel
import com.zhang.utilslibiary.utils.MyTextWatcher
import com.zhang.utilslibiary.utils.dialog.DialogUtils
import com.zhang.utilslibiary.utils.singleClick
import com.zhang.utilslibiary.utils.toast.Toasty
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk27.coroutines.onCheckedChange
import org.jetbrains.anko.textColor
import timber.log.Timber

@SuppressLint("SetTextI18n")
class LoginActivity : BaseNetWorkActivity<ActivityLoginBinding, LoginViewModel>() {

    /**
     * true 密码登录  false 验证码登录
     */
    private var loginType: Boolean = false

    var handler = Handler()
    var runnable: Runnable? = null

    override fun initView(savedInstanceState: Bundle?) {
        loginStatus(false)
    }


    override fun setListener() {

        tvLoginType.singleClick {
            if (tvLoginType.text.toString() == "验证码登录") {
                passwordET.visibility = View.INVISIBLE
                verificationGroup.visibility = View.VISIBLE
                loginType = false
                tvLoginType.text = getString(R.string.passwordLogin)
                loginStatus(phoneET.text!!.length == 11 && verificationET.text!!.length == 4 && checkBox_login.isChecked)
            } else {
                passwordET.visibility = View.VISIBLE
                verificationGroup.visibility = View.INVISIBLE
                loginType = true
                tvLoginType.text = getString(R.string.VerificationLogin)
                if (handler != null) handler.removeCallbacksAndMessages(runnable!!)
                sendVerification.text = "发送验证码"
                loginStatus(phoneET.text!!.length == 11 && passwordET.text!!.length >= 6 && checkBox_login.isChecked)
            }
        }
        phoneET.addTextChangedListener(object : MyTextWatcher() {
            override fun onMyTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.length == 11) {
                    Toasty.normal("手机号已注册")
                }
                loginStatus(s.length == 11 && passwordET.text!!.length >= 6 && checkBox_login.isChecked)
            }

        })
        passwordET.addTextChangedListener(object : MyTextWatcher() {
            override fun onMyTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loginStatus(phoneET.text!!.length == 11 && s!!.length >= 6 && checkBox_login.isChecked)
            }

        })
        verificationET.addTextChangedListener(object : MyTextWatcher() {
            override fun onMyTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loginStatus(phoneET.text!!.length == 11 && verificationET.text!!.length == 4 && checkBox_login.isChecked)
            }

        })

        checkBox_login.onCheckedChange { buttonView, isChecked ->
            Timber.e("LoginActivity ==== $isChecked  $loginType")
            if (loginType) {
                loginStatus(phoneET.text!!.length == 11 && passwordET.text!!.length >= 6 && isChecked)
            } else {
                loginStatus(phoneET.text!!.length == 11 && verificationET.text!!.length == 4 && isChecked)
            }

        }

        sendVerification.singleClick {
            DialogUtils.getVerificationCodeDialog(this@LoginActivity) {
                if (it) {
                    Toasty.success("调用发送验证码接口")
                    timer()
                }
            }
        }


        tvLogin.singleClick {
            if (loginType) {
                Toasty.success("调用密码登录接口")
            } else {
                Toasty.success("调用验证码登录接口")
            }
        }

    }

    private fun loginStatus(boolean: Boolean) {
        tvLogin.isSelected = boolean
        if (boolean) {
            tvLogin.isSelected = true
            tvLogin.isEnabled = true
        } else {
            tvLogin.isSelected = false
            tvLogin.isEnabled = false
        }
    }

    private fun timer() {
        var time = 60
        runnable = object : Runnable {
            override fun run() {
                //要做的事情
                time--
                sendVerification.apply {
                    text = "${time}s"
                    textColor = Color.parseColor("#BBBBBB")
                    isEnabled = false
                }
                if (time > 0) {
                    handler.postDelayed(this, 1000)
                } else {
                    sendVerification.apply {
                        text = "发送验证码"
                        textColor = Color.parseColor("#3E3E3F")
                        isEnabled = true
                    }
                    getResendCode()
                }
            }
        }
        handler.postDelayed(runnable!!, 1000)
    }

    private fun getResendCode() {
        sendVerification.singleClick {
            if (sendVerification.text == "发送验证码") {
                DialogUtils.getVerificationCodeDialog(this@LoginActivity) {
                    if (it) {
                        getVerificationCode()
                    }
                }
            } else sendVerification.isEnabled = false
        }
    }

    private fun getVerificationCode() {
        /**
         * 入参
         * userId 用户编号 false
         * mobile 手机号 true
         * authorization 授权码 true
         *
        mPresenter!!.sendSms(
        mapOf(
        "userId" to UserInfoManager.userId,
        "mobile" to UserInfoManager.userInfo?.mobile,
        "authorization" to codemd5
        ).getRequestBody()
        ) {
        if (it.code == 1000) {
        timer()
        } else {
        Timber.e("WriteOffActivity_190行_2022/2/23_13:56：${it.msg}")
        }
        }*/
        Toasty.success("调用发送验证码接口")

    }

    override fun onDestroy() {
        super.onDestroy()
        if (handler != null) handler.removeCallbacksAndMessages(runnable!!)
    }

    override fun createObserver() {
    }

}