package com.zj.smart.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.glance.GlanceId
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Column
import androidx.glance.text.Text

class SmartWidgetGlance : GlanceAppWidget() {

    override val sizeMode: SizeMode = SizeMode.Exact
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            GlanceTheme {
                Column {
                    Text("我爱你啊")
                }
            }
        }
    }


    @Composable
    fun Test() {
        Text("我爱你啊")
    }
}