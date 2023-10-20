@file:OptIn(ExperimentalMaterial3Api::class)

package com.zj.smart

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zj.smart.utils.RoomType
import com.zj.smart.utils.SmartType
import com.zj.smart.utils.StaggeredGridData
import com.zj.smart.utils.roomType
import com.zj.smart.utils.staggeredGridDataMutableList
import com.zj.smart.view.FeatureThatRequiresLocationPermissions
import com.zj.smart.view.VrView
import com.zj.smart.view.pagerTabIndicatorOffset
import com.zj.smart.widget.mode.checkType
import kotlinx.coroutines.launch


private val resIds = arrayOf(R.drawable.new_home, R.drawable.new_room)
private val pages = arrayOf(R.string.house_living_room, R.string.house_bedroom)


@Composable
fun MainPage(toScan: () -> Unit, toDetails: (StaggeredGridData) -> Unit) {
    val showPermission = rememberSaveable { mutableStateOf(false) }
    val isJump = rememberSaveable { mutableStateOf(false) }
    val alertDialog = rememberSaveable { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                // 标题
                title = {
                    Text(text = stringResource(id = R.string.home))
                },
                // 其它按钮
                actions = {
                    IconButton(onClick = { showPermission.value = true }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(shape = CircleShape, onClick = { /* 处理点击事件 */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_keyboard_voice_24),
                    contentDescription = ""
                )

            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (showPermission.value || alertDialog.value) {
                FeatureThatRequiresLocationPermissions(alertDialog) {
                    if (!isJump.value) {
                        toScan()
                        isJump.value = false
                    }
                }
                showPermission.value = false
            }
            VrPager(toDetails)

            //填充数据
            LazyVerticalStaggeredGrid(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 9.dp),
                columns = StaggeredGridCells.Fixed(2),
                content = {
                    items(staggeredGridDataMutableList) {
                        val smartType = it.smartType
                        if (smartType.contains(checkType.value) &&
                            it.roomType.contains(roomType.value)
                        ) {
                            SmartCard(it, toDetails)
                        }
                    }
                })
        }

    }

}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun VrPager(toDetails: (StaggeredGridData) -> Unit) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        pages.size
    }

    val current = LocalContext.current
    HorizontalPager(
        modifier = Modifier,
        state = pagerState,
        key = { pages[pagerState.currentPage] },
        pageContent = { page ->
            Card(modifier = Modifier.padding(horizontal = 16.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(210.dp),
                    contentAlignment = Alignment.Center
                ) {
                    VrView(resIds[page])
                    Text(
                        text = "1111111",
                        color = Color.Transparent,
                        modifier = Modifier.clickable {
                            toDetails(
                                StaggeredGridData(
                                    R.string.display, R.drawable.display, arrayListOf(
                                        SmartType.Ordinary,
                                        SmartType.HighPlay,
                                        SmartType.Placement,
                                        SmartType.Working,
                                    ), arrayListOf(RoomType.Bedroom, RoomType.LivingRoom)
                                )
                            )
                        })
                }
            }
        }
    )
    TabRow(
        // Our selected tab is our current page
        selectedTabIndex = pagerState.currentPage,
        // override the indicator, using the provided pagerTabIndicatorOffset modifier
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }) {
        // Add tabs for all of our pages
        pages.forEachIndexed { index, titleId ->
            Tab(
                text = { Text(stringResource(id = titleId)) },
                selected = pagerState.currentPage == index,
                onClick = {
                    // Later, scroll to page 2
                    scope.launch {
                        roomType.value = when (index) {
                            0 -> RoomType.LivingRoom
                            1 -> RoomType.Bedroom
                            else -> RoomType.LivingRoom
                        }
                        pagerState.scrollToPage(index)
                    }
                },
            )
        }
    }
}

@Composable
private fun SmartCard(
    staggeredGridData: StaggeredGridData, toDetails: (StaggeredGridData) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                toDetails(staggeredGridData)
            }, colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onTertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary
        )
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = staggeredGridData.resId),
            contentDescription = stringResource(id = staggeredGridData.nameId),
            contentScale = if (staggeredGridData.resId == R.drawable.legion_black) {
                ContentScale.Crop
            } else {
                ContentScale.Fit
            }
        )

        Text(
            text = stringResource(id = staggeredGridData.nameId),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.Center)
                .padding(10.dp),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 15.sp
        )
    }
}