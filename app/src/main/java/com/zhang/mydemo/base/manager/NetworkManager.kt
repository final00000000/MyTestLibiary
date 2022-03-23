package com.zhang.mydemo.base.manager

/**
 * 作者　: hegaojian
 * 时间　: 2020/5/2
 * 描述　: 网络变化管理者
 */
class NetworkManager private constructor() {

    val mNetworkStateCallback = EventLiveData<NetWorkState>()

    companion object {
        val instance: NetworkManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkManager()
        }
    }

}