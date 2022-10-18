package com.wind.process

import android.app.Application
import android.os.Build

/**
 * Copyright (C), 2015-2022, 杭州迈优文化创意有限公司
 * 通过Application新的API获取进程名，无需反射，无需IPC，效率最高。
 */
internal class ApplicationProcessGetter :ProcessGetter {
    override fun getProcess(): String? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Application.getProcessName()
        } else null
    }
}