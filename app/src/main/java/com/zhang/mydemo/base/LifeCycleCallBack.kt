package com.zhang.mydemo.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.zhang.utilslibiary.utils.AppActivityManager
import timber.log.Timber

/**
 * 描述　:
 */
class LifeCycleCallBack : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        AppActivityManager.addActivity(activity)
        Timber.e(" onActivityCreated ${activity.localClassName}")
    }

    override fun onActivityStarted(activity: Activity) {
        Timber.e(" onActivityStarted ${activity.localClassName}")
    }

    override fun onActivityResumed(activity: Activity) {
        Timber.e(" onActivityResumed ${activity.localClassName}")
    }

    override fun onActivityPaused(activity: Activity) {
        Timber.e(" onActivityPaused ${activity.localClassName}")
    }


    override fun onActivityDestroyed(activity: Activity) {
        Timber.e(" onActivityDestroyed ${activity.localClassName}")
        AppActivityManager.removeActivity(activity)
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        Timber.e(" onActivitySaveInstanceState ${activity.localClassName}")
    }

    override fun onActivityStopped(activity: Activity) {
        Timber.e(" onActivityStopped ${activity.localClassName}")
    }


}