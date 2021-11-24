package com.zhang.utilslibiary.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import java.util.*

/**
 * @author geyifeng
 * @date 2019-04-23 13:46
 */
object AppManager {
    // Activity栈
    private val mActivities = Stack<Activity>()

    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity) {
        mActivities.add(activity)
    }

    /**
     * 移除当前activity
     * @param activity Activity
     */
    fun removeActivity(activity: Activity) {
        hideSoftKeyBoard(activity)
        activity.finish()
        mActivities.remove(activity)
    }

    /**
     * 移除所有activity
     */
    fun removeAllActivity() {
        for (activity in mActivities) {
            hideSoftKeyBoard(activity)
            activity.finish()
        }
        mActivities.clear()
    }


    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        val activity: Activity = mActivities.lastElement()
        finishActivity(activity)
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity?) {
        var activity = activity
        if (activity != null) {
            AppManager.mActivities.remove(activity)
            activity.finish()
            activity = null
        }
    }
    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        for (i in mActivities.indices) {
            if (null != mActivities.get(i)) {
                mActivities[i].finish()
            }
        }
        mActivities.clear()
    }

    fun hideSoftKeyBoard(activity: Activity) {
        val localView = activity.currentFocus
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (localView != null && imm != null) {
            imm.hideSoftInputFromWindow(localView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}