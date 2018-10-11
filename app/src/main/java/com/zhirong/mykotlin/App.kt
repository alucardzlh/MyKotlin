package com.zhirong.mykotlin

import android.app.Application
import com.zhirong.mykotlin.Http.ApiClient

/**
 * 描述：application
 * @author 章龙海.
 * @date 2018/9/30 17:17.
 */
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        ApiClient.instance.init()
    }
}