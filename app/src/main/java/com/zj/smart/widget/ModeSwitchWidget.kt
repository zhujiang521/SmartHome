package com.zj.smart.widget

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver

/**
 * Implementation of App Widget functionality.
 */
class ModeSwitchWidget : GlanceAppWidgetReceiver()  {
    override val glanceAppWidget: GlanceAppWidget = ModeSwitchWidgetGlance()
}
