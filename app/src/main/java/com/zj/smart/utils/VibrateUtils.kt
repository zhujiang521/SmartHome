package com.zj.smart.utils

import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.os.Build
import android.os.VibrationEffect
import android.os.VibrationEffect.DEFAULT_AMPLITUDE
import android.os.Vibrator
import android.os.VibratorManager

/**
 * 震动工具类
 */
object VibrateUtils {

    private var vib: Vibrator? = null

    /**
     * 震动 milliseconds 毫秒
     */
    fun vibrate(context: Context, milliseconds: Long) {
        initVibrator(context)
        vib?.vibrate(VibrationEffect.createOneShot(milliseconds, DEFAULT_AMPLITUDE))
    }

    /**
     * 初始化震动
     */
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
        vib?.vibrate(VibrationEffect.createWaveform(pattern, repeat))
    }


    //取消震动
    fun vibratorCancel(context: Context) {
        initVibrator(context)
        try {
            vib?.cancel()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}