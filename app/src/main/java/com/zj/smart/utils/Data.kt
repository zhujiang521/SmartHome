package com.zj.smart.utils

import android.os.Parcelable
import com.zj.smart.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class StaggeredGridData(
    val nameId: Int,
    val resId: Int,
    val smartType: List<SmartType> = arrayListOf(SmartType.Ordinary),
    val detailsId: Int = R.string.device_details
) : Parcelable

enum class SmartType {
    // 普通模式
    Ordinary,

    // 嗨放模式
    HighPlay,

    // 睡眠模式
    Sleep,

    // 安防模式
    Placement,

    // 工作模式
    Working
}

val staggeredGridDataMutableList = mutableListOf<StaggeredGridData>().apply {
    add(
        StaggeredGridData(
            R.string.air, R.drawable.air, arrayListOf(
                SmartType.Ordinary,
                SmartType.HighPlay,
                SmartType.Sleep,
                SmartType.Placement,
                SmartType.Working
            )
        )
    )
    add(
        StaggeredGridData(
            R.string.display, R.drawable.display, arrayListOf(
                SmartType.Ordinary, SmartType.HighPlay, SmartType.Placement, SmartType.Working
            )
        )
    )
    add(
        StaggeredGridData(
            R.string.projector, R.drawable.projector, arrayListOf(
                SmartType.Ordinary,
                SmartType.HighPlay,
            )
        )
    )
    add(
        StaggeredGridData(
            R.string.light, R.drawable.light, arrayListOf(
                SmartType.Ordinary, SmartType.HighPlay, SmartType.Working
            )
        )
    )
    add(
        StaggeredGridData(
            R.string.lock, R.drawable.lock, arrayListOf(
                SmartType.Ordinary,
                SmartType.Placement,
            )
        )
    )
    add(
        StaggeredGridData(
            R.string.camera, R.drawable.camera, arrayListOf(
                SmartType.Ordinary,
                SmartType.Sleep,
                SmartType.Placement,
            )
        )
    )
    add(
        StaggeredGridData(
            R.string.scale, R.drawable.scale, arrayListOf(
                SmartType.Ordinary,
            )
        )
    )
    add(
        StaggeredGridData(
            R.string.cleaner, R.drawable.cleaner, arrayListOf(
                SmartType.Ordinary,
                SmartType.Placement,
            )
        )
    )
}
