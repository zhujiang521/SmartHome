package com.zj.smart.widget.mode

import android.content.Context
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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
import com.zj.smart.utils.SmartType
import com.zj.smart.utils.VibrateUtils
import com.zj.smart.utils.appWidgetBackgroundCornerRadius
import com.zj.smart.utils.stringResource
import com.zj.smart.widget.theme.GlanceColorScheme
import com.zj.smart.widget.theme.GlanceTextStyles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

private val checkIndex = mutableIntStateOf(0)
val checkType = mutableStateOf(SmartType.Ordinary)


class ModeSwitchWidgetGlance : GlanceAppWidget() {

    private val modeList =
        listOf(R.string.mode1, R.string.mode2, R.string.mode3, R.string.mode4, R.string.mode5)

    private suspend fun getName(): String {
        val name = withContext(Dispatchers.IO) {
            delay(1000L)
            "Home"
        }
        return name
    }

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val name = getName()
        provideContent {
            GlanceTheme(
                colors = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    GlanceTheme.colors
                } else {
                    GlanceColorScheme.colors
                }
            ) {
                Column(
                    modifier = GlanceModifier.fillMaxSize().background(GlanceTheme.colors.surface)
                        .appWidgetBackgroundCornerRadius(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    // set key for each size so that the onToggleBookmark lambda is called only once for the
                    // active size.
                    key(LocalSize.current) {
                        Text(
                            text = "${stringResource(id = R.string.widget_name)} $name",
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
        VibrateUtils.initVibrator(context)
        LazyVerticalGrid(
            modifier = GlanceModifier.fillMaxSize().padding(5.dp),
            gridCells = GridCells.Fixed(2)
        ) {
            itemsIndexed(modeList) { index, data ->
                ModeItem(context, index, data)
            }
        }
    }

    @Composable
    private fun ModeItem(context: Context, index: Int, data: Int) {
        Box(
            modifier = GlanceModifier.fillMaxWidth().height(70.dp)
                .padding(5.dp)
        ) {
            Row(
                modifier = GlanceModifier.fillMaxSize()
                    .cornerRadius(10.dp)
                    .background(
                        if (index == checkIndex.intValue) GlanceTheme.colors.errorContainer
                        else GlanceTheme.colors.tertiaryContainer
                    )
                    .clickable {
                        if (checkIndex.intValue != index) {
                            checkIndex.intValue = index
                            checkType.value = when (index) {
                                0 -> SmartType.Ordinary
                                1 -> SmartType.HighPlay
                                2 -> SmartType.Sleep
                                3 -> SmartType.Placement
                                4 -> SmartType.Ordinary
                                else -> SmartType.Working
                            }
                            VibrateUtils.vibrate(context, 100L)
                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(data),
                    maxLines = 2,
                    style = TextStyle(fontSize = 13.sp, color = GlanceTheme.colors.onBackground)
                )
            }
        }
    }
}