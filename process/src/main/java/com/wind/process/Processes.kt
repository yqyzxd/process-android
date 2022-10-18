package com.wind.process

import android.app.Application.getProcessName
import android.content.Context

/**
 * Copyright (C), 2015-2022, 杭州迈优文化创意有限公司
 * FileName: Processes
 * Author: wind
 * Date: 2022/10/18 17:26
 * Description: 描述该类的作用
 * Path: 路径
 * History:
 *  <author> <time> <version> <desc>
 *
 */
object Processes {
    private var processName: String? = ""

    fun isMainProcess(context: Context?): Boolean {
        return if (context == null) {
            false
        } else {
            val pkgName = context.applicationContext.packageName
            val processName = getProcessName(context)
            pkgName == processName
        }
    }

    fun getProcessName(context: Context): String? {

        if (processName.isNullOrEmpty()) {
            kotlin.run loop@{
                listOf(
                    ApplicationProcessGetter(),
                    FileProcessGetter(),
                    ActivityManagerProcessGetter(context)
                ).forEach {
                    processName = it.getProcess()
                    if (processName.isNullOrEmpty().not()) {
                        return@loop
                    }
                }
            }

        }

        return processName
    }
}