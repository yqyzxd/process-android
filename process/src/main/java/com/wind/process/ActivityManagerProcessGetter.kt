package com.wind.process

import android.app.ActivityManager
import android.content.Context
import android.os.Process

/**
 * Copyright (C), 2015-2022, 杭州迈优文化创意有限公司
 * FileName: ActivityManagerProcessGetter
 * Author: wind
 * Date: 2022/10/18 17:24
 * Description: 描述该类的作用
 * Path: 路径
 * History:
 *  <author> <time> <version> <desc>
 *
 */
internal class ActivityManagerProcessGetter(private val context:Context) : ProcessGetter {
    override fun getProcess(): String? {
        var processName: String? = null
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager?
        am?.apply {
            kotlin.run lp@{
                runningAppProcesses.forEach {
                    if (it.pid == Process.myPid()) {
                        processName = it.processName
                        return@lp
                    }
                }
            }
        }
        return processName
    }
}