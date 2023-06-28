package com.zj.smart.widget

import android.content.Context
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.LocalSize
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.lazy.GridCells
import androidx.glance.appwidget.lazy.LazyVerticalGrid
import androidx.glance.appwidget.lazy.itemsIndexed
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.zj.smart.R
import com.zj.smart.widget.theme.GlanceColorScheme
import com.zj.smart.widget.theme.GlanceTextStyles

class ModeSwitchWidgetGlance : GlanceAppWidget() {

    private val modeList =
        listOf(R.string.mode1, R.string.mode2, R.string.mode3, R.string.mode4, R.string.mode5)

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            GlanceTheme(
                colors = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    GlanceTheme.colors
                } else {
                    GlanceColorScheme.colors
                }
            ) {
                Column(
                    modifier = GlanceModifier.fillMaxSize().background(GlanceTheme.colors.surface),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    // set key for each size so that the ontogglebookmark lambda is called only once for the
                    // active size.
                    key(LocalSize.current) {
                        Text(
                            text = context.getString(R.string.widget_name),
                            modifier = GlanceModifier.padding(
                                top = 10.dp,
                                start = 10.dp,
                                end = 10.dp
                            ),
                            style = GlanceTextStyles.bodyLarge.copy(color = GlanceTheme.colors.onBackground)
                        )
                        ModeGrid(context)
                    }

                }
            }
        }
    }

    @Composable
    private fun ModeGrid(context: Context) {
        val checkIndex = rememberSaveable { mutableStateOf(0) }

        LazyVerticalGrid(
            modifier = GlanceModifier.fillMaxSize().padding(5.dp),
            gridCells = GridCells.Fixed(2)
        ) {
            itemsIndexed(modeList) { index, data ->
                ModeItem(checkIndex, index, context.getString(data))
            }
        }
    }

    @Composable
    private fun ModeItem(checkIndex: MutableState<Int>, index: Int, data: String) {
        Box(
            modifier = GlanceModifier.fillMaxWidth().height(70.dp)
                .padding(5.dp)
        ) {
            Row(
                modifier = GlanceModifier.fillMaxSize()
                    .cornerRadius(10.dp)
                    .background(
                        if (index == checkIndex.value) GlanceTheme.colors.errorContainer
                        else GlanceTheme.colors.tertiaryContainer
                    )
                    .clickable {
                        checkIndex.value = index
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = data,
                    maxLines = 2,
                    style = TextStyle(fontSize = 13.sp, color = GlanceTheme.colors.onBackground)
                )
            }
        }
    }
}