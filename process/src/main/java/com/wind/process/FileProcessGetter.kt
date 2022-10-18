package com.wind.process

import android.os.Process
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

/**
 * Copyright (C), 2015-2022, 杭州迈优文化创意有限公司
 * FileName: FileProcessGetter
 * Author: wind
 * Date: 2022/10/18 17:23
 * Description: 描述该类的作用
 * Path: 路径
 * History:
 *  <author> <time> <version> <desc>
 *
 */
internal class FileProcessGetter :ProcessGetter{
    override fun getProcess(): String? {
        var reader: BufferedReader? = null
        try {
            val fileName = "/proc/" + Process.myPid() + "/cmdline"
            reader =
                BufferedReader(InputStreamReader(FileInputStream(fileName), "iso-8859-1"))
            val builder = StringBuilder()
            var read: Int
            while (reader.read().also { read = it } > 0) {
                builder.append(read.toChar())
            }
            return builder.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                reader?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return null
    }
}