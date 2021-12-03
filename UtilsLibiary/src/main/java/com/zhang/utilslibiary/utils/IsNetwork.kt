package com.zhang.utilslibiary.utils

import android.content.Context
import android.net.NetworkInfo

import android.net.ConnectivityManager
import android.telephony.TelephonyManager





/**
 * @Author : zhang
 * @Create Time : 2021/11/30
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
object IsNetwork {
    /**
     * 判断是否有网络连接
     * @param context Context?
     * @return Boolean
     */
    fun isNetworkConnected(context: Context?): Boolean {
        if (context != null) {
            val mConnectivityManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mNetworkInfo = mConnectivityManager.activeNetworkInfo
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable
            }
        }
        return false
    }

}