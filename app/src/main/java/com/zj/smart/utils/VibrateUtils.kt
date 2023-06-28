package com.zj.smart.utils

import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.os.Build
import android.os.Vibrator
import android.os.VibratorManager


object VibrateUtils {

    private var vib :Vibrator? = null

    //震动milliseconds毫秒
    fun vibrate(context: Context, milliseconds: Long) {
        initVibrator(context)
        vib?.vibrate(milliseconds)
    }

    fun initVibrator(context: Context) {
        if (vib != null) return
        vib = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager =
                context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            context.getSystemService(VIBRATOR_SERVICE) as Vibrator
        }
    }


    /**
     * 以pattern[]方式震动
     * @param repeat -1 不重复  0一直震动
     */
    fun vibrate(context: Context, pattern: LongArray, repeat: Int) {
        initVibrator(context)
        vib?.vibrate(pattern, repeat)
    }


    //取消震动
    fun virateCancle(context: Context) {
        initVibrator(context)
        try {
            vib?.cancel()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}