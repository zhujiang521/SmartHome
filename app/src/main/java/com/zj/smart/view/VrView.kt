package com.zj.smart.view

import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.glance.text.Text
import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener
import com.google.vr.sdk.widgets.pano.VrPanoramaView
import com.zj.smart.R

@Composable
fun VrView(resId: Int = R.drawable.new_home) {
    // Adds view to Compose
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp), // Occupy the max size in the Compose UI tree
        factory = { context ->
            // Creates custom view
            VrPanoramaView(context).apply {
                // Sets up listeners for View -> Compose communication
                setEventListener(object : VrPanoramaEventListener() {})
                val options = VrPanoramaView.Options()
                setInfoButtonEnabled(false)// 设置隐藏最左边信息的按钮
                setStereoModeButtonEnabled(false)// 设置隐藏立体模型的按钮
                setFullscreenButtonEnabled(true)// 隐藏全屏模式按钮
                setTouchTrackingEnabled(true) //false 只能通过传感器旋转 true设置传感器旋转加上左右方向可以通过触摸滑动旋转
//        setPureTouchTracking(true) //设置手动上下左右滑动，不能通过传感器旋转

                options.inputType = VrPanoramaView.Options.TYPE_MONO
                val bitmap = BitmapFactory.decodeResource(resources, resId)
                loadImageFromBitmap(bitmap, options)
            }
        },
        update = {
            // View's been inflated or state read in this block has been updated
            // Add logic here if necessary

            // As selectedItem is read here, AndroidView will recompose
            // whenever the state changes
            // Example of Compose -> View communication
        }
    )
}

@Preview
@Composable
private fun ContentExample() {
    Column(Modifier.fillMaxSize()) {
        Text("Look at this CustomView!")
        VrView()
    }
}