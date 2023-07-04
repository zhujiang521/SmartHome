package com.zj.smart.widget.smart

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalSize
import androidx.glance.action.Action
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.ContentScale
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.Text
import com.zj.smart.R
import com.zj.smart.SmartActivity
import com.zj.smart.utils.StaggeredGridData
import com.zj.smart.utils.appWidgetBackgroundCornerRadius
import com.zj.smart.utils.staggeredGridDataMutableList
import com.zj.smart.utils.stringResource
import com.zj.smart.widget.theme.GlanceColorScheme
import com.zj.smart.widget.theme.GlanceTextStyles

class SmartWidgetGlance : GlanceAppWidget() {

    companion object {
        const val STAGGERED_GRID_DATA = "STAGGERED_GRID_DATA"
    }

    override val sizeMode: SizeMode = SizeMode.Exact
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
                    modifier = GlanceModifier.fillMaxSize().background(GlanceTheme.colors.surface)
                        .appWidgetBackgroundCornerRadius(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    // set key for each size so that the onToggleBookmark lambda is called only once for the
                    // active size.
                    key(LocalSize.current) {
                        Text(
                            text = stringResource(id = R.string.widget_name),
                            modifier = GlanceModifier.padding(10.dp),
                            style = GlanceTextStyles.bodyLarge.copy(color = GlanceTheme.colors.onBackground)
                        )
                        LazyColumn(
                            modifier = GlanceModifier.fillMaxSize().padding(horizontal = 10.dp)
                        ) {
                            items(staggeredGridDataMutableList) { data ->
                                SmartGlanceCard(context, data)
                            }
                        }
                    }
                }

            }
        }
    }

    @Composable
    private fun SmartGlanceCard(context: Context, staggeredGridData: StaggeredGridData) {
        Column {
            Row(
                modifier = GlanceModifier.padding(8.dp)
                    .clickable(onClick = openSmartHome(context, staggeredGridData))
                    .cornerRadius(10.dp)
                    .background(GlanceTheme.colors.onTertiary),
                horizontalAlignment = Alignment.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Image(
                    modifier = GlanceModifier.size(80.dp).cornerRadius(8.dp),
                    provider = ImageProvider(staggeredGridData.resId),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )

                Column(
                    modifier = GlanceModifier.padding(start = 10.dp)
                ) {

                    Text(
                        text = stringResource(id = staggeredGridData.nameId),
                        maxLines = 1,
                        style = GlanceTextStyles.bodyLarge.copy(color = GlanceTheme.colors.onBackground)
                    )
                    Spacer(modifier = GlanceModifier.height(4.dp))
                    Text(
                        text = stringResource(id = staggeredGridData.detailsId),
                        style = GlanceTextStyles.bodySmall.copy(color = GlanceTheme.colors.onBackground),
                        maxLines = 3
                    )
                }
            }

            Spacer(
                modifier = GlanceModifier.fillMaxWidth().height(10.dp)
            )
        }

    }

    private fun openSmartHome(context: Context, staggeredGridData: StaggeredGridData): Action {
        // actionStartActivity is the preferred way to start activities.
        return actionStartActivity(
            Intent(context, SmartActivity::class.java).apply {
                putExtra(STAGGERED_GRID_DATA, staggeredGridData)
            }
        )
    }

}