package com.zj.smart.view

import android.app.Activity
import android.view.View
import android.view.WindowManager

class StatusBar(private val activity: Activity) {

    //将状态栏设置为传入的color
    fun setColor(color: Int) {
        val view = activity.window.decorView
        view.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        activity.window.statusBarColor = activity.resources.getColor(color)
    }

    //隐藏状态栏
    fun hide() {
        activity.window
            .setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
    }
}